package code;

import java.util.Arrays;

public class MergeSort {

	private static int cc = 0;// Comparation Counter
	private static int ac = 0;// Assignment Counter 
	private static int mc = 0;//  Movement Counter
	
	// Método principal que recebe o array de dados e um valor de versão merge sort desejada
	@SuppressWarnings("unchecked")
	public static <C extends Comparable<C>> C[] mergeSort(C [] A, int mode) {
		cc = 0;
		ac = 0;
		mc = 0;
		
		// Executa versão do merge sort que elimina a cópia do array temporário para o original no fim do merge
		if(mode == 4) { 
			long startTime = System.nanoTime(); // Marca o momento em que o código abaixo inicia
			
			// Inicialização de Temp como cópia de A, necessário para essa versão
			C [] Temp = (C[]) new Comparable[A.length];
			for(int i=0; i < A.length; i++) { ac++; cc++;
				Temp[i] = A[i]; ac++;
			} ac++; cc++;// Primeira atribuição e comparação final
			A = sortNoCopy(Temp, A, 0, A.length-1);
			
			long endTime = System.nanoTime();// Marca o momento que o código acima finaliza
	        long timeElapsed = endTime - startTime;// Tempo de execução em nanossegundos
	        System.out.println("Ordenação finalizada em " + (timeElapsed/1000) + " microssegundos; atrib: " + ac + "; comp: " + cc + "; mov: " + mc);
			return A;
		}
		
		// Para demais casos o array Temp é inicializado vazio
		C [] Temp = (C[]) new Comparable[A.length]; ac++;
		
		// Versão do merge sort que utiliza insertion sort para (sub)arrays de tamanho <= 15
		if(mode == 2) {
			long startTime = System.nanoTime();
			
			sortInsertion(A, Temp, 0, A.length-1);
			
			long endTime = System.nanoTime();
	        long timeElapsed = endTime - startTime;
	        System.out.println("Ordenação finalizada em " + (timeElapsed/1000) + " microssegundos; atrib: " + ac + "; comp: " + cc + "; mov: " + mc);
			return A;
		}
			
		// Versão do merge sort que pula o merge se notar que o array resultante já está ordenado
		if(mode == 3) {
			long startTime = System.nanoTime();
			
			A = sortAlreadySorted(A, Temp, 0, A.length-1);
			
			long endTime = System.nanoTime();
	        long timeElapsed = endTime - startTime;
	        System.out.println("Ordenação finalizada em " + (timeElapsed/1000) + " microssegundos; atrib: " + ac + "; comp: " + cc + "; mov: " + mc);
			return A;
		}
		
		// Versão TimSort do JDK do Java
		if(mode == 5) {
			long startTime = System.nanoTime();
			
			Arrays.sort(A);
			
			long endTime = System.nanoTime();
	        long timeElapsed = endTime - startTime;
	        System.out.println("Ordenação finalizada em " + (timeElapsed/1000) + " microssegundos");
			return A;
		}
			
		// Versão original do merge sort
		long startTime = System.nanoTime();
		
		A = sort(A, Temp, 0, A.length-1);
		
		long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Ordenação finalizada em " + (timeElapsed/1000) + " microssegundos; atrib: " + ac + "; comp: " + cc + "; mov: " + mc);
		return A;
	}// Método principal ========================================================================
	
	// Sort da versão original do merge sort
	private static <C extends Comparable<C>> C[] sort(C [] A, C [] T, int l, int r) {
		
		int mid;// "Meio"
		
		if(l < r) { 
			mid = (l + r) / 2; ac++;// mid posicionado no meio do vetor
			sort(A, T, l, mid);// Divisão esquerda, meio vira o limite direito
			sort(A, T, mid+1, r);// Divisão da direita, meio vira o limite esquerdo
			merge(A, T, l, mid+1, r);// Chamada do merge
		} cc++; 
		
		return A; // Retorno, relevante na versão sem cópia final
	}
	
	// Sort que utiliza insertion sort para array.length <= 15
	private static <C extends Comparable<C>> C[] sortInsertion(C [] A, C [] T, int l, int r) {
		
		// Se o tamanho do array atual for menor ou igual a 15, insertion sort é utilizado
		if (r <= l + 15 - 1) {
			int counters[] = InsertionSort.insertionSort(A, l, r);
			ac += counters[0];
			cc += counters[1];
			mc += counters[2];
			return A;
		} cc++;
		
		// Merge sort padrão para arrays com mais que 15 elementos
		int mid;
		
		if(l < r) {
			mid = (l + r) / 2; ac++;
			sortInsertion(A, T, l, mid);
			sortInsertion(A, T, mid+1, r);
			merge(A, T, l, mid+1, r);
		} cc++;
		
		return A;
		
	}
	
