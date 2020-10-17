/*
:: Implementacao da qDestroy() de um TAD de fila, seguindo implementacao de TAD
    feita em aula. Referente a terceira questao da lista de exercicios 04.
*/

#include <stdlib.h>

#define TRUE 1
#define FALSE 0

typedef struct _query_
{
    int maxItems;
    int front; //Se a fila tem itens, front "aponta" para uma posicao anterior ou igual a rear
    int rear; //rear "aponta" para o ultimo elemento da fila
    void *items;
}Query;

int qDestroy(Query *q)
{
    if(q != NULL)
    {
        if(q->front <= q->rear)
        {
            free(q->items);
            free(q);
            return TRUE;
        }
    }
    return FALSE;
}