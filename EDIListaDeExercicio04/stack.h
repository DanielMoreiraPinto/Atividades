#ifndef STACK_H
#define STACK_H

#define TRUE 1
#define FALSE 0

typedef struct _stack_ Stack;

Stack *stkCreate(int maxItems);
int stkPush(Stack *stk, void *item);
void *stkPop(Stack *stk);
void *stkTop(Stack *stk);
int stkIsEmpty(Stack *stk);
int stkDestroy(Stack *stk);

#endif