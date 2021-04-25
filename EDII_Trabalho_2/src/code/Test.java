package code;

import hashes.*;
import trees.*;

public class Test {

	private static final int TO_SHOW = 21;
	private static final int SMALL = 100;
	private static final int MEDIUM = 10000;
	private static final int LARGE = 100000;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		
		int[] sizes = new int[] {TO_SHOW, SMALL, MEDIUM, LARGE};
		int rounds = 50;
		double insTime[] = new double[6], getTime[] = new double[6], delTime[] = new double[6], delGetTime[] = new double[6];
		long startTime;
		
		// Experimento para cada tamanho de entrada
		for(int sizeType=0; sizeType < sizes.length; sizeType++) {
			
			Item[] testItems = ItemGenerator.randomItems(LARGE); // Geração dos itens
			
			int qty = sizes[sizeType];
			int qry=qty/4, del=qty/2;
			
			// Estruturas declaradas
			Hash<Integer, Item> h = new Hash();
			ReHash<Integer, Item> rh = new ReHash();
			LLHash<Integer, Item> llh = new LLHash();
			NoDelHash<Integer, Item> ndh = new NoDelHash();
			AVLTree<Integer, Item> avlt = new AVLTree();
			RBTree<Integer, Item> rbt = new RBTree();
			
			for(int i=0; i < 6; i++) {
				insTime[i] = 0;
				getTime[i] = 0;
				delTime[i] = 0;
				delGetTime[i] = 0;
			}
			
			System.out.println("------------------------------");
			System.out.println("Experimento com " + qty + " itens");
			System.out.println("------------------------------\n");
			
			// 50 rodadas de cada experimento para obter a média
			for(int round = (sizeType == 0 ? rounds-1 : 0); round < rounds; round++) {
				
				System.out.println("Rodada " + (round+1) + " $$$$$$$$$$$$$$$$$");
				
				// Inserções
				for(int i=0; i < qty; i++) {
					startTime = System.nanoTime();
					h.put(testItems[i].getId(), testItems[i]);
					insTime[0] += (System.nanoTime() - startTime) / 1000;
					startTime = System.nanoTime();
					rh.put(testItems[i].getId(), testItems[i]);
					insTime[1] += (System.nanoTime() - startTime) / 1000;
					startTime = System.nanoTime();
					llh.put(testItems[i].getId(), testItems[i]);
					insTime[2] += (System.nanoTime() - startTime) / 1000;
					startTime = System.nanoTime();
					ndh.put(testItems[i].getId(), testItems[i]);
					insTime[3] += (System.nanoTime() - startTime) / 1000;
					startTime = System.nanoTime();
					avlt.put(testItems[i].getId(), testItems[i]);
					insTime[4] += (System.nanoTime() - startTime) / 1000;
					startTime = System.nanoTime();
					rbt.insere(testItems[i].getId(), testItems[i]);
					insTime[5] += (System.nanoTime() - startTime) / 1000;
				}
				
				if(qty == TO_SHOW) {
					System.out.println("Estruturas depois de " + qty + " inserções: \n");
					System.out.println("Hash padrão");
					System.out.println(h.toString());
					System.out.println("Hash com rehash");
					System.out.println(rh.toString());
					System.out.println("Hash com lista encadeada");
					System.out.println(llh.toString());
					System.out.println("Hash com deleção preguiçosa");
					System.out.println(ndh.toString());
					System.out.println("Árvore balanceada");
					avlt.view();
					System.out.println();
					System.out.println("Árvore rubro-negra");
					rbt.view();
					System.out.println("===============================\n");
				}
				
				// Funcionalidades especiais da árvore rubro-negra
				if(qty == TO_SHOW) {
					System.out.println("Percentual de nós vermelhos na árvore rubro-negra: " 
							+ (rbt.percentVermelhos() * 100) + "%\n");
					
					RbtToAvl rta = new RbtToAvl();
					AVLTree avltFromRbt = rta.convert(rbt);
					System.out.println("Árvore rubro-negra convertida para balanceada:");
					avltFromRbt.view();
					System.out.println("===============================\n");
				}
				
				// Buscas
				testItems = ItemGenerator.shuffleItems(testItems, qty);
				if(qty == TO_SHOW) {
					System.out.println(qry + " buscas nas estruturas: \n");
					for(int i=0; i < qry; i++) {
						System.out.println("Buscando " + testItems[i].toString());
						System.out.println(h.get(testItems[i].getId()).toString() + " - Hash padrão");
						System.out.println(rh.get(testItems[i].getId()).toString() + " - Hash com rehash");
						System.out.println(llh.get(testItems[i].getId()).toString() + " - Hash com lista encadeada");
						System.out.println(ndh.get(testItems[i].getId()).toString() + " - Hash com deleção preguiçosa");
						System.out.println(avlt.get(testItems[i].getId()).toString() + " - Árvore balanceada");
						System.out.println(rbt.get(testItems[i].getId()).toString() + " - Árvore rubro-negra");
						System.out.println();
					}
					System.out.println("===============================\n");
				} else {
					for(int i=0; i < qry; i++) {
						startTime = System.nanoTime();
						h.get(testItems[i].getId());
						getTime[0] += (System.nanoTime() - startTime) / 1000;
						startTime = System.nanoTime();
						rh.get(testItems[i].getId());
						getTime[1] += (System.nanoTime() - startTime) / 1000;
						startTime = System.nanoTime();
						llh.get(testItems[i].getId());
						getTime[2] += (System.nanoTime() - startTime) / 1000;
						startTime = System.nanoTime();
						ndh.get(testItems[i].getId());
						getTime[3] += (System.nanoTime() - startTime) / 1000;
						startTime = System.nanoTime();
						avlt.get(testItems[i].getId());
						getTime[4] += (System.nanoTime() - startTime) / 1000;
						startTime = System.nanoTime();
						rbt.get(testItems[i].getId());
						getTime[5] += (System.nanoTime() - startTime) / 1000;
					}
				}
				
				// Deleções
				testItems = ItemGenerator.shuffleItems(testItems, qty);
				for(int i=0; i < del; i++) {
					startTime = System.nanoTime();
					h.delete(testItems[i].getId());
					delTime[0] += (System.nanoTime() - startTime) / 1000;
					startTime = System.nanoTime();
					rh.delete(testItems[i].getId());
					delTime[1] += (System.nanoTime() - startTime) / 1000;
					startTime = System.nanoTime();
					llh.delete(testItems[i].getId());
					delTime[2] += (System.nanoTime() - startTime) / 1000;
					startTime = System.nanoTime();
					ndh.delete(testItems[i].getId());
					delTime[3] += (System.nanoTime() - startTime) / 1000;
					startTime = System.nanoTime();
					avlt.delete(testItems[i].getId());
					delTime[4] += (System.nanoTime() - startTime) / 1000;
					//startTime = System.nanoTime();
					//rbt.delete(testItems[i].getId());
					//delTime[5] += (System.nanoTime() - startTime);
				}
				
				if(qty == TO_SHOW) {
					System.out.println("Estruturas depois de " + del + " deleções: \n");
					System.out.println("Hash padrão");
					System.out.println(h.toString());
					System.out.println("Hash com rehash");
					System.out.println(rh.toString());
					System.out.println("Hash com lista encadeada");
					System.out.println(llh.toString());
					System.out.println("Hash com deleção preguiçosa");
					System.out.println(ndh.toString());
					System.out.println("Árvore balanceada");
					avlt.view();
					System.out.println();
					//System.out.println("Árvore rubro-negra");
					//rbt.view();
					System.out.println("===============================\n");
				}
				
				// Buscas após deleções
				testItems = ItemGenerator.shuffleItems(testItems, qty);
				if(qty == TO_SHOW) {
					System.out.println(qry + " buscas nas estruturas após deleções: \n");
					for(int i=0; i < qry; i++) {
						Item hi = h.get(testItems[i].getId()), 
								rhi = rh.get(testItems[i].getId()), 
								llhi = llh.get(testItems[i].getId()), 
								ndhi = ndh.get(testItems[i].getId()),
								avlti = avlt.get(testItems[i].getId());
								//rbti = rbt.get(testItems[i].getId());
						System.out.println("Buscando " + testItems[i].toString());
						System.out.println((hi == null ? "Inexistente" : "Existente") + " - Hash padrão");
						System.out.println((rhi == null ? "Inexistente" : "Existente") + " - Hash com rehash");
						System.out.println((llhi == null ? "Inexistente" : "Existente") + " - Hash com lista encadeada");
						System.out.println((ndhi == null ? "Inexistente" : "Existente") + " - Hash com deleção preguiçosa");
						System.out.println((avlti== null ? "Inexistente" : "Existente") + " - Árvore balanceada");
						//System.out.println((rbti== null ? "Inexistente" : "Existente") + " - Árvore rubro-negra");
						System.out.println();
					}
					System.out.println("===============================\n");
				} else {
					for(int i=0; i < qry; i++) {
						startTime = System.nanoTime();
						h.get(testItems[i].getId());
						delGetTime[0] += (System.nanoTime() - startTime) / 1000;
						startTime = System.nanoTime();
						rh.get(testItems[i].getId());
						delGetTime[1] += (System.nanoTime() - startTime) / 1000;
						startTime = System.nanoTime();
						llh.get(testItems[i].getId());
						delGetTime[2] += (System.nanoTime() - startTime) / 1000;
						startTime = System.nanoTime();
						ndh.get(testItems[i].getId());
						delGetTime[3] += (System.nanoTime() - startTime) / 1000;
						startTime = System.nanoTime();
						avlt.get(testItems[i].getId());
						delGetTime[4] += (System.nanoTime() - startTime) / 1000;
						//startTime = System.nanoTime();
						//rbt.get(testItems[i].getId());
						//delGetTime[5] += (System.nanoTime() - startTime);
					}
				}
				
			} // for de rodadas
			
			// Exibição dos resultados
			if(sizes[sizeType] != TO_SHOW) {
				
				for(int i=0; i < 6; i++) {
					insTime[i] /= rounds;
					getTime[i] /= rounds;
					delTime[i] /= rounds;
					delGetTime[i] /= rounds;
				}
				
				System.out.println();
				System.out.println("Média de tempo para inserção de " + sizes[sizeType] + " itens");
				System.out.println("Hash padrão: " + insTime[0] + " microssegundos");
				System.out.println("Hash com rehash: " + insTime[1] + " microssegundos");
				System.out.println("Hash com lista encadeada: " + insTime[2] + " microssegundos");
				System.out.println("Hash com deleção preguiçosa: " + insTime[3] + " microssegundos");
				System.out.println("Árvore balanceada: " + insTime[4] + " microssegundos");
				System.out.println("Árvore rubro-negra: " + insTime[5] + " microssegundos");
				System.out.println("=================================\n");
				
				System.out.println("Média de tempo para busca de " + qry + " itens");
				System.out.println("Hash padrão: " + getTime[0] + " microssegundos");
				System.out.println("Hash com rehash: " + getTime[1] + " microssegundos");
				System.out.println("Hash com lista encadeada: " + getTime[2] + " microssegundos");
				System.out.println("Hash com deleção preguiçosa: " + getTime[3] + " microssegundos");
				System.out.println("Árvore balanceada: " + getTime[4] + " microssegundos");
				System.out.println("Árvore rubro-negra: " + getTime[5] + " microssegundos");
				System.out.println("=================================\n");
				
				System.out.println("Média de tempo para deleção de " + del + " itens");
				System.out.println("Hash padrão: " + delTime[0] + " microssegundos");
				System.out.println("Hash com rehash: " + delTime[1] + " microssegundos");
				System.out.println("Hash com lista encadeada: " + delTime[2] + " microssegundos");
				System.out.println("Hash com deleção preguiçosa: " + delTime[3] + " microssegundos");
				System.out.println("Árvore balanceada: " + delTime[4] + " microssegundos");
				//System.out.println("Árvore rubro-negra: " + delTime[5] + " nanossegundos");
				System.out.println("=================================\n");
				
				System.out.println("Média de tempo para busca de " + qry + " itens após " + del + " deleções");
				System.out.println("Hash padrão: " + delGetTime[0] + " microssegundos");
				System.out.println("Hash com rehash: " + delGetTime[1] + " microssegundos");
				System.out.println("Hash com lista encadeada: " + delGetTime[2] + " microssegundos");
				System.out.println("Hash com deleção preguiçosa: " + delGetTime[3] + " microssegundos");
				System.out.println("Árvore balanceada: " + delGetTime[4] + " microssegundos");
				//System.out.println("Árvore rubro-negra: " + delGetTime[5] + " nanossegundos");
				System.out.println("=================================\n");
				
			}
		
		} // for de tamanhos
		
	}

}
