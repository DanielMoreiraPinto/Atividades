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
        case 1: //opcao int
        printf("");
        int *elemPInt, elemInt, *auxPtrInt;

        //coleta o vetor
        for(i=0; i < n; i++)
        {
            scanf("%d", &elemInt);
            elemPInt = &elemInt;
            bool = colInserir(c, elemPInt);

            if(bool == FALSE)
            {
                printf("Erro de insercao");
                return 0;
            }
        }

        //processa a colecao pela funcao
        bool = meuQsort(c, comparaInt);
        if(bool == FALSE)
        {
            printf("Erro");
            return 0;
        }

        //exibe o vetor, ja esvaziando
        printf("Vetor ordenado: ");
        for(i=0; i < n; i++)
        {
            auxPtrInt = (int*) colRetirar(c, colBuscarPorIndice(c, 0));
            if(auxPtrInt != NULL)
            {
                printf("%d ", *auxPtrInt);
            }
        }
        break; //case 1

        case 2: //opcao float
        printf("");
        float *elemPFloat, elemFloat, *auxPtrFloat;

        //coleta o vetor
        for(i=0; i < n; i++)
        {
            scanf("%f", &elemFloat);
            elemPFloat = &elemFloat;
            bool = colInserir(c, elemPFloat);

            if(bool == FALSE)
            {
                printf("Erro de insercao");
                return 0;
            }
        }

        //processa a colecao pela funcao
        bool = meuQsort(c, comparaFloat);
        if(bool == FALSE)
        {
            printf("Erro");
            return 0;
        }

        //exibe o vetor, ja esvaziando
        printf("Vetor ordenado: ");
        for(i=0; i < n; i++)
        {
            auxPtrFloat = (float*) colRetirar(c, colBuscarPorIndice(c, 0));
            if(auxPtrFloat != NULL)
            {
                printf("%f ", *auxPtrFloat);
            }
        }
        break; //case 2
        
        default: //opcao invalida
        printf("Opcao invalida");
        break; //default
    } //switch(op)

    colDestruir(c);

    return 0;
}