import pandas as pd
from math import sqrt
from random import seed
import os
from datetime import datetime
import numpy as np
from sklearn.metrics import classification_report, accuracy_score, precision_score, recall_score, f1_score, multilabel_confusion_matrix
from sklearn.model_selection import RepeatedKFold
from tqdm import tqdm

"""
======================================================================================================
O script gera o arquivo conf_matrixes.txt, onde podem ser encontradas as matrizes confusão
de cada iteração do KFold.
O arquivo iris.data deve estar no mesmo diretório desse script.
As métricas finais são exibidas no terminal ao fim das iterações.
===================================================
"""

SETOSA = "Iris-setosa"
VERSICOLOR = "Iris-versicolor"
VIRGINICA = "Iris-virginica"

# Calcula o quadrado da distância entre dois pontos
def calc_distance(p1, p2):
    return np.sum(np.square(p1 - p2))

# A partir dos n neighbors fornecidos, retorna a classe definida
def calc_class(n_neighbors):
    vote_setosa = vote_versicolor = vote_virginica = 0
    # Cada vizinho vota em uma classe
    for neighbor in n_neighbors:
        if neighbor[1] == SETOSA:
            vote_setosa += 1
        elif neighbor[1] == VERSICOLOR:
            vote_versicolor += 1
        elif neighbor[1] == VIRGINICA:
            vote_virginica += 1
    
    # Então é verificada qual foi a classe mais votada
    most_voted = vote_setosa
    winner = SETOSA
    if vote_versicolor > most_voted:
        most_voted = vote_versicolor
        winner = VERSICOLOR
    if vote_virginica > most_voted:
        most_voted = vote_virginica
        winner = VIRGINICA
    
    return winner

# Cria o modelo, calculando os n vizinhos mais próximos de cada elemento e 
# retornando as métricas do teste
def predict(x, y, train, train_labels, k, truth):
    results = []
    for i, instance in enumerate(x):
        n_neighbors = []
        for j, record in enumerate(train):
            if len(n_neighbors) < k:
                # Os primeiros k vizinhos são preenchidos diretamente
                n_neighbors.append((calc_distance(instance, record), train_labels[j]))
                n_neighbors.sort(key = lambda x: x[0])
            else:
                # Um vizinho é substituído ao ser encontrado um mais próximo
                for l, neighbor in enumerate(n_neighbors):
                    if calc_distance(instance, record) < neighbor[0]:
                        n_neighbors.insert(l, (calc_distance(instance, record), train_labels[j]))
                        n_neighbors.pop()
                        break
        
        # É feita a predição com base nos vizinhos definidos
        prediction = calc_class(n_neighbors)
        
        # Os rótulos são simplificados
        if prediction == SETOSA:
            results.append(0)
        elif prediction == VERSICOLOR:
            results.append(1)
        else:
            results.append(2)
    
    # É obtida a matriz confusão para cada classe
    mcm = multilabel_confusion_matrix(truth, results, labels=[0, 1, 2])
    with open("conf_matrixes.txt", "a") as output:
        for item in mcm:
            output.write("{}\n".format(item))
        output.write("-"*20 + "\n")
    # São obtidos os VP, VN, FP e FN de cada classe
    results_setosa = mcm[0].ravel()
    results_versicolor = mcm[1].ravel()
    results_virginica = mcm[2].ravel()
    #As métricas são calculadas para cada classe
    metrics_dict = {
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
    
    report = classification_report(truth, results, target_names=["Setosa", "Versicolor", "Virginica"])
    accuracy = accuracy_score(truth, results)
    precision = precision_score(truth, results, average='micro')
    recall = precision_score(truth, results, average='micro')
    f1 = f1_score(truth, results, average='micro')

    metrics = {
        "report": report,
        "accuracy": accuracy,
        "precision": precision,
        "recall": recall,
        "f1": f1,
        "individual": metrics_dict
    }
    
    return metrics

# Inicializa as variáveis necessárias, prepara os dados, insere no modelo e calcula
# os resultados finais das métricas
def main():
    open("conf_matrixes.txt", "w").close()
    random_state = seed(datetime.now())
    os.system("clear")
    k = 5 # Definindo K
    test_results = {
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

    # Lendo os dados como características e rótulo
    data = pd.read_csv("iris.data", header=None).sample(frac=1).to_numpy()
    X = data[:,:4]
    y = data[:,4:]
    
    # Definindo o Repeated KFold
    rkf = RepeatedKFold(n_splits=5, n_repeats=100, random_state=random_state) 
    print("Iniciando {} iterações".format(5*100))
    for train_index, test_index in tqdm(rkf.split(X)):
        # Obtendo os índices dos conjuntos de treino e teste
        X_train, X_test = X[train_index], X[test_index]
        y_train, y_test = y[train_index], y[test_index]
    
        truth = []
        # Simplificando os rótulos de teste
        for item in y_test:
            if item == SETOSA:
                truth.append(0)
            elif item == VERSICOLOR:
                truth.append(1)
            else:
                truth.append(2)
        # Gerando o modelo
        metrics = predict(X_test, y_test, X_train, y_train, k, truth)
        
        # Somando as métricas para a média ao final
        for iris_class in metrics["individual"]:
            for key, metric in metrics["individual"][iris_class].items():
                test_results[iris_class][key] += metric

    # Calculando e exibindo a média das métricas
    for iris_class in test_results:
        print(iris_class)
        for key, metric in test_results[iris_class].items():
            output = test_results[iris_class][key] = round(test_results[iris_class][key] / (5 * 100), 2)
            print("{}: {}".format(key, output, 2))
        print()


if __name__ == "__main__":
    main()
