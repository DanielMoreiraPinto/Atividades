package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph {
	
	// Atributos úteis para os algoritmos
	protected final int WHITE = 0;
	protected final int GREY = 1;
	protected final int BLACK = 2;
	protected int time;
	protected double circumference;
	
	// Classe vértice
	private class Vertex implements Comparable<Vertex> {
		String key;
		LinkedList<Edge> adjs;
		int color;
		Vertex pi;
		int dist;
		double wDist;
		int finishTime;
		
		Vertex(String key) {// Adiciona apenas vértice
			this.key = key;
			this.adjs = new LinkedList<Edge>();
		}
		void addEdge(Vertex dest) {// Adiciona vértice conectado sem peso
			this.adjs.add(new Edge(this, dest));
			if(!directed)
				dest.adjs.add(new Edge(dest, this));
		}
		void addEdge(Vertex dest, Double weight) {// Adiciona vértice conectado com peso
			this.adjs.add(new Edge(this, dest, weight));
			if(!directed)
				dest.adjs.add(new Edge(dest, this, weight));
		}
		@Override
		public int compareTo(Vertex v) {
			if(this.wDist < v.wDist) return -1;
			if(this.wDist > v.wDist) return 1;
			return 0;
		}
	}
	
	// Classe aresta
	private class Edge {
		Vertex orig;
		Vertex dest;
		Double w;
		Edge(Vertex orig, Vertex dest) {// Adiciona aresta sem peso
			this.orig = orig;
			this.dest = dest;
			this.w = 1.0;
		}
		Edge(Vertex orig, Vertex dest, Double w) {// Adiciona aresta com peso
			this.orig = orig;
			this.dest = dest;
			this.w = w;
		}
	}
	
	// Atributos da estrutura do grafo
	protected HashMap<String,Vertex> vertices;
	protected LinkedList<String> keys;
	protected boolean directed;
	
	public Graph(boolean isDirected) {
		this.vertices = new HashMap<String,Vertex>();
		this.keys = new LinkedList<String>();
		this.directed = isDirected;
	}
	
	public void put(String key) {
		if(vertices.containsKey(key)) return;
		Vertex v = new Vertex(key);
		this.vertices.put(key, v);
		this.keys.add(key);
	}
	
	// Verifica se algum dos vértices já existe e faz a inserção / conexão
	public void put(String key, String destination) {
		Vertex v, dest;
		if(!vertices.containsKey(key)) {
			v = new Vertex(key);
			vertices.put(key, v);
			this.keys.add(key);
		} else {
			v = vertices.get(key);
		}
		if(!vertices.containsKey(destination)) {
			dest = new Vertex(destination);
			vertices.put(destination, dest);
			this.keys.add(destination);
		} else {
			dest = vertices.get(destination);
		}
		v.addEdge(dest);
	}
	
	public void put(String key, String destination, double weight) {
		Vertex v, dest;
		if(!vertices.containsKey(key)) {
			v = new Vertex(key);
			vertices.put(key, v);
			this.keys.add(key);
		} else {
			v = vertices.get(key);
		}
		if(!vertices.containsKey(destination)) {
			dest = new Vertex(destination);
			vertices.put(destination, dest);
			this.keys.add(destination);
		} else {
			dest = vertices.get(destination);
		}
		v.addEdge(dest, weight);
	}
	
	// Exibe as listas de adjacências dos vértices
	public void show() {
		if(keys.size() == 0) { 
			System.out.println("Grafo vazio");
			return;
		}
		for(String key : keys) {
			Vertex v = vertices.get(key);
			System.out.println("Lista de adjacências (v : w) do vértice " + v.key);
			String adjsStr = "| ";
			for(Edge edge : v.adjs) {
				adjsStr += (edge.dest.key + " : " + edge.w.toString() + " | ");
			}
			System.out.println(adjsStr);
		}
	}
	
	// Mostra quantas arestas estão entre dois vértices
	public int numEdges(String rootKey, String destKey) {
		
		if(!vertices.containsKey(rootKey)) return -1;
		
		Vertex v, temp;
		for(String key : keys) {
			if(key != rootKey) {
				v = vertices.get(key);
				v.color = WHITE;
				v.dist = Integer.MAX_VALUE;
				v.pi = null;
			}
		}
		
		v = vertices.get(rootKey);
		v.color = GREY;
		v.dist = 0;
		v.pi = null;
		
		LinkedList<Vertex> queue = new LinkedList<Vertex>();
		queue.add(v);
		while(!queue.isEmpty()) {
			v = (Vertex) queue.remove();
			for(Edge edge : v.adjs) {
				temp = edge.dest;
				if(temp.color == WHITE) {
					temp.color = GREY;
					temp.dist = v.dist + 1; // Distância medida em número de arestas
					temp.pi = v;
					// Alteração no algoritmo: ao detectar o vértice desejado, retorna sua distância
					if(temp.key.equals(destKey))
						return temp.dist;
					queue.add(temp);
				}
			}
			v.color = BLACK;
		}
		
		return -1;
	}
	
	// Retorna um caminho entre dois vértices
	public String path(String rootKey, String destKey) {
		
		if(!vertices.containsKey(rootKey)) return "Vértice de origem não encontrado";
		
		Vertex v, temp;
		for(String key : keys) {
			if(key != rootKey) {
				v = vertices.get(key);
				v.color = WHITE;
				v.dist = Integer.MAX_VALUE;
				v.pi = null;
			}
		}
		
		v = vertices.get(rootKey);
		v.color = GREY;
		v.dist = 0;
		v.pi = null;
		
		LinkedList<Vertex> queue = new LinkedList<Vertex>();
		queue.add(v);
		while(!queue.isEmpty()) {
			v = (Vertex) queue.remove();
			for(Edge edge : v.adjs) {
				temp = edge.dest;
				if(temp.color == WHITE) {
					temp.color = GREY;
					temp.dist = v.dist + 1;
					temp.pi = v;
					// Alteração no algoritmo: ao detectar o vértice desejado, preenche uma lista
						// com os vértices que levaram até ele, até chegar na raiz
					if(temp.key.equals(destKey)) {
						LinkedList<Vertex> aux = new LinkedList<Vertex>();
						while(temp != null) {
							aux.addFirst(temp);
							temp = temp.pi;
						}
						// Convertendo a lista em uma string com a chave dos vértices
						String path = ""; 
						for(Vertex pathNode : aux) {
							path += (pathNode.key + " ");
						}
						return path;
					}
					queue.add(temp);
				}
			}
			v.color = BLACK;
		}
		
		return "Destino não encontrado / Caminho inexistente";
	}
	
	// Retorna quais vértices estão a uma dada distância de um vértice
	public String around(String rootKey, int d) {
		
		if(!vertices.containsKey(rootKey)) return "Vértice de origem não encontrado";
		
		Vertex v, temp;
		for(String key : keys) {
			if(key != rootKey) {
				v = vertices.get(key);
				v.color = WHITE;
				v.wDist = Double.MAX_VALUE;
				v.pi = null;
			}
		}
		
		v = vertices.get(rootKey);
		v.color = GREY;
		v.wDist = 0;
		v.pi = null;
		
		LinkedList<Vertex> queue = new LinkedList<Vertex>();
		queue.add(v);
		LinkedList<Vertex> aux = new LinkedList<Vertex>();
		while(!queue.isEmpty()) {
			v = (Vertex) queue.remove();
			for(Edge edge : v.adjs) {
				temp = edge.dest;
				if(temp.color == WHITE) {
					temp.color = GREY;
					temp.wDist = v.wDist + edge.w;
					temp.pi = v;
					// Alteração no algoritmo: enquanto não alcançar a distância desejada,
						// adicionar em uma lista os vértices visitados
					if(temp.wDist <= d) {
						aux.add(temp);
					}
					queue.add(temp);
				}
			}
			v.color = BLACK;
		}
		
		// Transformando a lista de vértices em uma string para retorno
		if(aux.isEmpty()) {
			return "Não há vértices";
		} else {
			String around = ""; 
			for(Vertex pathNode : aux) {
				around += (pathNode.key + " ");
			}
			return around;
		}
		
	}
	
	// Retorna a circunferência do grafo
	public double circumference() {
		
		circumference = Double.MAX_VALUE;
		Vertex v;
		for(String rootKey : keys) {
			for(String key : keys) {
				v = vertices.get(key);
				v.color = WHITE;
				v.pi = null;
				v.wDist = 0.0;
			}
			time = 0;
			
			v = vertices.get(rootKey);
			if(v.color == WHITE)
				dfsVisit(v);
		}
		
		return circumference;
		
	}
	
	private void dfsVisit(Vertex v) {
		
		time++;
		v.dist = time;
		v.color = GREY;
		
		Vertex temp;
		for(Edge edge : v.adjs) {
			temp = edge.dest;
			if(temp.color == WHITE) {
				temp.wDist = v.wDist + edge.w;
				temp.pi = v;
				dfsVisit(temp);
			} else if(!temp.equals(v.pi) 
					&& (temp.wDist + v.wDist + edge.w) < circumference) {
				// Alteração no algoritmo: se é detectado um vértice adjacente já visitado, verifica-se
					// se forma um ciclo menor que o armazenado em circumference
				circumference = temp.wDist + v.wDist + edge.w;
			}
		}
		
		v.color = BLACK;
		time++;
		v.finishTime = time;
		
	}
	
}
