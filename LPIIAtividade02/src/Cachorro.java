
public class Cachorro {

	private String nome;
	private int idade;
	private float peso;
	private boolean adotado;

	public Cachorro(String nome, int idade, float peso) {
		
		this.nome = nome;
		this.idade = idade;
		this.peso = peso;
		this.adotado = false;
		
	}
	
	public boolean alimentarCachorro(float quantidade) {
		
		if(quantidade > 0) {
			
			this.peso += 0.1 * quantidade;
			return true;
			
		}
		
		return false;
		
	}
	
	public boolean exercitarCachorro(int distancia) {
		
		if(distancia > 0) {
			
			this.peso -= 0.05 * distancia;
			return true;
			
		}
		
		return false;
		
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public boolean isAdotado() {
		return adotado;
	}
	
	public void setAdotado(boolean adotado) {
		this.adotado = adotado;
	}
	
}
