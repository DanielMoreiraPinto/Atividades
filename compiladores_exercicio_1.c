#include <stdio.h>
#include <stdlib.h>

#define LETRA 256
#define NUMERO 257

int isLetra(char c)
{
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
}

int isDigito(char c)
{
    return (c >= '0' && c <= '9');
}

int main()
{
    char entrada[110], lexema[110];
    int i, j, estado;

    printf("Digite um identificador: ");
    scanf("%s", entrada);
    
    estado = 1;
    i=0;
    j=0;
    while(estado == 1 || estado == 2)
    {
        switch(estado)
        {
            case 1:
            if(isLetra(entrada[i]))
            {
                lexema[j++] = entrada[i++];
                estado = 2;
            }
            else
            {
                i++;
            }
            break;

            case 2:
            if(isLetra(entrada[i]) || isDigito(entrada[i]))
            {
                lexema[j++] = entrada[i++];
                estado = 2;
            }
            else
            {
                lexema[j] = '\0';
                estado = 3;
            }
            break;
        }
    }
    if(estado == 3)
    {
        printf("Identificador aceito, lexema: %s\n", lexema);
    }
    else
    {
        printf("Erro léxico, identificador não aceito\n");
    }

    return 0;
}