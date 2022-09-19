#include <stdio.h>
#include <iostream>
#include <cstring>
using namespace std;

// Máquina NORMA
// ----------------------------------------------------
// Memória: com 256 registradores
#define  INF    256
unsigned long R[INF];  //

// funções de entrada/saída:
#define ent       memset(R, 0, sizeof (R)),\
                  cout << "X = ", cin  >> R['X']
#define sai       cout << "Y = ", cout << R['Y'] << "\n"

// conjunto de operações:
#define inc(K)    R[K] += 1
#define dec(K)    R[K] -= R[K] > 0 ? 1 : 0
//#define set(K,n)  R[K] = n
//#define mov(A,B)  R[A] = R[B]

// conjunto de testes:
#define zero(K)   R[K] == 0

// ----------------------------------------------------
// funções auxilares:
void trace(const char* inst) {
  cout << inst << '\n';
  cout << "R[] = { ...";
  for (int i = 0; i < INF; i++)
    if ( i >= 'A' && i <= 'Z' )
      cout << ", " << char(i) << ':' << R[i];
    else if ( R[i] > 0)
      cout << ", " << i << ':' << R[i];
  cout << ", ... }\n";
}

// ----------------------------------------------------
// Sintaxe: Programa Monolítico:
// ----------------------------------------------------
// #define faca(op)     op;
// #define va_para(r)  goto r;
// #define se(t)       if(t)
// #define entao
// #define senao       else

// ----------------------------------------------------
// Sintaxe: Programa Iterativo:
// ----------------------------------------------------
#define se(t)         if(t)
#define entao
#define senao         else
#define enquanto(t)   while(t)
#define faca
#define ate(t)        while(!t)

#define falso         return false;
#define verdadeiro    return true;

// ----------------------------------------------------
// macros
// ----------------------------------------------------
#define usando

// macro: K = 0
void set0(char A) {
    ate(zero(A)) faca {
        dec(A);
    }
}

//  A = 1, ou de modo geral A = n
void set1(char A) {
    set0(A), inc(A);
}

void set2(char A) {
    set1(A), inc(A);
}

void set3(char A) {
    set2(A), inc(A);
}

void set4(char A) {
    set3(A), inc(A);
}

// ...

//#define setn(A, n)   set#n(A)
void set(char A, int n) {
    R[A] = n;
}

// macro :  A = A + B
void add(char A, char B, usando char C) {
    set0(C);
    ate ( zero(B) ) faca {
        inc(A);
        dec(B);
        inc(C);
    }
    ate ( zero(C) ) faca {
        dec(C);
        inc(B);
    }
}

// macro :  A = A - B
void sub(char A, char B, usando char C) {
    set0(C);
    ate ( zero(B) ) faca {
        dec(A);
        dec(B);
        inc(C);
    }
    ate ( zero(C) ) faca {
        dec(C);
        inc(B);
    }
}

// macro:  A = B
void mov(char A, char B, usando char C) {
    set0(A);
    add(A, B, usando C);
}

// macro: A = A x B
void mul(char A, char B, usando char C, char D) {
    set0(C);
    ate ( zero(A) ) faca {
        inc(C);
        dec(A);
    }
    ate ( zero(C) ) faca {
        add(A,B, usando D);
        dec(C);
    }
}

bool teste_mod(char A, char C) {
    return R[A] % R[C] == 0;
}

bool teste_primo(char A, usando char C, char D) {
    se ( zero(A) )
    entao falso
    senao {
        mov(C, A, usando D);
        dec(C);

        se ( zero(C) )
        entao verdadeiro
        senao {
            ate ( teste_mod(A,C) )
            faca dec(C);

            dec(C);

            se ( zero(C) )
            entao verdadeiro
            senao falso
        }
    }
}

// macro:  A = primo(B)
void primo(char A, char B, usando char C, char D, char E) {
    set(A, 1);
    mov(D, B, usando C);
    ate ( zero(D) ) faca {
        dec(D);
        inc(A);
        ate ( teste_primo(A, usando C, E) )
        faca inc(A);
    }
}

// 4.15 letra A -> macro :  A = B - C
void sub2(char A, char B, char C, usando char D) {
    set0(A);
    set0(D);
    ate ( zero(B) ) faca {
        inc(A);
        dec(B);
        inc(D);
    }
    ate ( zero(D) ) faca {
        dec(D);
        inc(B);
    }
    ate ( zero(C) ) faca {
        dec(A);
        dec(C);
        inc(D);
    }
    ate ( zero(D) ) faca {
        dec(D);
        inc(C);
    }
}

