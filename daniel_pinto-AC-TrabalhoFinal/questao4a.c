/*
a) Imprimir a sequencia de fibonacci a partir de 2 números quaisquer

b) imprimir o maior número dentre 3 quaisquer

c) imprimir a potencia de x ^y quaisquer

d) soma de itens de um array

e) calcula fatorial de um número
*/

#include <stdio.h>
#include <stdlib.h>

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