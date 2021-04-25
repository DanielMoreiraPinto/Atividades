package code;

public class InsertionSort {
	
	private static int cc = 0;// Comparation Counter
	private static int ac = 0;// Assignment Counter 
	private static int mc = 0;// Movement Counter
	
	// Método principal que executa o insertion sort sobre um array
	// Modificado para receber os limites esquerdo e direito de um subarray, para uso com merge sort
	public static <C extends Comparable<C>> int[] insertionSort(C [] A, int l, int r) {
		cc = 0;
		ac = 0;
		mc = 0;
		int counters[] = new int[3];// Array para retornar contadores para teste
		
		int i, j;
		C key;// Item sendo comparado
		
		// Loop de uma posição além do início (esquerda) até o final (direita)
		for(i = l + 1; i <= r; i++) { ac++; cc++;
			
			key = A[i]; ac++;// Chave recebe o próximo elemento a ser ordenado
			
			// Da chave até o início do array, enquanto houverem elementos maiores que a chave
			// eles são copiados para frente
			j = i - 1; ac++;
			while(j >= l && A[j].compareTo(key) > 0) { cc+=2; 
				A[j+1] = A[j]; ac++; mc++;
				j--; ac++;
			} cc+=2;
			A[j+1] = key; ac++; mc++;// Chave é posicionada assim que achar um elemento menor que si, à frente dele
			
		} ac++; cc++;
		
		counters[0] = ac; 
		counters[1] = cc;
		counters[2] = mc;
		return counters;
	}
	
}
