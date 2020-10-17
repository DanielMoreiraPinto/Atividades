import java.util.ArrayList;

public class Canil {
	
	private static ArrayList<Cachorro> cachorros;
	private static Teclado entrada;

	public static void main(String args[]) {
		
		int opcao = 0;
		entrada = new Teclado();
		cachorros = null;
		
		do {
			
			System.out.println("========================================");
			System.out.println("Bem-vindo! O que deseja fazer?\n\n"
					+ "1-Cadastrar cachorro\n"
					+ "2-Listar cachorros abrigados\n"
					+ "3-Registro de adoção\n"
					+ "4-Registro de alimentação\n"
					+ "5-Registro de exercício\n"
					+ "6-Sair\n");
			opcao = entrada.lerInteiro();
			
			switch(opcao) {
			//usadas funcoes para nao ter problema com nomes de variaveis
			case 1:
				cadastrar();
				break;
				
			case 2:
				listar();
				break;
			
			case 3:
				adotar();
				break;
			
			case 4:
				alimentar();
				break;
			
			case 5:
				exercitar();
				break;
			
			case 6:
				System.out.println("Até logo!");
				break;
			
			default:
				System.out.println("Opção inválida.....!!!!");
				break;
			
			}
			
		}while(opcao != 6);
			
	}//main()
	
	private static void cadastrar() {
		
		//Modelo que formata as entradas no terminal
		System.out.print("Nome: ");
		String nome = entrada.lerString();
		System.out.println();
		
		System.out.print("Idade: ");
		int idade = entrada.lerInteiro();
		System.out.println();
		
		System.out.print("Peso: ");
		float peso = entrada.lerFloat();
		System.out.println();
		
		Cachorro cachorro = new Cachorro(nome, idade, peso);
		if(cachorros == null) {
			
			cachorros = new ArrayList<Cachorro>();
			
		}
		cachorros.add(cachorro);
		
	}//cadastrar()
	
	private static void listar() {
		
		if(cachorros != null && !cachorros.isEmpty()) {
			
			System.out.println("Listando cachorros abrigados...\n");
			for(Cachorro cachorro : cachorros) {
				
				if(!cachorro.isAdotado()) {
					
					System.out.println("------------------------");
					System.out.println("Nome: "+ cachorro.getNome() + "\n"
							+ "Idade: " + cachorro.getIdade() + "\n"
							+ "Peso: " + cachorro.getPeso());
					
				}
			
			}
			System.out.println("------------------------\n");
			
		} else {
			
			System.out.println("Não há cachorros abrigados.\n");
			//nao ha aviso quando os cachorros abrigados foram todos adotados
			
		}
		
	}//listar()
	
	private static void adotar() {
		
		//ainda nao estamos trabalhando com id e foi optado usar ArrayList
		if(cachorros != null && !cachorros.isEmpty()) {
			
			System.out.println("Diga o nome do cachorro adotado:");
			String nome = entrada.lerString();
			for(Cachorro cachorro : cachorros) {
				
				if(cachorro.getNome().equals(nome)) {
					
					if(!cachorro.isAdotado()) {
						
						cachorro.setAdotado(true);
						System.out.println("Adoção de "
								+ cachorro.getNome() + " registrada!");
						
					} else {
						
						System.out.println(cachorro.getNome()
								+ " já havia sido adotado.\n");
						
					}
					
					return;
				
				}
			
			}
			System.out.println(nome + " não está nos registros...\n");
			
		} else {
			
			System.out.println("Não há cachorros abrigados.\n");
			
		}
		
	}//adotar()
	
	private static void alimentar() {
		
		if(cachorros != null && !cachorros.isEmpty()) {
		
			System.out.println("Digite a porção individual, em quilos:");
			float quantidade = entrada.lerFloat();
			
			if(quantidade <= 0) {
				
				System.out.println("Valor inválido de quantidade.\n");
				return;
				
			}
			
			System.out.println("Digite o nome do cachorro que foi alimentado ou"
					+ " pressione enter para registrar para todos:");
			String nome = entrada.lerStringVazioPermitido();
			
			if(nome.isEmpty()) {
				
				for(Cachorro cachorro : cachorros) {
					
					if(!cachorro.isAdotado()) {
						
						cachorro.alimentarCachorro(quantidade);
						
					}
					
				}
				return;
					
			} else {
				
				for(Cachorro cachorro : cachorros) {
					/*novamente foi considerada a nao existencia de nomes
					  repetidos, apesar de se usar ArrayList*/
					if(!cachorro.isAdotado() && cachorro.getNome().equals(nome)) {
						
						cachorro.alimentarCachorro(quantidade);
						System.out.println("Registro efetuado.");
						return;
						
					}
					
				}
				
			}
			System.out.println(nome + " não está nos registros...\n");
		
		} else {
			
			System.out.println("Não há cachorros abrigados.\n");
			
		}
			
	}//alimentar()
	
	private static void exercitar() {
		
		//a observacao na funcao anterior se aplica nesta
		if(cachorros != null && !cachorros.isEmpty()) {
		
			System.out.println("Digite a distância individual percorrida,"
					+ " em quilômetros:");
			int distancia = entrada.lerInteiro();
			if(distancia <= 0) {
				
				System.out.println("Valor inválido de distancia.\n");
				return;
				
			}
			
			System.out.println("Digite o nome do cachorro que teve exercício ou"
					+ " pressione enter para registrar para todos:");
			String nome = entrada.lerStringVazioPermitido();
			
			if(nome.isEmpty()) {
				
				for(Cachorro cachorro : cachorros) {
					
					if(!cachorro.isAdotado()) {
						
						cachorro.exercitarCachorro(distancia);
						
					}
					
				}
				return;
					
			} else {
				
				for(Cachorro cachorro : cachorros) {
					
					if(!cachorro.isAdotado() && cachorro.getNome().equals(nome)) {
	
						cachorro.exercitarCachorro(distancia);
						System.out.println("Registro efetuado.");
	
						return;
						
					}
					
				}
				
			}
			System.out.println(nome + " não está nos registros...\n");
			
		} else {
		
			System.out.println("Não há cachorros abrigados.\n");
		
		}
		
	}//exercitar()
	
}
