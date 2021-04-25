package hashes;

import java.util.Iterator;
import java.util.LinkedList;

public class LLHash<Key, Value> {

	private int N; // número de pares de chaves na tabela
	private int M = 16; // tamanho da tabela hash com tratamento linear
	private LinkedList<Key>[] keys; // as chaves
	private LinkedList<Value>[] vals; // the values
	
	@SuppressWarnings("unchecked")
	public LLHash() {
		keys = (LinkedList<Key>[]) new LinkedList[M];
		vals = (LinkedList<Value>[]) new LinkedList[M];
	}
	
	@SuppressWarnings("unchecked")
	public LLHash(int cap) {
		keys = (LinkedList<Key>[]) new LinkedList[cap];
		vals = (LinkedList<Value>[]) new LinkedList[cap];
		M = cap;
	}
	
	/**
	 * Calcula o Hash
	 * @param key
	 * @return hash da key
	 */
	private int hash(Key key){ 
		return (key.hashCode() & 0x7fffffff) % M; 
	}
	
	/**
	 * Redimensiona a tabela de acordo com a nova capacidade indicada
	 * @param cap
	 */
	private void resize(int cap) {
		
		LLHash<Key, Value> t = new LLHash<Key, Value>(cap);
		
		for (int i = 0; i < keys.length; i++)
			if (keys[i] != null)
				for(int j=keys[i].size(); j > 0; j--)
					t.put(keys[i].remove(), vals[i].remove());
		
		keys = t.keys;
		vals = t.vals;
		M = t.M;
		
	}
	
	/**
	 * Utiliza o método get para descobrir se um elemento está no hash
	 * @param key
	 * @return boolean
	 */
	public boolean contains(Key key) {
	    if (key == null) {
	        throw new IllegalArgumentException("Argument to contains() cannot be null");
	    }
	    return get(key) != null;
	}
	
	/**
	 * Insere um novo objeto no Hash 
	 * @param key
	 * @param val
	 */
	public void put(Key key, Value val) {
		int i;
		if (N >= M/2)
			resize(2*M); // dobra M se preencher até a metade
		
		// Calcula o hash e insere no final da lista
		// Se a chave já está presente, substitui o valor
		i = hash(key);
		if(keys[i] != null) {
			int j = keys[i].indexOf(key);
			if(j != -1) { 
				vals[i].set(j, val);
				return;
			}
		} else {
			keys[i] = new LinkedList<Key>();
			vals[i] = new LinkedList<Value>();
		}
		keys[i].add(key);
		vals[i].add(val);
		N++;
	
	}
	
	/**
	 * Remove um objeto do Hash
	 * @param key
	 */
	public void delete(Key key) // ALTERAR
	{
		if (key == null) 
			throw new IllegalArgumentException("Argument to delete() cannot be null");
		
		if (!contains(key))
			return;
		
		// Busca a posição do elemento na lista e o remove
		int i = hash(key), j=0;
		Iterator<Key> ki = keys[i].iterator(); 
		Key cur = ki.next();
		while(!cur.equals(key)) {
			cur = ki.next();
			j++;
		}
		keys[i].remove(j);
		vals[i].remove(j);
		N--;
		// Diminui o hash se chegar a um oitavo do espaço ocupado
		if (N > 0 && N == M/8) 
			resize(M/2);
	}
	
	/**
	 * Busca um objeto no Hash
	 * @param key
	 * @return valor
	 */
	public Value get(Key key) {
		int i = hash(key);
		if(keys[i] != null)
			for(int j=0; j < keys[i].size(); j++)
				if(keys[i].get(j).equals(key))
					return vals[i].get(j);
		return null;
	}
	
	/**
	 * Exibe os itens dentro do Hash
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public String toString() { 
		String str = "";
		for(int i=0; i < keys.length; i++) {
			if(keys[i] != null) {
				Iterator it = vals[i].iterator();
				while(it.hasNext())
					str += "Pos: " + i + " | " + it.next().toString() + "\n";
			}
		}
		return str;
	}
	
	public int numItems() {
		return N;
	}
	
}
