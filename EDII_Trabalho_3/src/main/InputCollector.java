package main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class InputCollector {

	public static void collectVertices(Graph g) {
		
		JFrame screen = null;
		
		try {
		
			// Buscando arquivos .txt
			JFileChooser chooser = new JFileChooser();
			FileFilter fileFilter = new FileNameExtensionFilter("Text Files", "txt");
			
			chooser.setFileFilter(fileFilter);
			chooser.addChoosableFileFilter(fileFilter);
			
			// Prompting a busca
			chooser.setDialogTitle("Selecione o arquivo txt");
			screen = new JFrame(""); 
			chooser.showOpenDialog(screen);
			chooser.setVisible(true);
			
			String filePath = chooser.getSelectedFile().getAbsolutePath();
			File inputFile = new File(filePath);
			
			if(inputFile.canRead()) {
				
				Scanner input;
				String line;
				String[] lineItem;
				try {
		
					// Coletando o grafo, a primeira linha é ignorada e as restantes tornam-se os vértices
						// e arestas
					input = new Scanner(inputFile);
					input.nextLine();
					try {
						
						while(input.hasNextLine()) {
							
							line = input.nextLine();
							lineItem = line.split(" ");
							if(lineItem.length == 3) { // Grafo ponderado
								g.put(lineItem[0], lineItem[1], Double.parseDouble(lineItem[2]));
							} else if(lineItem.length == 2) { // Grafo não ponderado
								g.put(lineItem[0], lineItem[1]);
							} else { // Apenas vértice em alguma linha
								g.put(lineItem[0]);
							}
					
						}
						
					} catch(java.util.NoSuchElementException e) {
						
						System.out.println("Leitura do arquivo finalizada.");
						
					} 
					
				} catch (IOException e) {
					e.printStackTrace();
				} 
				
			}
			
		} catch (java.lang.NullPointerException e) {
			System.out.println("Tente novamente e selecione um arquivo.");
		} catch(Exception e) {
			System.out.println("Tente novamente.");
		} finally {
			screen.dispose();
		}
		
	}
	
}
