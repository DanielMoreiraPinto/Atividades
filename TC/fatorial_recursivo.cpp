#include <iostream>
using namespace std;

// Máquina:
// V  = NxNxN
// X  = N
// Y  = N
// pi_x : X --> V,   pi_x(a) =  (n1:=a; n2:=1; n3:=1)
// pi_y : V --> Y,   pi_y(n1, n2, n3) =  n2

// PI_F = { F, G} 
// F : V --> V,   F(n1,n2,n3) = (n1,n2,n3+1)
// G : V --> V,   G(n1,n2,n3) = (n1, n2*n3, n3) 

// PI_T =
// T1 : V --> {v,f},  T1(n1,n2,n3) = verd. se (n3 <= n1)
//                                      senão falso

int n1, n2, n3;

#define pi_x  cin  >> n1; n2 = 1; n3 = 1;
#define pi_y  cout << "Fatorial = " << n2 << "\n";

#define F   n3 += 1;
#define G   n2 *= n3;

#define T1  n3 <= n1

// Controle de Fluxo Recursivo:
#define se(t)         if(t)
#define entao
#define senao         else

void r1();

void rn() { ; }
void r3() { F; r1(); }
void r2() { G; r3(); }
void r1() { se(T1) entao r2(); senao rn(); }

int main() {

  pi_x

  r1();

  pi_y

}
