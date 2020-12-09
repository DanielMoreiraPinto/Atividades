/*
a) Imprimir a sequencia de fibonacci a partir de 2 números quaisquer

b) imprimir o maior número dentre 3 quaisquer

c) imprimir a potencia de x ^y quaisquer

d) soma de itens de um array

e) calcula fatorial de um número
*/

#include <stdio.h>
#include <stdlib.h>

// a) Imprimir a sequencia de fibonacci a partir de 2 números quaisquer
/*
int main()
{
  int k=20, i, ant, at, prox;

  scanf("%d %d", &ant, &at);

  printf("%d\n", ant);
  printf("%d\n", at);
  prox = ant + at;
  for(i=0; i < k-2; i++) {
    printf("%d\n", prox);
    ant = at;
    at = prox;
    prox = ant + at;
  }

  return 0;
}
*/

// b) imprimir o maior número dentre 3 quaisquer
/*
int main()
{
    int numX, numY, numZ, maior;

    scanf("%d %d %d", &numX, &numY, &numZ);

    maior = numX;

    if(maior < numY)
        maior = numY;
    if(maior < numZ)
        maior = numZ;

    printf("%d", maior);

    return 0;
}
*/

// c) imprimir a potencia de x ^y quaisquer
/*
int main()
{
    int x, y, i, n;
    float pot;

    scanf("%d %d", &x, &y);

    pot = x;

    n = y;
    if(y < 0)
        n *= -1;

    for(i=0; i < n-1; i++)
    {
        pot *= x;
    }

    if(y < 0)
        pot = 1/pot;

    printf("%f", pot);

    return 0;
}
*/

// d) soma de itens de um array
/*
#define SIZE 5

int main()
{
  int v[SIZE], soma=0, i; // Vetor de tamanho fixado de 5 posicoes

  for(i=0; i < SIZE; i++)
  {
    scanf("%d", &v[i]);
  }

  for(i=0; i < SIZE; i++)
  {
    soma += v[i];
  }

  printf("%d", soma);

  return 0;
}
*/

// e) calcula fatorial de um número
/*
int main()
{
  int num, fat;

  scanf("%d", &num);

  fat = num;
  num--;

  while(num > 1)
  {
    fat *= num;
    num--;
  }

  printf("%d", fat);

  return 0;
}
*/