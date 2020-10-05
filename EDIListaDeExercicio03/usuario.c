#include <stdio.h>
#include <stdlib.h>
#include "colecao.h"

int comparaInt(void *a, void *b)
{
    if(*(int*)a > *(int*)b)
    {
        return 1;
    }
    return 0;
}

int comparaFloat(void *a, void *b)
{
    if(*(float*)a > *(float*)b)
    {
        return 1;
    }
    return 0;
}

int main()
{
    int op, n, i, bool;
    Colecao *c;

    printf("Escolha a opcao de vetor a ordenar:\n  1-Inteiro\n  2-Real\n");
    scanf("%d", &op);
    printf("Defina o tamanho do vetor: ");
    scanf("%d", &n);
    printf("\n");

    c = colCriar(n);
    if(c == NULL)
    {
        printf("Erro de memoria ou entrada invalida");
    }

    printf("Preencha o vetor\n");
    switch(op)
    {
        case 1:
        int *elemp, elem, *auxPtr;

        for(i=0; i < n; i++)
        {
            scanf("%d", &elem);
            elemp[i] = &elem;
            bool = colInserir(c, elemp);

            if(bool == FALSE)
            {
                printf("Erro de insercao");
                return 0;
            }
        }

        bool = meuQsort(c, comparaInt);

        if(bool == FALSE)
        {
            printf("Erro");
            return 0;
        }
        printf("Vetor ordenado: ");
        for(i=0; i < n; i++)
        {
            auxPtr = (int*) colRetirar(c, colBuscarPorIndice(c, i));
            printf("%d ", *auxPtr);
        }
        break; //case 1

        case 2:
        float *elemp, elem, *auxPtr;

        for(i=0; i < n; i++)
        {
            scanf("%f", &elem);
            elemp[i] = &elem;
            bool = colInserir(c, elemp);

            if(bool == FALSE)
            {
                printf("Erro de insercao");
                return 0;
            }
        }

        bool = meuQsort(c, comparaFloat);

        if(bool == FALSE)
        {
            printf("Erro");
            return 0;
        }
        printf("Vetor ordenado: ");
        for(i=0; i < n; i++)
        {
            auxPtr = (float*) colRetirar(c, colBuscarPorIndice(c, i));
            printf("%f ", *auxPtr);
        }
        break; //case 2
        
        default:
        printf("Opcao invalida");
        break; //default
    } //switch(op)

    return 0;
}