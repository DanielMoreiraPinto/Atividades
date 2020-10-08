import java.util.Scanner;

public class MainFile {
	
	public static void valueTable(int number) {
		
		System.out.println("N\tN*10\tN*100\tN*1000\n");
		for(int i=1; i <= number; i++) {
			
			System.out.println(i + "\t" + i*10 + "\t" + i*100 + "\t" + i*1000);
			
		}
		
	}
	
	public static void patterns(int number) {
		
		for(int i=1; i <= number; i++) {
			
			for(int j=1; j <= i; j++) {
				
				System.out.printf("*");
				
			}
			
			System.out.println();
			
		}
		System.out.println();
		for(int i=number; i > 0; i--) {
			
			for(int j=1; j <= i; j++) {
				
				System.out.printf("*");
				
			}
			
			System.out.println();
			
		}
		System.out.println();
		for(int i=0; i < number; i++) {
			
			for(int j=i; j > 0 ; j--) {
				
				System.out.printf(" ");
				
			}
			for(int j=number-i; j > 0; j--) {
				
				System.out.printf("*");
				
			}
			
			System.out.println();
			
		}
		System.out.println();
		for(int i=0; i < number; i++) {
			
			for(int j=0; j < number-i-1 ; j++) {
				
				System.out.printf(" ");
				
			}
			for(int j=0; j <= i; j++) {
				
				System.out.printf("*");
				
			}
			
			System.out.println();
			
		}
		
	}
	
	public static void diamond(int number) {
		
		for(int i=1; i <= number; i+=2) {
				
			for(int j=0; j < (number-i)/2; j++) {
				
				System.out.printf(" ");
				
			}
			for(int j=i; j > 0; j--) {
				
				System.out.printf("*");
				
			}
			System.out.println();
			
		}
		int spaces = 1;
		for(int i=1; i <= number-2; i+=2) {
				
			for(int j=1; j <= spaces; j++) {
				
				System.out.printf(" ");
				
			}
			spaces++;
			for(int j=number-2-i; j >= 0; j--) {
				
				System.out.printf("*");
				
			}
			System.out.println();
			
		}
		
	}

	public static void main(String[] args) {
		

		Scanner input = new Scanner(System.in);
		int number;
		String option="0";
		
		do {
			
			System.out.println("Digite uma opção:\n1- Tabela de valores\n"
					+ "2- Padrões\n3- Losango\n4- Encerrar");
			option = input.next();
			switch (option) {
			
			case "1":
				for(;;) {
					
					System.out.println("Digite um número natural.");
					if(input.hasNextInt()) {
						
						number = input.nextInt();
						if(number >= 0) {
							
							valueTable(number);
							break;
							
						} 
						
					} else {
						
						input.next();
					
					}
						
				}
				break;
				
			case "2":
				for(;;) {
					
					System.out.println("Digite um número natural.");
					if(input.hasNextInt()) {
						
						number = input.nextInt();
						if(number >= 0) {
							
							patterns(number);
							break;
							
						} 
						
					} else {
						
						input.next();
					
					}
						
				}
				break;
				
			case "3":
				for(;;) {
					
					System.out.println("Digite um número natural ímpar de 1 a 19.");
					if(input.hasNextInt()) {
						
						number = input.nextInt();
						if(number >= 1 && number <= 19 && number % 2 != 0) {
							
							diamond(number);
							break;
							
						} 
						
					} else {
						
						input.next();
					
					}
						
				}
				break;
				
			case "4":
				System.out.println("Até a próxima!");
				break;
				
			default:
				System.out.println("Digite uma opção válida.");
				break;
			
			}
			
			System.out.println("\n\n");
			
		} while(!option.equals("4"));
		
		input.close();

	}

}