// 4.15 letra B -> macro :  A = B / C
void div(char A, char B, char C, usando char D, char E) {
    set0(A);
    set0(E);
    mov(D, B, usando E);
    ate ( zero(D) ) faca {
        inc(A);
        sub(D, C, usando E);
    }
}

// 4.15 letra C -> macro :  A = A!
void fat(char A, usando char B, char C, char D) {
    set0(C);
    set0(D);
    mov(B, A, usando C);
    set1(A);
    ate ( zero(B) ) faca {
        mul(A, B, usando C, D);
        dec(B);
  }
}

// 4.15 letra D -> macro :  A = A^B
void pot(char A, char B, usando char C, char D, char E, char F) {
    set0(D);
    set0(E);
    mov(C, B, usando D);
    dec(C);
    mov(F, A, usando D);
    ate ( zero(C) ) faca {
        mul(A, F, usando D, E);
        dec(C);
    }
}

// 4.15 letra E -> macro :  A > B
bool maior(char A, char B, usando char C, char D, char E) {
    set0(C);
    mov(D, A, usando C);
    mov(E, B, usando C);
    ate ( zero(D) ) faca {
        dec(E);
        dec(D);
        se ( zero(E) )
            se ( zero(D) )
            entao falso
            senao verdadeiro
    }
    falso
}

// 4.15 letra F -> macro :  A >= B
bool maior_igual(char A, char B, usando char C, char D, char E) {
    set0(C);
    mov(D, A, usando C);
    mov(E, B, usando C);
    ate ( zero(D) ) faca {
        dec(E);
        dec(D);
        se ( zero(E) )
            entao verdadeiro
    }
    falso
}

// 4.15 letra G -> macro :  A <= B
bool menor_igual(char A, char B, usando char C, char D, char E) {
    set0(C);
    mov(D, A, usando C);
    mov(E, B, usando C);
    ate ( zero(E) ) faca {
        dec(E);
        dec(D);
        se ( zero(D) )
            entao verdadeiro
    }
    falso
}

// macro :  A != B
bool diferente(char A, char B, usando char C, char D, char E) {
    set0(C);
    mov(D, A, usando C);
    mov(E, B, usando C);
    ate ( zero(D) ) faca {
        dec(E);
        dec(D);
        se ( zero(E) )
        entao se ( zero(D) )
            entao falso
            senao verdadeiro
    }
    verdadeiro
}

// 4.15 letra H -> macro :  A = mdc(A,B)
void mdc(char A, char B, usando char C, char D, char E, char F, char G, char H) {
    set0(C);
    set0(F);
    set0(G);
    set0(H);
    mov(D, A, usando C);
    mov(E, B, usando C);
    enquanto ( diferente(D, E, usando F, G, H) ) faca {
        set0(F);
        set0(G);
        set0(H);
        se ( maior(D, E, F, G, H) )
        entao sub(D, E, usando C);
        senao sub(E, D, usando C);
    }
    mov(A, D, usando C);
}

// 4.15 letra I -> macro :  A = A % B
void mod(char A, char B, usando char C, char D, char E, char F, char G, char H) {
    set0(C);
    set0(G);
    set0(D);
    set1(F);
    set0(E);
    enquanto ( menor_igual(E, A, usando C, D, G) ) faca {
        mul(B, F, usando C, D);
        mov(E, B, usando C);
        div(B, E, F, usando C, D);
        inc(F);
    }
    sub(E, B, usando C);
    sub(A, E, usando C);
}

// 4.15 letra J -> macro :  move para C primos entre A e B
void primos(char A, char B, usando char C, char D, char E, char F) {
    set0(C);
    set0(D);
    set0(E);
    set0(F);
    mov(C, A, usando D);
    enquanto ( menor_igual(C, B, usando D, E, F) ) faca {
        se ( teste_primo(C, usando D, E) )
        entao trace("mod -> macro :  move para C primos entre A e B");
        inc(C);
    }
}

// 4.15 letra K -> macro : Y = A é perfeito
bool teste_nperf(char A, usando char B, char C, char D, char E, char F, char G, char H) {
    se ( zero(A) )
    entao falso
    senao
        set0(C);
        set0(D);
        set0(E);
        set2(G);
        div(F, A, G, usando C, D);
        set0(G);
        set1(H);
        enquanto ( menor_igual(H, F, usando C, D, E) ) faca {
            se ( teste_mod(A, H) )
            entao add(G, H, usando C);
            inc(H);
        }
        se ( menor_igual(G, A, usando C, D, E) )
        entao
            se ( maior_igual(G, A, usando C, D, E) )
            entao verdadeiro
            senao falso
        senao falso
}

