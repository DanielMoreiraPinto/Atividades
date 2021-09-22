# Treino
import numpy as np
import pandas as pd
from sklearn import svm
from sklearn.datasets import load_wine
from sklearn.model_selection import RepeatedStratifiedKFold
from sklearn.metrics import classification_report, accuracy_score, precision_score, recall_score, f1_score, multilabel_confusion_matrix, confusion_matrix
from sklearn.preprocessing import StandardScaler
from tqdm import tqdm
from datetime import datetime
from random import seed, shuffle
import os
import seaborn as sns
import matplotlib.pyplot as plt

abspath = os.path.abspath(__file__)
dname = os.path.dirname(abspath)
os.chdir(dname)

def main():
    random_state = seed(datetime.now())
    
    # Carregando o dataset
    data = load_wine()
    data_df = pd.DataFrame(data['data'], columns=data.feature_names)
    X = data_df.to_numpy()
    y = data.target
    
    # Preparando para coletar as métricas do Repeated KFold
    general_metrics_dict = {
        "Classe 1": {
            "accuracy": 0,
            "precision": 0,
            "sensitivity": 0,
            "specificity": 0,
            "f1": 0
        },
        "Classe 2": {
           "accuracy": 0,
            "precision": 0,
            "sensitivity": 0,
            "specificity": 0,
            "f1": 0
        },
        "Classe 3": {
            "accuracy": 0,
            "precision": 0,
            "sensitivity": 0,
            "specificity": 0,
            "f1": 0
        }
    }
    os.system('clear')
    open("matriz_confusao.txt", "w").close()
    
    # Normalizando as características
    scaler = StandardScaler()
    X = scaler.fit_transform(X)

    # Preparando o Repeated KFold
    rskf = RepeatedStratifiedKFold(n_splits=5, n_repeats=100, random_state=random_state) 
    rodada = 0
    for train_index, test_index in tqdm(rskf.split(X, y)):
        rodada += 1
        
        # Coletando os conjuntos
        X_train, X_test = X[train_index], X[test_index]
        y_train, y_test = y[train_index], y[test_index]

        # Aleatorizando a ordem
        train_data = list(zip(X_train, y_train))
        shuffle(train_data)
        X_train, y_train = zip(*train_data)
        X_train, y_train = np.array(X_train), np.array(y_train)
        
        # Preparando e treinando o modelo
        model = svm.SVC(C=10, kernel='rbf', verbose=False, 
        decision_function_shape='ovo', random_state=random_state)
        model.fit(X_train, y_train)
        
        # Predizendo sobre o conjunto de teste
        predictions = model.predict(X_test)
        
        # Gerando a matriz confusão de cada classe
        mcm = multilabel_confusion_matrix(y_test, predictions, labels=[0, 1, 2])

        with open("matriz_confusao.txt", "a") as mat_out:
            mat_out.write("---- Rodada {} ----\n".format(rodada))
            mat_out.write("{}\n\n".format(mcm))
        # Os valores da matriz vêm na ordem TN, FP, FN, TP
        results_setosa = mcm[0].ravel()
        results_versicolor = mcm[1].ravel()
        results_virginica = mcm[2].ravel()
        if rodada < 2:
            cf_matrix = confusion_matrix(y_test, predictions, labels=[0, 1, 2])
            plt.figure(figsize = (10,7))
            cfm_plot = sns.heatmap(cf_matrix, annot=True, fmt="", cmap="Blues")
            cfm_plot.figure.savefig("confusion_matrix_example.png")

        # Calculando as métricas
        fold_metrics_dict = {
            "Classe 1": {
                "accuracy": (results_setosa[0] + results_setosa[3]) / sum(results_setosa),
                "precision": results_setosa[3] / (results_setosa[1] + results_setosa[3]),
                "sensitivity": results_setosa[3] / (results_setosa[2] + results_setosa[3]),
                "specificity": results_setosa[0] / (results_setosa[0] + results_setosa[1]),
                "f1": 2*results_setosa[3] / (2*results_setosa[3] + results_setosa[1] + results_setosa[2])
            },
            "Classe 2": {
                "accuracy": (results_versicolor[0] + results_versicolor[3]) / sum(results_versicolor),
                "precision": results_versicolor[3] / (results_versicolor[1] + results_versicolor[3]),
                "sensitivity": results_versicolor[3] / (results_versicolor[2] + results_versicolor[3]),
                "specificity": results_versicolor[0] / (results_versicolor[0] + results_versicolor[1]),
                "f1": 2*results_versicolor[3] / (2*results_versicolor[3] + results_versicolor[1] + results_versicolor[2])
            },
            "Classe 3": {
                "accuracy": (results_virginica[0] + results_virginica[3]) / sum(results_virginica),
                "precision": results_virginica[3] / (results_virginica[1] + results_virginica[3]),
                "sensitivity": results_virginica[3] / (results_virginica[2] + results_virginica[3]),
                "specificity": results_virginica[0] / (results_virginica[0] + results_virginica[1]),
                "f1": 2*results_virginica[3] / (2*results_virginica[3] + results_virginica[1] + results_virginica[2])
            }
        }

        # Adicionando as métricas para o resultado final
        for iris_class in fold_metrics_dict:
            #print(iris_class)
            for key, metric in fold_metrics_dict[iris_class].items():
                #print("{}: {}".format(key, round(metric, 2)))
                general_metrics_dict[iris_class][key] += metric
        
    print()
    # Exibindo a média das métricas após o experimento
    for iris_class in general_metrics_dict:
        print(iris_class)
        for key, metric in general_metrics_dict[iris_class].items():
            output = general_metrics_dict[iris_class][key] = round(general_metrics_dict[iris_class][key] / (5 * 100), 2)
            print("{}: {}".format(key, output, 2))
        print()

if __name__ == "__main__":
    main()