	// Sort que pula o merge se arrays desse passo já estiverem ordenados entre si
	private static <C extends Comparable<C>> C[] sortAlreadySorted(C [] A, C [] T, int l, int r) {
		
		int mid;
		
		if(l < r) {
			mid = (l + r) / 2; ac++;
			sortAlreadySorted(A, T, l, mid);
			sortAlreadySorted(A, T, mid+1, r);
			
			// Se o último elemento da esquerda (maior do subarray esquerdo) for menor ou igual ao
			// primeiro elemento da direita (menor do subarray direito), estão ordenados, pula-se o merge
			if (A[mid].compareTo(A[mid+1]) <= 0) return A; cc++;
			merge(A, T, l, mid+1, r);
		} cc++;
		
		return A;
	}
	
	// Merge da versão original do merge sort
	private static <C extends Comparable<C>> void merge(C [] A, C [] T, int leftPos, int rightPos, int rightEnd) {
		
		// Idêntico à implementação mergeNoCopy, com exceção dos dois itens comentados
		int leftEnd = rightPos - 1; ac++;
		int tempPos = leftPos; ac++;
		int numElms = rightEnd - leftPos + 1; ac++;// Quantidade de elementos no subarray, necessária para cópia final
		
		while(leftPos <= leftEnd && rightPos <= rightEnd) { cc += 2;
			
			if(A[leftPos].compareTo(A[rightPos]) <= 0) {
				T[tempPos++] = A[leftPos++]; ac += 3;
			} else {
				T[tempPos++] = A[rightPos++]; ac += 3;
			} cc++; mc++;
			
		} cc += 2;
		
		while(leftPos <= leftEnd) { cc++;
			T[tempPos++] = A[leftPos++]; ac += 3; mc++;
		} cc++;
		while(rightPos <= rightEnd) { cc++;
			T[tempPos++] = A[rightPos++]; ac += 3; mc++;
		} cc++;
		
		// Cópia do array temporário, onde estão os valores ordenados, para o array original
		for(int i=0; i < numElms; i++, rightEnd--) { ac++; cc++;
			A[rightEnd] = T[rightEnd]; ac++; mc++;
		} ac++; cc++;
		
	}
	
	// Sort que alterna os papéis de array original e temporário para evitar a cópia final
	private static <C extends Comparable<C>> C[] sortNoCopy(C [] A, C [] T, int l, int r) {
		
		int mid;
		
		if(l < r) { 
			mid = (l + r) / 2; ac++;
			sortNoCopy(T, A, l, mid);// Inversão dos papéis de array original e temporário
			sortNoCopy(T, A, mid+1, r);// Inversão dos papéis de array original e temporário
			mergeNoCopy(A, T, l, mid+1, r);
		} cc++;
		
		return T;// Retorna o temporário por este na verdade ser o original no último passo
	}
	
	// Merge que não realiza a cópia final por considerar a troca de papéis entre A e T a cada passo
	private static <C extends Comparable<C>> void mergeNoCopy(C [] A, C [] T, int leftPos, int rightPos, int rightEnd) {
		
		int leftEnd = rightPos - 1; ac++;// Limite da esquerda uma posição atrás do início da direita
		int tempPos = leftPos; ac++;// Posição no array temporário para onde a ordenação é feita
		
		// Enquanto houver elementos em ambos arrays
		while(leftPos <= leftEnd && rightPos <= rightEnd) { cc += 2;
			
			// Compara se o próximo elemento é o da esquerda ou da direita para cópia ao array temporário
			if(A[leftPos].compareTo(A[rightPos]) <= 0) {
				T[tempPos++] = A[leftPos++]; ac+=3;
			} else {
				T[tempPos++] = A[rightPos++]; ac+=3;
			} cc++; mc++;
			
		} cc += 2;
		
		// Copia o restante do subarray que sobrar após sair do while anterior
		while(leftPos <= leftEnd) { cc++;
			T[tempPos++] = A[leftPos++]; ac+=3; mc++;
		} cc++;
		while(rightPos <= rightEnd) { cc++;
			T[tempPos++] = A[rightPos++]; ac+=3; mc++;
		} cc++;
		
		// Não precisa copiar de volta ao original, no último passo T é o original
	}
	
}
