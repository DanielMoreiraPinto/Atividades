import java.util.Scanner;

public class Teclado {
	
	Scanner input = new Scanner(System.in);
	
	public String lerString() {
    	return input.next();
    }
	
	public String lerStringVazioPermitido() {
		input.nextLine();
		return input.nextLine();
	}
	
    public int lerInteiro() {
    	while(!input.hasNextInt()) {
    		System.err.println(input.next() + " não é válido!");
    		System.out.println("Digite um número inteiro: ");
    	}
    	return input.nextInt();
    }	

    public float lerFloat() {
    	while(!input.hasNextFloat()) {
    		System.err.println(input.next() + " não é válido!");
    		System.out.println("Digite um número real: ");
    	}
    	return input.nextFloat();
    }
    
}