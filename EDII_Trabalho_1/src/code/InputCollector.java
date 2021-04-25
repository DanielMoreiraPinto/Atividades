package code;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import sampleobjects.*;

public class InputCollector {

	// Método que coleta o input do CSV de acordo com o tipo e a quantidade especificados
	@SuppressWarnings({ "resource", "unchecked" })
	public static <C extends Comparable<C>> C[] collect(int size, int type) {
		
		String cwd = System.getProperty("user.dir");
		String data_path = "";
		
		if(cwd.contains("src")) {
			data_path = "../";
		}
		
		// Seleção do tipo de dado
		if(type == 1) data_path += "resources/double_string.csv";// Chave Double valor String
		else if(type == 2) data_path += "resources/string_double.csv";// Chave String valor Double
		else data_path += "resources/int_intarr.csv";// Chave int valor int[30]
		
		File inputFile = new File(data_path);// Arquivo em EDII_Trabalho_1/res
		
		// Array de elementos a retornar
		C[] elements;
		if(type == 1) elements = (C[]) new KDoubleVString[size];
		else if(type == 2) elements = (C[]) new KStringVDouble[size];
		else elements = (C[]) new KIntVIntArr[size];
		
		// Testando presença do arquivo
		if(inputFile.canRead()) {
			
			Scanner input;
			try {
				
				// Scanner no início do CSV
				input = new Scanner(inputFile).useDelimiter("\\,|\\n");
				input.useLocale(Locale.US);// Considerando ponto como separador de inteiro-decimal
				int i=0;
				
				try {
					// Lendo todas as linhas do CSV e guardando no objeto de acordo com o tipo
					while(input.hasNext() && i < size) {
						if(type == 1) {
							Double key = input.nextDouble();
							String value = input.next();
							elements[i] = (C) new KDoubleVString(key, value);
						} else if(type == 2) {
							String key = input.next();
							Double value = input.nextDouble();
							elements[i] = (C) new KStringVDouble(key, value);
						} else {
							int key = input.nextInt();
							int[] value = new int[30];
							for(int j=0; j < 30; j++)
								value[j] = input.nextInt();
							elements[i] = (C) new KIntVIntArr(key, value);
						}
						i++;
					}
					input.close();
					return elements;
					
				} catch(java.util.NoSuchElementException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();	
			} 
		} else {
			System.out.println("Problema com o arquivo.");
		}
		
		return null;
	}// collect()
	
	
}
