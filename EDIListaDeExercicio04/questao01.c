/*
:: Implementacao do programa usuario da pilha que detecta strings xCy. Referente a primeira
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
    int stringSize, i, check, isXcy;

    /* Coletando a string e salvando seu tamanho. Se so vier uma letra, ja retorna falso. */
    printf("Digite uma string: ");
    fgets(string, MAXLENGTH, stdin);
    //fgets enfia o \n na string, logo deve ser descontado do comprimento
    stringSize = strlen(string)-1;
    if(stringSize == 1) 
    {
        printf("Nao e xCy.\n");
        return 0;
    } 

    /* Criando a pilha. Como o programa e simples, se houver problema se roda novamente. */
    stk = stkCreate(stringSize);
    if(stk == NULL)
    {
        printf("Erro\n");
        return 0;
    }
    
    /* Enfia letras na pilha ate chegar na letra 'C' ou no final da string. */
    i = 0;
    while(i < stringSize)
    {
        check = 0;

        character = (char*)malloc(sizeof(char));
        if(character != NULL)
        {
            *character = string[i];
            check = stkPush(stk, character);
        }

        //Se algum push nao for efetuado (stkPush retornou falso), nao faz sentido prosseguir
        if(check == 0)
        {
            printf("Erro de memoria, rode novamente.\n");
            return 0;
        }

        //Verificando se foi encontrado 'C', aproveitando para usar a funcao stkTop()
        character = (char*)stkTop(stk);
        i++;
        if(*character == 'C') break;
    }
    
    //Removendo 'C' e definindo o resultado do programa como true.
    stkPop(stk);
    isXcy = 1;

    /* Iterando pelo restante da string, comparando com os pops da pilha, que, a string sendo
        xCy, saem na mesma ordem que o trecho depois de C na string. Se uma comparacao apontar 
        diferenca, ja se deve encerrar a iteracao. Observa-se que o iterador preservou a
        posicao logo apos aquela na qual encontrou 'C'*/
    while(i < stringSize)
    {
        character = (char*)stkPop(stk);

        if(character != NULL)
        {
            //So podem haver elementos A, B e um unico C na string
            if(string[i] != *character || (string[i] != 'A' && string[i] != 'B'))
            {
                isXcy = 0;
                break;
            }
        }
        else
        {
            //Esse nulo pode ser por ter acabado a pilha mas ainda "ter vetor para ler"
            break;
        }
        i++;
    }

    /* Se ainda ha elementos na pilha ou o iterador nao chegou no final da string, nao e do 
        tipo xCy. */
    if(!stkIsEmpty(stk) || i < stringSize) isXcy = 0;

    /* Aplicando o veredito. */
    if(isXcy)
    {
        printf("E xCy.\n");
    }
    else
    {
        printf("Nao e xCy.\n");
    }
    
    /* Terminando de esvaziar a pilha e destruindo */
    while(!stkIsEmpty)
    {
      stkPop(stk);  
    }
    stkDestroy(stk);

    return 0;
}