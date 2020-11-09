
public class IngressoEstudante extends Ingresso {

	public IngressoEstudante(int cadeira) {
		super(cadeira);
	}
	
	public double getValor() {
		return (super.getValor())/2;
	}
	
	public void setValor(double valor) {
		super.setValor(valor);
	}
	
	public int getCadeira() {
		return super.getCadeira();
	}
	
	public void setCadeira(int cadeira) {
		super.setCadeira(cadeira);
	}
	
}
