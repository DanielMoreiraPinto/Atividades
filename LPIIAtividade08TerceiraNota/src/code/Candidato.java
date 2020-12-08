package code;

public class Candidato {

	private String inscricao;
	private String nome;
	private int idade;
	private int nota;
	
	public Candidato(String inscricao, String nome, int idade, int nota) {
	
		this.inscricao = inscricao;
		this.nome = nome;
		this.idade = idade;
		this.nota = nota;
	}
	
	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
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

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		// Ao printar, uma linha é misteriosamente pulada antes da inscrição
		return ("Candidato: " + this.getInscricao() + ", Nome: " + this.getNome() 
		+ ", Idade: " + this.getIdade() + ", Nota: " + this.getNota());
	}
	
	

}
