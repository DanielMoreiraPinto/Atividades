package hashes;

public class NoDelHash<Key, Value> {
	
	private int N; // número de pares de chaves na tabela
	private int M = 16; // tamanho da tabela hash com tratamento linear
	private Key[] keys; // chaves
	private Value[] vals; // valores
	private boolean[] stats; // status de deleção
	
	private final boolean ACC = true; // active
	private final boolean DEL = false; // deleted
	
	@SuppressWarnings("unchecked")
	public NoDelHash() {
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
		stats = new boolean[M];
	}
	
	@SuppressWarnings("unchecked")
	public NoDelHash(int cap) {
		keys = (Key[]) new Object[cap];
		vals = (Value[]) new Object[cap];
		stats = new boolean[cap];
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
		
		NoDelHash<Key, Value> t = new NoDelHash<Key, Value>(cap);
		
		for (int i = 0; i < keys.length; i++)
			if (keys[i] != null && stats[i])
				t.put(keys[i], vals[i]);
		keys = t.keys;
		vals = t.vals;
		stats = t.stats;
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
		int i, pos=-1;
		if (N >= M/2) 
			resize(2*M); // dobra M se preencher até a metade
		
		// Calcula o hash e avança até encontrar uma posição vazia, pulando deletados
		// Se passou por um deletado, insere no primeiro a ter passado
		// Se a chave já está presente, substitui o valor
		for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
			if(keys[i].equals(key) && stats[i]) {
				vals[i] = val;
				return; 
			}
			if(!stats[i] && pos == -1)
				pos = i;
		}
		
		if(pos == -1)
			pos = i;
		keys[pos] = key;
		vals[pos] = val;
		stats[pos] = ACC;
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
		int i = hash(key);
		while (!key.equals(keys[i]))
			i = (i + 1) % M;
		
		// Define o elemento como deletado
		stats[i] = DEL;
		
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
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key) && stats[i])
				return vals[i];
		return null;
	}
	
	/**
	 * Exibe os itens ativos dentro do Hash
	 * @return String
	 */
	public String toString() {
		String str = "";
		for(int i=0; i < keys.length; i++) {
			if(keys[i] != null && stats[i]) {
				str += "Pos: " + i + " | " + vals[i].toString() + "\n";
			}
		}
		return str;
	}
	
	public int numItems() {
		return N;
	}
	
}
