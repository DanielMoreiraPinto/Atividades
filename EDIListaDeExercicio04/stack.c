#include <stdlib.h>
#include "stack.h"

typedef struct _stack_
{
    int maxItems;
    int top; //"Aponta" para a ultima posicao ocupada ou a "primeira anterior ao vetor"
    void **items;
}Stack;

/* Criacao usual de colecao de TAD, alocando espaco para a pilha e seu vetor e se protegendo
    de erros de alocacao. */
Stack *stkCreate(int maxItems)
{
    if(maxItems > 0)
    {
        Stack *stk = (Stack *)malloc(sizeof(Stack));
        if(stk != NULL)
        {
            stk->items = (void**)malloc(maxItems * sizeof(void*));
            if(stk->items != NULL)
            {
                return stk;
            }
            free(stk);
        }
    }
    return NULL;
}

/* Se item e pilha nao nulos e ainda havendo espaço na ultima, faz top "apontar" 
    para a proxima posicao do vetor da pilha, esta vazia, e coloca o item nela. */
int stkPush(Stack *stk, void *item)
{
    if(stk != NULL && item != NULL)
    {
        if(stk->top < stk->maxItems-1) 
        {
            /*maxItems marca a primeira posicao fora do vetor da pilha, logo, e preciso ser
                capaz de incrementar top sem alcancar maxItems antes de colocar o
                item. */

            stk->top++;
            stk->items[stk->top] = item;
            return TRUE;
        }
    }
    return FALSE;
}

/* Se pilha nao nula e ainda nao vazia, recupera o item no topo para retornar
    e decrementa top, pois o item anterior ao removido e o novo topo da pilha. */
void *stkPop(Stack *stk)
{
    void *popped;

    if(stk != NULL)
    {
        if(stk->top > -1)
        {
            popped = stk->items[stk->top];
            stk->top--;
            return popped;
        }
    }
    return NULL;
}

/* Se pilha nao nula e com algum item dentro, retorna o item que esta no topo. */
void *stkTop(Stack *stk)
{
    if(stk != NULL)
    {
        if(stk->top > -1)
        {
            return stk->items[stk->top];
        }
    }
    return NULL;
}

/* Se pilha nao nula e com algum item dentro, retorna falso. Se a pilha esta vazia
    ou e nula, retorna verdadeiro. Nulo e vazio, afinal. */
int stkIsEmpty(Stack *stk)
{
    if(stk != NULL)
    {
        if(stk->top > -1)
        {
            return FALSE;
        }
    }
    return TRUE;
}

/* Se pilha nao nula e sem itens dentro, desaloca a memoria do vetor e da struct
    e confirma a destruicao. */
int stkDestroy(Stack *stk)
{
    if(stk != NULL)
    {
        if(stk->top == -1)
        {
            free(stk->items);
            free(stk);
            return TRUE;
        }
    }
    return FALSE;
}