/*
:: Implementacao do programa usuario da pilha que detecta palindromos. Referente a segunda
    questao da lista de exercicios 04.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "stack.h"

#define MAXLENGTH 1010 //string tera tamanho maximo fixo por constante

int main()
{
    Stack *stk;
    char string[MAXLENGTH], *character;
    int stringSize, i, check, isPalindrome;

    /* Coletando a string e salvando seu tamanho. Se so vier uma letra, ja alega palindromo. */
    printf("Digite um palindromo: ");
    fgets(string, MAXLENGTH, stdin);
    //fgets enfia o \n na string, logo deve ser descontado do comprimento
    stringSize = strlen(string)-1;
    if(stringSize == 1) 
    {
        printf("E palindromo.\n");
        return 0;
    } 

    /* Criando a pilha. Como o programa e simples, se houver problema se roda novamente. */
    stk = stkCreate(stringSize/2);
    if(stk == NULL)
    {
        printf("Erro\n");
        return 0;
    }
    
    /* Enfia letras na pilha ate chegar na metade (arredondada para baixo) da string. Nota-se
        que isso significa nao colocar o caractere central de strings de comprimento impar. */
    i = 0;
    while(i < stringSize/2)
    {
        check = 0;

        character = (char*)malloc(sizeof(char));
        if(character != NULL)
        {
            *character = string[i];
            check = stkPush(stk, character);
        }
        i++;

        //Se algum push nao for efetuado (stkPush retornou falso), nao faz sentido prosseguir
        if(check == 0)
        {
            printf("Erro de memoria, rode novamente.\n");
            return 0;
        }
    }

    /* Posicionando o iterador na posicao correta da string. Se o numero de caracteres e impar,
        como divisao quebrada de inteiro e arredondada para baixo, pula-se uma posicao alem da
        "metade". Dessa forma ignora-se o caractere central, que nao esta na pilha. Tambem se
        esta iniciando a variavel que dara o veredito, como verdadeira. */
    if(stringSize % 2 == 0)
    {
        i = (stringSize / 2);
        
    }
    else
    {
        i = (stringSize/2) + 1;
    }
    isPalindrome = 1;

    /* Iterando pelo restante da string, comparando com os pops da pilha, que, a string sendo
        palindromo, saem na mesma ordem que a segunda metade da string e lida. Se uma
        comparacao apontar diferenca, ja se deve encerrar a iteracao. */
    while(i < stringSize)
    {
        character = (char*)stkPop(stk);
        if(character != NULL)
        {
            if(string[i] != *character)
            {
                isPalindrome = 0;
                break;
            }
        }
        i++;
    }

    /* Aplicando o veredito. */
    if(isPalindrome)
    {
        printf("E palindromo\n");
    }
    else
    {
        printf("Nao e palindromo\n");
    }
    
    /* Terminando de esvaziar a pilha e destruindo */
    while(!stkIsEmpty)
    {
      stkPop(stk);  
    }
    stkDestroy(stk);

    return 0;
}