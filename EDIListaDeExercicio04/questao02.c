#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "stack.h"

#define MAXLENGTH 1010

int main()
{
    Stack *stk;
    char string[MAXLENGTH], stringSize, *character, *aux;
    int i, check, isPalindrome;

    printf("Digite um palindromo: ");
    fgets(string, MAXLENGTH, stdin);
    stringSize = strlen(string);

    stk = stkCreate(stringSize/2);
    if(stk == NULL)
    {
        printf("Erro\n");
        return 0;
    }

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
        if(check)
        {
            i++;
        }
    }

    if(stringSize % 2 == 0)
    {
        i = (stringSize / 2);
    }
    else
    {
        i = (stringSize / 2) + 1;
    }

    isPalindrome = 1;
    while(i < stringSize)
    {
        aux = (char*)stkPop(stk);
        if(strcmp(string[i], *aux) == 1)
        {
            isPalindrome = 0;
            break;
        }
    }

    if(isPalindrome)
    {
        printf("E palindromo\n");
    }
    else
    {
        printf("Nao e palindromo\n");
    }
    

    return 0;
}