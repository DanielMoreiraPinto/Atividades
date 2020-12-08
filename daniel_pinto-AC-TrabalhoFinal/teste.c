#include <stdio.h>

int main()
{
    //int codigoSecreto = 20122020;
    //printf("%d", (codigoSecreto >> 2));
    char vetor[] = {"a%$F@r&L&%qA$#@uM%#@iE$%@tN%$#eG@$#t0$#@u%$@)r)(*&a"};
    int i;
    for(i=0; i < sizeof(vetor); i++) {
        printf("%c", vetor[i<<2]);
    }
}