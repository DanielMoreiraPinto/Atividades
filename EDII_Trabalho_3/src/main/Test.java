package main;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int op;
		
		System.out.println("Indique o tipo do grafo: 1 - direcionado | 2 - não direcionado");
		op = input.nextInt();
		
		Graph g;
		
		if(op == 1)
			g = new Graph(true);
		else
			g = new Graph(false);
		
		InputCollector.collectVertices(g);
		System.out.println("Grafo:");
		g.show();		
		System.out.println("Arestas entre os vértices 2 e 3 do grafo:");
		System.out.println(g.numEdges("2", "3"));
		System.out.println("Caminho do vértice 4 para o vértice 2");
		System.out.println(g.path("4", "2"));
		System.out.println("Vértices a uma distância de 3 do vértice 1");
		System.out.println(g.around("1", 3));
		System.out.println("Circumferência do grafo");
		System.out.println(g.circumference());

	}

}
