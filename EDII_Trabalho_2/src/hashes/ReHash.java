package hashes;

public class ReHash<Key, Value> {
	
	private int N; // número de pares de chaves na tabela
	private int M = 16; // tamanho da tabela hash com tratamento linear
	private Key[] keys; // chaves
	private Value[] vals; // valores
	
	@SuppressWarnings("unchecked")
	public ReHash() {
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}
	
	@SuppressWarnings("unchecked")
	public ReHash(int cap) {
		keys = (Key[]) new Object[cap];
		vals = (Value[]) new Object[cap];
		M = cap;
	}
	
	/**
	 * Calcula o Hash 1
	 * @param key
	 * @return hash 1 da key
	 */
	private int h1(Key key) {
		return (key.hashCode() & 0x7fffffff) + 37;
	}
	
	/**
	 * Calcula o Hash 2
	 * @param key
	 * @return hash 2 da key
	 */
	private int h2(Key key) {
		int ha = ((key.hashCode() & 0x7fffffff) + 31);
		if(ha % 2 != 0)
			return ha;
		return ha + 1;
	}
	
	/**
	 * Calcula o Hash
	 * @param key
	 * @return hash da key
	 */
	private int hash(Key key, int i) {
		return (h1(key) + i*h2(key)) % M; 
	}
	
	/**
	 * Redimensiona a tabela de acordo com a nova capacidade indicada
	 * @param cap
	 */
	private void resize(int cap) {
		
		ReHash<Key, Value> t = new ReHash<Key, Value>(cap);
		
		for (int i = 0; i < keys.length; i++)
			if (keys[i] != null)
				t.put(keys[i], vals[i]);
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
		int i, j=0;
		if (N >= M/2) 
			resize(2*M); // dobra M se preencher até a metade
		
		// Calcula o hash duplo e avança até encontrar uma posição vazia
		// Se a chave já está presente, substitui o valor
		for (i = hash(key, j); keys[i] != null; i = hash(key, ++j)) {
			if (keys[i].equals(key)) { 
				vals[i] = val; 
				return; 
			}
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
	/**
	 * Remove um objeto do Hash
	 * @param key
	 */
	public void delete(Key key) {
		if (key == null) 
			throw new IllegalArgumentException("Argument to delete() cannot be null");
		
		if (!contains(key))
			return;
		
		// Busca a chave
		int j=0, i = hash(key, j);
		while (!key.equals(keys[i]))
			i = hash(key, ++j);
		
		// Deleta-se os conteúdos do item
		keys[i] = null;
		vals[i] = null;
		i = hash(key, ++j);
		
		// Passa todos os elementos seguintes para frente, usando put
		while (keys[i] != null) {
			Key keyToRedo = keys[i];
			Value valToRedo = vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;
			put(keyToRedo, valToRedo);
			i = hash(key, ++j);
		}
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
		int j=0;
		for (int i = hash(key, j); keys[i] != null; i = hash(key, ++j))
			if (keys[i].equals(key))
				return vals[i];
		return null;
	}
	
	/**
	 * Exibe os itens dentro do Hash
	 * @return String
	 */
	public String toString() {
		String str = "";
		for(int i=0; i < keys.length; i++) {
			if(keys[i] != null) {
				str += "Pos: " + i + " | " + vals[i].toString() + "\n";
			}
		}
		return str;
	}
	
	public int numItems() {
		return N;
	}
	
}
