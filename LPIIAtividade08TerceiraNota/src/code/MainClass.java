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
		
		JFrame screen = null; // Para poder dar dispose() no final
		
		try {
		
			JFileChooser chooser = new JFileChooser();
			
			FileFilter fileFilter = new FileNameExtensionFilter("Text Files", "txt");
			
			chooser.setFileFilter(fileFilter);
			chooser.addChoosableFileFilter(fileFilter);
			
			chooser.setDialogTitle("Selecione o arquivo");
			screen = new JFrame(""); 
			chooser.showOpenDialog(screen);
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
							
							String inscricao = input.next().replaceAll("\n", ""); // Vem uma quebra de linha antes da inscrição dos candidatos
							String nome = input.next();
							int idade = input.nextInt();
							int nota = input.nextInt();
							Candidato candidato = new Candidato(inscricao, nome, idade, nota);
							candidatos.add(candidato);
							i++;
					
						}
						
					} catch(java.util.NoSuchElementException e) {
						
						// O arquivo é lido até o final e pode encontrar inconsistências ou reclamar no final
						System.out.println("Leitura do arquivo finalizada. "
								+ "Participantes lidos: " + i);
						// Eclipse diz que InputMismatch já é tratada pela NoSuchElement
						
					} finally {
						
						// Evitando alterar o array original
						ArrayList<Candidato> toWrite = seleciona();
						
						String writtenPath = gravaSelecao(toWrite, inputFile);
						if(writtenPath != null) {
							
							System.out.println("Classificação salva em " + writtenPath);
							
						} else {
							
							System.out.println("Não foi possível salvar a"
									+ "seleção, tente novamente.");
							
						}
						
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
	
	public static ArrayList<Candidato> seleciona() {
		
		ArrayList<Candidato> sortedCandidatos = new ArrayList<Candidato>(candidatos);
		
		Collections.sort(sortedCandidatos, new CandidatoComparator());
		
		if(sortedCandidatos.size() > 0) {
			if(sortedCandidatos.size() >= 100)
				sortedCandidatos.subList(100, sortedCandidatos.size()).clear();
			
			int posicao=1;
			for(Candidato candidato : sortedCandidatos) {
				System.out.println(posicao + "° " + candidato.toString());
				posicao++;
			}
		} else {
			
			System.out.println("Lista de candidatos vazia.");
			
		}
		
		return sortedCandidatos;
		
	}
	
	public static String gravaSelecao(ArrayList<Candidato> data, File source) {
		
		String writePath = source.getParent();
		File outputFile = new File(writePath, "aprovados.txt");

		try {
			
			outputFile.createNewFile();
			if(outputFile.canWrite()) {
				
				PrintWriter output = new PrintWriter(outputFile);
				int i=1;
				for(Candidato candidato : data) {
					
					String colocacao = i + "°";
					String inscricao = candidato.getInscricao();
					String nome = candidato.getNome();
					int idade = candidato.getIdade();
					int nota = candidato.getNota();
					output.print(colocacao + ";");
					output.print(inscricao + ";");
					output.print(nome + ";");
					output.print(idade + ";");
					output.println(nota);
					
					i++;
					
				}
				output.close();
				
				return outputFile.getAbsolutePath();
				
			}
		
		} catch(IOException e) {
			
			System.out.println("Impossível salvar em diretório!");
		
		} catch(Exception e) {
			
			System.out.println("Erro de gravação!");
			
		}
		
		return null;
		
	}

}
