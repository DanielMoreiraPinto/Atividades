#include <iostream>
using namespace std;

// Máquina:
// V  = RxRxR
// X  = R
// Y  = R
// pi_x : X --> V,   pi_x(a) =  (n1:=a; n2:=n1; n3:=1)
// pi_y : V --> Y,   pi_y(n1, n2, n3) =  n2

// PI_F = { F, G} 
// F : V --> V,   F(n1,n2,n3) = (n1, (n2+n3)/2, n3)
// G : V --> V,   G(n1,n2,n3) = (n1, n2, n1/n2) 

// PI_T =
// T1 : V --> {v,f},  T1(n1,n2,n3) = verd. se ((n2-n3)/2 > 0.000001)
//                                      senão falso

float n1, n2, n3;

#define pi_x  cout << "Enter the number: "; cin  >> n1; n2 = n1; n3 = 1;
#define pi_y  cout << "square root is: " << n2 << "\n";

#define F   n2 = (n2+n3)/2;
#define G   n3 = n1/n2;

#define T1  (n2-n3) > 0.000001


// Controle de fluxo Iterativo:
#define se(t)         if(t)
#define entao
#define senao         else

#define enquanto(t)   while(t)
#define faca
#define ate(t)        while(!t)

int main() {

  pi_x


  enquanto (T1) faca {
    F
    G
  }

  pi_y

}
