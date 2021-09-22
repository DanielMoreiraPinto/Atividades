# Treino
import numpy as np
import pandas as pd
from sklearn import svm
from sklearn.datasets import load_iris
from sklearn.model_selection import RepeatedKFold
from sklearn.metrics import classification_report, accuracy_score, precision_score, recall_score, f1_score, multilabel_confusion_matrix
from tqdm import tqdm
from datetime import datetime
from random import seed, shuffle
import os

# Exibição
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D
from sklearn.decomposition import PCA

abspath = os.path.abspath(__file__)
dname = os.path.dirname(abspath)
os.chdir(dname)

def exhibition_dataset():
    # Importando a base
    iris = load_iris()
    X = iris.data[:, :2] # Pegando primeiro duas características
    y = iris.target

    x_min, x_max = X[:, 0].min() - .5, X[:, 0].max() + .5
    y_min, y_max = X[:, 1].min() - .5, X[:, 1].max() + .5

    plt.figure(2, figsize=(8, 6))
    plt.clf()

    # Exibindo a distribuição
    plt.scatter(X[:, 0], X[:, 1], c=y, cmap=plt.cm.coolwarm, edgecolor='k')
    plt.xlabel('Comprimento da sépala')
    plt.ylabel('Largura da sépala')

    plt.xlim(x_min, x_max)
    plt.ylim(y_min, y_max)
    plt.xticks(())
    plt.yticks(())
    plt.savefig('plot2D.png')

    # Agora coletando uma terceira característica para tentar melhorar a divisão
    fig = plt.figure(1, figsize=(8, 6))
    ax = Axes3D(fig, elev=-150, azim=110, auto_add_to_figure=False)
    fig.add_axes(ax)
    X_reduced = PCA(n_components=3).fit_transform(iris.data)
    ax.scatter(X_reduced[:, 0], X_reduced[:, 1], X_reduced[:, 2], c=y,
            cmap=plt.cm.coolwarm, edgecolor='k', s=40)
    ax.set_xlabel("Comprimento da Sépala")
    ax.w_xaxis.set_ticklabels([])
    ax.set_ylabel("Largura da Sépala")
    ax.w_yaxis.set_ticklabels([])
    ax.set_zlabel("Comprimento da Pétala")
    ax.w_zaxis.set_ticklabels([])

    plt.show()

def make_meshgrid(x, y, h=.02):
    x_min, x_max = x.min() - 1, x.max() + 1
    y_min, y_max = y.min() - 1, y.max() + 1
    xx, yy = np.meshgrid(np.arange(x_min, x_max, h), np.arange(y_min, y_max, h))
    return xx, yy
def plot_contours(ax, clf, xx, yy, **params):
    Z = clf.predict(np.c_[xx.ravel(), yy.ravel()])
    Z = Z.reshape(xx.shape)
    out = ax.contourf(xx, yy, Z, **params)
    return out

def exhibition_decision():
    # Coletando duas características
    iris = load_iris()
    X = iris.data[:, :2]
    y = iris.target
    # Treinando o modelo em todo o dataset
    model = svm.SVC(C=10, kernel="rbf")
    clf = model.fit(X, y)
    fig, ax = plt.subplots()
    # Preparando o plot
    X0, X1 = X[:, 0], X[:, 1]
    xx, yy = make_meshgrid(X0, X1)
    plot_contours(ax, clf, xx, yy, cmap=plt.cm.coolwarm, alpha=0.8)
    ax.scatter(X0, X1, c=y, cmap=plt.cm.coolwarm, s=20, edgecolors="k")
    ax.set_ylabel("Comprimento da Sépala")
    ax.set_xlabel("Largura da Sépala")
    ax.set_xticks(())
    ax.set_yticks(())
    plt.savefig('plot2D_decision.png')
    #plt.show()

def main():
    random_state = seed(datetime.now())
    exhibition_dataset()
    exhibition_decision()
    #return
    
    # Carregando e explorando o dataset
    data = load_iris()
    data_df = pd.DataFrame(data['data'], columns=data.feature_names)
    print(data_df.head())
    X = data_df.to_numpy()
    y = data.target

    # Preparando para coletar as métricas do Repeated KFold
    general_metrics_dict = {
        "Setosa": {
            "accuracy": 0,
            "precision": 0,
            "sensitivity": 0,
            "specificity": 0,
            "f1": 0
        },
        "Versicolor": {
           "accuracy": 0,
            "precision": 0,
            "sensitivity": 0,
            "specificity": 0,
            "f1": 0
        },
        "Virginica": {
            "accuracy": 0,
            "precision": 0,
            "sensitivity": 0,
            "specificity": 0,
            "f1": 0
        }
    }
    os.system('clear')
    open("matriz_confusao.txt", "w").close()
    
    # Preparando o Repeated KFold
    rkf = RepeatedKFold(n_splits=5, n_repeats=100, random_state=random_state) 
    rodada = 0
    for train_index, test_index in tqdm(rkf.split(X)):
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
        # Os valores da matriz vem na ordem TN, FP, FN, TP
        results_setosa = mcm[0].ravel()
        results_versicolor = mcm[1].ravel()
        results_virginica = mcm[2].ravel()

        # Calculando as métricas
        fold_metrics_dict = {
            "Setosa": {
                "accuracy": (results_setosa[0] + results_setosa[3]) / sum(results_setosa),
                "precision": results_setosa[3] / (results_setosa[1] + results_setosa[3]),
                "sensitivity": results_setosa[3] / (results_setosa[2] + results_setosa[3]),
                "specificity": results_setosa[0] / (results_setosa[0] + results_setosa[1]),
                "f1": 2*results_setosa[3] / (2*results_setosa[3] + results_setosa[1] + results_setosa[2])
            },
            "Versicolor": {
                "accuracy": (results_versicolor[0] + results_versicolor[3]) / sum(results_versicolor),
                "precision": results_versicolor[3] / (results_versicolor[1] + results_versicolor[3]),
                "sensitivity": results_versicolor[3] / (results_versicolor[2] + results_versicolor[3]),
                "specificity": results_versicolor[0] / (results_versicolor[0] + results_versicolor[1]),
                "f1": 2*results_versicolor[3] / (2*results_versicolor[3] + results_versicolor[1] + results_versicolor[2])
            },
            "Virginica": {
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
