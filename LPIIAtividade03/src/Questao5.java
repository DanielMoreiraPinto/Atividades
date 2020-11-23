import java.util.Scanner;
import java.util.InputMismatchException;

public class Questao5 {
	
	public static void main(String[] args) {
		
		ContaBancaria conta = new ContaBancaria(1500, 500);
		String opc="0";
		Scanner input = new Scanner(System.in);
		double valor;
		
		while(!opc.equals("3")) {
			
			System.out.println("Menu:\n1-Sacar\n2-Depositar\n"
					+ "3-Sair\n");
			
			opc = input.next();
			if(opc.equals("1")) {
				
				try {
					
					valor = input.nextDouble();
					conta.sacar(valor);
				
				} catch(InputMismatchException e) {
					
					System.out.println("Valor inválido.");
					
				} catch(ContaException e) {
					
					System.out.println(e.getMessage());
					
				} finally {
					
					System.out.println("Você tem R$" + conta.getSaldo()
							+ " e " + conta.getLimite() + " de limite.");
					
				}
				
			} else if(opc.equals("2")) {
				
				try {
					
					valor = input.nextDouble();
					conta.depositar(valor);
				
				} catch(InputMismatchException e) {
					
					System.out.println("Valor inválido.");
					
				} catch(ContaException e) {
					
					System.out.println(e.getMessage());
					
				} finally {
					
					System.out.println("Você tem R$" + conta.getSaldo()
							+ " e " + conta.getLimite() + " de limite.");
					
				}
				
			} else if(opc.equals("3")) {
			
				System.out.println("Até a próxima!");
				
			} else {
				
				System.out.println("Opção inválida!");
			
			}
			
		}
		
	}

}