int main() {
    ent, trace("ent : N -> R");
    // programa

    // Macro auxiliar sub, inicializando manualmente os registradores
    set('A', 5);
    set('B', 2);
    trace("set('A', 5), set('B', 2)");
    sub('A', 'B', 'C');
    trace("sub -> macro auxiliar :  A = A - B");
    cout << "------------------------------------------------------" << endl;

    // 4.15 letra A, inicializando manualmente os registradores
    set('B', 5);
    set('C', 2);
    trace("set('B', 5), set('C', 2)");
    sub2('A', 'B', 'C', 'D');
    trace("sub2 -> macro :  A = B - C");
    cout << "------------------------------------------------------" << endl;

    // 4.15 letra B, inicializando manualmente os registradores
    set('B', 25);
    set('C', 5);
    trace("set('B', 25), set('C', 5)");
    div('A', 'B', 'C', 'D', 'E');
    trace("div -> macro :  A = B / C");
    cout << "------------------------------------------------------" << endl;

    // 4.15 letra C, inicializando manualmente o registrador
    set('A', 4);
    trace("set('A', 4)");
    fat('A', 'B', 'C', 'D');
    trace("fat -> macro :  A = A!");
    cout << "------------------------------------------------------" << endl;

    // 4.15 letra D, inicializando manualmente os registradores
    set('A', 5);
    set('B', 2);
    trace("set('A', 5), set('B', 2)");
    pot('A', 'B', 'C', 'D', 'E', 'F');
    trace("pot -> macro :  A = A^B");
    cout << "------------------------------------------------------" << endl;

    // 4.15 letra E, inicializando manualmente os registradores
    set('A', 5);
    set('B', 5);
    trace("set('A', 5), set('B', 5)");
    set('Y', maior('A', 'B', 'C', 'D', 'E')); 
    trace("maior -> macro : Y = A > B");
    cout << "------------------------------------------------------" << endl;

    // 4.15 letra F, inicializando manualmente os registradores
    set('A', 5);
    set('B', 5);
    trace("set('A', 5), set('B', 5)");
    set('Y', maior_igual('A', 'B', 'C', 'D', 'E')); 
    trace("maior_igual -> macro : Y = A >= B");
    cout << "------------------------------------------------------" << endl;

    // 4.15 letra G, inicializando manualmente os registradores
    set('A', 5);
    set('B', 6);
    trace("set('A', 5), set('B', 6)");
    set('Y', menor_igual('A', 'B', 'C', 'D', 'E')); 
    trace("menor_igual -> macro : Y = A <= B");
    cout << "------------------------------------------------------" << endl;

    // Macro auxiliar diferente, inicializando manualmente os registradores
    set('A', 5);
    set('B', 5);
    trace("set('A', 5), set('B', 5)");
    set('Y', diferente('A', 'B', 'C', 'D', 'E')); 
    trace("diferente -> macro auxiliar :  Y = A != B");
    cout << "------------------------------------------------------" << endl;

    // 4.15 letra H, inicializando manualmente os registradores
    set('A', 76);
    set('B', 16);
    trace("set('A', 76), set('B', 16)");
    mdc('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'); 
    trace("mdc -> macro :  A = mdc(A,B)");
    cout << "------------------------------------------------------" << endl;

    // 4.15 letra I, inicializando manualmente os registradores
    set('A', 16);
    set('B', 7);
    trace("set('A', 16), set('B', 7)");
    mod('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H');
    trace("mod -> macro :  A = A % B");
    cout << "------------------------------------------------------" << endl;

    // 4.15 letra J, inicializando manualmente os registradores
    set('A', 1);
    set('B', 8);
    trace("set('A', 1), set('B', 8)");
    primos('A', 'B', 'C', 'D', 'E', 'F');
    cout << "------------------------------------------------------" << endl;

    // 4.15 letra K, inicializando manualmente o registrador
    set('A', 6);
    trace("set('A', 6)");
    set('Y', teste_nperf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H')); 
    trace("teste_nperf -> macro : Y = A é perfeito");
    cout << "------------------------------------------------------" << endl;

  sai;
}
