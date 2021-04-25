package code;

import java.util.Scanner;

public class Test {
	
	// Tamanhos de entrada definidos
	private static final int SMALL_SIZE = 50;
	private static final int MEDIUM_SIZE = 5000;
	private static final int BIG_SIZE = 30000;
	
	// Método main
	public static <C extends Comparable<C>> void main(String[] args) {
		
		// Variáveis para input do usuário
		int opt;// Experimentar ou sair do programa, exibir ou não vetor original e ordenado
		int optVersion;// Versão do merge sort a usar
		int optSize;// Tamanho da entrada a usar
		int optData;// Tipo de dado chave:valor a usar
		Scanner input = new Scanner(System.in);
		
		// Array de dados
		C[] dataArray;
		
		// Loop para experimentos seguidos
		while(true) {
			
			//-----------------Display da sequência de menus-----------------//
			System.out.println("################################################################################");
			System.out.println("Experimento merge sort:  1 > Teste manual     2 > Teste automático     3 > Sair");
			opt = input.nextInt();
			if(opt == 3) break;// Opção "sair"
			
			if(opt == 1) {
				System.out.println("Seleção versão de merge sort a utilizar:\n"
						+ "1 > original\n"
						+ "2 > insertion sort para subarrays pequenos\n"
						+ "3 > pular o merge se array estiver ordenado\n"
						+ "4 > eliminar passo de cópia a array original\n"
						+ "5 > usar a Arrays.sort do Java");
				optVersion = input.nextInt();
				
				System.out.println("Seleção de tamanho de dado a utilizar:\n"
						+ "1 > Pequeno\n"
						+ "2 > Médio\n"
						+ "3 > Grande");
				optSize = input.nextInt();
				
				System.out.println("Seleção de tipo de dado a utilizar:\n"
						+ "1 > Chave Double valor String (11)\n"
						+ "2 > Chave String (11) valor Double\n"
						+ "3 > Chave int valor int[30]");
				optData = input.nextInt();
				
				System.out.println("Exibir array original e ordenado?\n1 > Sim\n2 > Não");
				opt = input.nextInt();
				//-----------------Display da sequência de menus-----------------//
				
				// Seleção do tamanho da entrada com base na opção escolhida
				if(optSize == 1) optSize = SMALL_SIZE;
				else if(optSize == 2) optSize = MEDIUM_SIZE;
				else optSize = BIG_SIZE;
				
				// Coleção dos dados do CSV
				dataArray = InputCollector.collect(optSize, optData);
				
				// Prints opcionais dos dados e ordenação
				if(opt == 1) {
					System.out.println("\n=============Array original=============");
					for(int i=0; i < dataArray.length; i++)
						System.out.println(dataArray[i].toString());
				}
				dataArray = MergeSort.mergeSort(dataArray, optVersion);// Chamada da ordenação sobre o array
				if(opt == 1) {
					System.out.println("\n=============Array ordenado=============");
					for(int i=0; i < dataArray.length; i++)
						System.out.println(dataArray[i].toString());
				}
				
				System.out.println("\n");
			}// if(teste manual)
			
			else {
				
				// Arrays para automatização dos inputs
				int[] sizes = new int[] {0, SMALL_SIZE, MEDIUM_SIZE, BIG_SIZE};
				String[] versions = new String[] {" ", " padrão ", " com insertion sort ", " que checa ordenação nos arrays ", " sem cópia final ao array original ", " arrays.sort() do Java "};
				String[] dataTypes = new String[] {" ", " K: Double, V: String ", " K: String, V: Double ", " K: int, V: int[30] "};
				String[] sizeNames = new String[] {" ", " Pequeno ", " Médio ", " Grande "};
				
				for(int i=1; i <=5; i++) {// Itera pelas versões de merge sort
					optVersion = i;
					System.out.println("Versão" + versions[i]);
					for(int j=1; j <= 3; j++) {// Itera pelos tipos de dados da entrada
						optData = j;
						for(int k=1; k <= 3; k++) {// Itera pelos tamanhos de entrada
							optSize = k;
							dataArray = InputCollector.collect(sizes[optSize], optData);
							System.out.println(dataTypes[optData] + sizeNames[optSize]);
							dataArray = MergeSort.mergeSort(dataArray, optVersion);
						}
						System.out.println();
					}
					System.out.println("==================\n");
				}
				
				optVersion = 1;
				optData = 3;
				optSize = 3;
				dataArray = InputCollector.collect(sizes[optSize], optData);
				System.out.println("Ordenação de array já ordenado" + dataTypes[optData] + sizeNames[optSize]);
				System.out.println("> ordenação primária");
				dataArray = MergeSort.mergeSort(dataArray, optVersion);
				System.out.println("> ordenação de array ordenado pelo método padrão");
				dataArray = MergeSort.mergeSort(dataArray, optVersion);
				optVersion = 3;
				System.out.println("> ordenação de array ordenado pelo método que verifica ordenação");
				dataArray = MergeSort.mergeSort(dataArray, optVersion);
				System.out.println();
				
			}// if(teste automático)
			
		}// while(true)
		
		input.close();
		System.out.println("Programa terminado.");
	}

}
