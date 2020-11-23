import java.util.Scanner;
import java.util.InputMismatchException;

public class Questao2 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int[] v = new int[10];
		int next, i=0;
		
		try {
			
			next = input.nextInt();
			while(next != 0) {
				
				v[i] = next;
				i++;
				next = input.nextInt();
				
			}
			
		} catch(InputMismatchException e) {
			
			System.out.println("A entrada só aceita números.");
		
		} catch(ArrayIndexOutOfBoundsException e) {
			
			System.out.println("O vetor acabou.");
			
		} finally {
			
			System.out.println("Vetor: ");
			for(int j=0; j < i; j++) {
				
				System.out.printf(v[j] + " ");
				
			}
			
		}

	}

}
