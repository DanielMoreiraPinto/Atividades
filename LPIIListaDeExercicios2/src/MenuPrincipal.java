import java.util.Scanner;

public class MenuPrincipal {

	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);
		Cinema cinema = new Cinema();
		String opc;
		
		do {
		
			System.out.println("1- Compra de ingresso\n"
					+ "2- Ver lugares ocupados\n"
					+ "3- Ver valor vendido\n"
					+ "4- Definir/redefinir valor de ingresso\n"
					+ "5- Sair\n"
					+ "Escolha uma opção: ");
			
			opc = entrada.next();
			entrada.nextLine();
			
			if(opc.equals("1")) {
				int numLugar;
				System.out.println("Lugares padrão: 1 - 200\n"
						+ "Lugares VIP: 201 - 300\n"
						+ "Digite o número da poltrona: ");
				while(!entrada.hasNextInt()) {
					System.out.println("Lugares padrão: 1 - 200\n"
							+ "Lugares VIP: 201 - 300\n"
							+ "Digite o número da poltrona: ");
					entrada.next();
				}
				numLugar = entrada.nextInt();
				
				Ingresso ingresso;
				String tipoIngresso;
				System.out.println("Selecione o tipo de ingresso\n"
						+ "1- Normal\n2- Estudante\n3- VIP\nTipo: ");
				tipoIngresso = entrada.next();
				if (tipoIngresso.equals("1")) {
					ingresso = new IngressoNormal(numLugar);
				} else if (tipoIngresso.equals("2")) {
					ingresso = new IngressoEstudante(numLugar);
				} else if (tipoIngresso.equals("3")) {
					ingresso = new IngressoVip(numLugar);
				} else {
					System.out.println("Opção inválida!");
					continue;
				}
				
				Ingresso comprovante = cinema.comprarIngresso(ingresso);
				if(comprovante != null)
					System.out.println("Compra efetuada na poltrona "
							+ comprovante.getCadeira() + "\n"
							+ "Valor: " + comprovante.getValor());
				else
					System.out.println("Poltrona inválida. Verifique os"
							+ "lugares ocupados e a área VIP!");
				
			} else if (opc.equals("2")) {
				
				System.out.println(cinema.listarLugaresOcupados());
				
			} else if (opc.equals("3")) {
				
				System.out.println(cinema.mostrarValorRecebido());
				
			} else if (opc.equals("4")) {
				
				double novoValor;
				System.out.println("Digite o valor do ingresso: ");
				while(!entrada.hasNextDouble()) {
					System.out.println("Digite o valor do ingresso: ");
					entrada.next();
				}
				novoValor = entrada.nextDouble();
				if(cinema.definirValorDoIngresso(novoValor))
					System.out.println("Valor atualizado.");
				else
					System.out.println("Valor inválido!");
				
			} else if (opc.equals("5")) {
				
				System.out.println("Encerrado!");
				
			} else {
				System.out.println("Opção inválida!");
			}
			
		}while(!opc.equals("5"));

	}

}
