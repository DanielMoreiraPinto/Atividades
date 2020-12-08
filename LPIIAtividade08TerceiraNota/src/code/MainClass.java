package code;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainClass {
	
	private static ArrayList<Candidato> candidatos;

	public static void main(String[] args) {
		
		try {
		
			JFileChooser chooser = new JFileChooser();
			
			FileFilter fileFilter = new FileNameExtensionFilter("Text Files", "txt");
			
			chooser.setFileFilter(fileFilter);
			chooser.addChoosableFileFilter(fileFilter);
			
			chooser.setDialogTitle("Selecione o arquivo");
			chooser.showOpenDialog(new JFrame(""));
			chooser.setVisible(true);
			
			String filePath = chooser.getSelectedFile().getAbsolutePath();
			File inputFile = new File(filePath);
			
			candidatos = new ArrayList<Candidato>();
			if(inputFile.canRead()) {
				
				Scanner input;
				try {
		
					// O \r está sendo identificado como nova linha neste caso em vez do \n
					input = new Scanner(inputFile).useDelimiter("\\;|\\r");
					
					int i=0;
					
					try {
						
						while(input.hasNext()) {
							
							String inscricao = input.next();
							String nome = input.next();
							int idade = input.nextInt();
							int nota = input.nextInt();
							Candidato candidato = new Candidato(inscricao, nome, idade, nota);
							candidatos.add(candidato);
							i++;
					
						}
						
					} catch(java.util.NoSuchElementException e) {
						
						System.out.println("Leitura do arquivo finalizada. "
								+ "Participantes lidos: " + i);
						
					} finally {
						
						// Evitando alterar o array original, (supondo boa prática)
						ArrayList<Candidato> toWrite = classifica();
						if(grava()) {
							System.out.println("Classificação salva!");
						} else {
							System.out.println("Erro, tente novamente.");
						}
						
					}
					
				} catch (IOException e) {
					
					e.printStackTrace();
					
				} 
				
			}
			
		} catch (java.lang.NullPointerException e) {
			
			e.printStackTrace();
			
		}

	}
	
	public static ArrayList<Candidato> classifica() {
		
		ArrayList<Candidato> sortedCandidatos = new ArrayList<Candidato>(candidatos);
		
		Collections.sort(sortedCandidatos, new CandidatoComparator());
		
		if(sortedCandidatos.size() >= 100)
			sortedCandidatos.subList(100, sortedCandidatos.size()).clear();
		
		int posicao=1;
		for(Candidato candidato : sortedCandidatos) {
			System.out.println(posicao + "° " + candidato.toString());
			posicao++;
		}
		return sortedCandidatos;
		
	}
	
	public static boolean grava() {
		
		
		
	}

}
