import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int numero;
		
		System.out.println("Digite um inteiro:");
		
		while(!scan.hasNextInt()) {
			System.out.println("Digite um inteiro:");
			scan.next();
    	}
		numero = scan.nextInt();
		
		for(int i=0; i <= 10; i++) {
			System.out.println(i + " x " + numero + " = " + (i*numero));
		}
		
	}
	
}	
	/*Escreva um programa em Java que utilize laços de repetição (loop) para 
	 * imprimir a tabuada de um número passado pelo usuário. Lembre-se, quanto 
	 * menos laços utilizados, melhor.
	Ex.: Se número inserido pelo usuário for 2, deve mostrar na tela:
      0 x 2 = 0
      1 x 2 = 2
      2 x 2 = 4
      ...
      10 x 2 = 20*/

// abacaxi

/*
 * Classes são o "projeto de objetos", representam elementos do mundo real 
 * generalizados, como a ideia que generaliza aquilo que se assemelha e é 
 * classificada sob ela. Em estrutura, a classe define que atributos os objetos 
 * poderão ter, que métodos eles podem invocar, atributos compartilhados por 
 * todos os objetos, entre outras características. Objetos são criados a partir 
 * das classes, são os reais representantes dos elementos do mundo real. Deixam 
 * de ser genéricos para diferenciarem-se através de seus atributos, interagem 
 * entre si através de seus métodos. São a "concepção da ideia no mundo real".

Como a filosofia que diz que quando um ser humano pensa numa cadeira, por 
exemplo, ele pensa numa cadeira perfeita, que não existe em outro lugar além 
do mundo das ideias. Essa cadeira perfeita seria a classe cadeira. Agora no 
mundo real as cadeiras são semelhantes, mas diferenciáveis, por como parecem 
e como são utilizadas (carteira, cadeira sem braços, cadeira de rodinhas, etc). 
Seriam os objetos.
 */
