import java.util.Scanner;
import java.util.InputMismatchException;

public class Questao1 {
	
	//Como estou usando double, é necessária uma função para jogar a
	//exceção aritmética, ou irei obter NaN ou Infinity da divisão.
	public static double divide(double numX, double numY) throws ArithmeticException {
		
		if(numY == 0)
			throw new ArithmeticException("Divisão por zero.");
		return numX / numY;
		
	}

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		double numX, numY, result=0;
		
		try {
			
			numX = input.nextFloat();
			numY = input.nextFloat();
			result = divide(numX, numY);
			
		} catch(InputMismatchException e) {
			
			System.out.println("Erro de entrada! Impossível calcular.");
			result = 0;
			
		} catch(ArithmeticException e) {
			
			System.out.println("Erro de aritmética! " + e.getMessage());
			result = 0;
			
		} finally {
			
			System.out.println("O resultado ficou " + result);
			
		}

	}

}
