#ifndef__COLECAO_H
#define__COLECAO_H

#define TRUE 1
#define FALSE 0

typedef struct _colecao_ Colecao;

Colecao *colCriar(int maxItens);
int colInserir(Colecao *c, void *item);
void *colBuscar(Colecao *c, void *chave);
void *colRetirar(Colecao *c, void *chave);
int colDestruir(Colecao *c);

void *colBuscarPorIndice(Colecao *c, int indice);

#endif