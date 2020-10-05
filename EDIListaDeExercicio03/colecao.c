#include "colecao.h"
#include <stdlib.h>

typedef struct _colecao_
{
    int numItens;
    int maxItens;
    void **itens;
} Colecao;

Colecao *colCriar(int maxItens)
{
    Colecao *c;
    if(maxItens > 0)
    {
        c = (Colecao*) malloc(sizeof(Colecao));
        if(c != NULL)
        {
            c->itens = (void**) malloc(sizeof(void*) * maxItens);
            if(c->itens != NULL)
            {
                c->maxItens = maxItens;
                c->numItens = 0;
                return c;
            }
            free(c);
        }
    }
    return NULL;
}

int colInserir(Colecao *c, void *item)
{
    if(c != NULL && c->numItens < c->maxItens)
    {
        c->itens[c->numItens] = item;
        c->numItens++;
        return TRUE;
    }
    return FALSE;
}

void *colBuscar(Colecao *c, void *chave)
{
    int i;
    if(c != NULL && c->numItens > 0)
    {
        for(i=0; i < c->numItens; i++)
        {
            if(c->itens[i] == chave)
            {
                return c->itens[i];
            }
        }
    }
    return NULL;
}

void *colRetirar(Colecao *c, void *chave)
{
    int i, j;
    void *del;
    if(c != NULL && c->numItens > 0)
    {
        for(i=0; i < c->numItens; i++)
        {
            if(c->itens[i] == chave)
            {
                del = c->itens[i];
                j=i;
                while(j < c->numItens-1)
                {
                    c->itens[j] = c->itens[j+1];
                }
                c->numItens--;    
                return del;
            }
        }
    }
    return NULL;
}

int colDestruir(Colecao *c)
{
    if(c != NULL && c->numItens == 0)
    {
        free(c->itens);
        free(c);
        return TRUE;
    }
    return FALSE;
}

void *colBuscarPorIndice(Colecao *c, int indice)
{
    if(c != NULL && c->numItens > 0 && indice < c->numItens)
    {
        return c->itens[indice];
    }
    return NULL;
}

int meuQsort(Colecao *c, int (*comp)(void *, void *))
{
    if(c != NULL)
    {
        
    }
}