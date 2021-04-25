package code;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ItemGenerator {

    public static Item[] randomItems(int n) {
    	
        Item[] items = new Item[n];
        Random rand = new Random(2021); // Semente para reprodução de resultados: 2021
        int id;
        int[] data;
        
        // Geração de chaves e arrays de dados
        for(int i=0; i<n; i++) {
        	id = rand.nextInt(n*100);
        	data = new int[30];
        	for(int j=0; j<30; j++)
        		data[j] = rand.nextInt(99);
            items[i] = new Item(id, data);
        }
        
        return items;
    }
    
    public static Item[] shuffleItems(Item[] items, int n) {
    	
    	// Embaralhamento dos n primeiros itens do array
    	Item[] temp = new Item[n];
    	for(int i=0; i < n; i++) {
    		temp[i] = items[i];
    	}
    	// Uso da classe Collections para embaralhar o array
    	List<Item> itemList = Arrays.asList(temp);
        Collections.shuffle(itemList, new Random(2021));
		itemList.toArray(temp);
		for(int i=0; i < n; i++) {
    		items[i] = temp[i];
    	}
		
        return items;
    }

}
