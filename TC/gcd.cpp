#include <iostream>
using namespace std;

// Máquina:
// V  = NxN
// X  = NxN
// Y  = N
// pi_x : X --> V,   pi_x(a,b) =  (n1:=a; n2:=b;)
// pi_y : V --> Y,   pi_y(n1, n2) =  n1

// PI_F = { F, G}
// F : V --> V,   F(n1,n2) = (n1-n2, n2)
// G : V --> V,   G(n1,n2) = (n1, n2-n1)

// PI_T =
// T1 : V --> {v,f},  T1(n1,n2) = verd. se (n1 != n2)
//                                      senão falso
// T2 : V --> {v,f},  T2(n1,n2) = verd. se (n1 > n2)
//                                      senão falso

int n1, n2;

#define pi_x  cout << "Entrada: "; cin  >> n1 >> n2;
#define pi_y  cout << "Saída = " << n1 << "\n";

#define F   n1 -= n2;
#define G   n2 -= n1;

#define T1  n1 != n2
#define T2  n1 >  n2



// Controle de fluxo Iterativo:
#define se(t)         if(t)
#define entao
#define senao         else

#define enquanto(t)   while(t)
//#define faca
#define ate(t)        while(!t)

// Controle de fluxo Monolítico:
#define faca(op)     op;
#define va_para(r)  goto r;
#define se(t)       if(t)
#define entao
#define senao       else

// Controle de Fluxo Recursivo:

void r1();

void rn() { ; }
void r4() { G; r1(); }
void r3() { F; r1(); }
void r2() { se(T2) entao r3(); senao r4(); }
void r1() { se(T1) entao r2(); senao rn(); }

// Função computada:
//   f : N x N --> N
//   f(n1,n2) = mdc(n1,n2)
int main() {

  pi_x

/*
  while (n1 != n2)
    if ( n1 > n2)
      n1 -= n2;  // n1 = n1 - n2;
    else
      n2 -= n1;  // n2 = n2 - n1;
*/

/*
  enquanto (T1) faca {
    se (T2) entao F senao G
  }
*/

/*
  r1: se(T1) entao va_para(r2) senao va_para(rn)
  r2: se(T2) entao va_para(r3) senao va_para(r4)
  r3: faca(F) va_para(r1)
  r4: faca(G) va_para(r1)
  rn: ;
*/

  r1();


  pi_y

}
