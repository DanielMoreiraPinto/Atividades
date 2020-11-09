import java.util.*;

public class Cinema {

	private double valorIngresso;
	private ArrayList<Ingresso> ingressosVendidos;
	
	public Cinema() {
		this.ingressosVendidos = new ArrayList<Ingresso>();
	}
	
	public boolean definirValorDoIngresso(double valorNormal) {
		
		if(valorNormal > 0) {
			
			this.valorIngresso = valorNormal;
			return true;
			
		}
		return false;
		
	}
	
	public String listarLugaresOcupados() {
		
		if(this.getIngressosVendidos().isEmpty()) return "Sala vazia.";
		
		ArrayList<Ingresso> ingressos = this.getIngressosVendidos();
		
		String listagem = "Lugares ocupados:\n";
		int i=0;
		for(Ingresso ingresso : ingressos) {
			
			listagem += ingresso.getCadeira() + "\t";
			i++;
			if(i >= 10) {
				listagem += "\n";
				i=0;
			}
			
		}
		return listagem;
		
	}
	public String mostrarValorRecebido() {
		
		double valorRecebido = 0;
		ArrayList<Ingresso> ingressos = this.getIngressosVendidos();
		
		for(Ingresso ingresso : ingressos) {
			valorRecebido += ingresso.getValor();
		}
		
		return "Total recebido: R$" + (int)Math.floor(valorRecebido)
		+ "," + (int)(Math.floor((valorRecebido-Math.floor(valorRecebido))*100));
		
	}
	public Ingresso comprarIngresso(Ingresso id) {
		
		if(id.getCadeira() < 1 || id.getCadeira() > 300)
			return null;
		
		if(!(id instanceof IngressoVip) && id.getCadeira() > 200)
			return null;
		
		if((id instanceof IngressoVip) && id.getCadeira() < 201)
			return null;
		
		id.setValor(this.getValorIngresso());
		
		ArrayList<Ingresso> ingressos = this.getIngressosVendidos();
		
		for(Ingresso ingresso : ingressos) {
			if(id.getCadeira() == ingresso.getCadeira())
				return null;
		}

		ingressos.add(id);
		this.setIngressosVendidos(ingressos);
		return id;
		
	}

	public double getValorIngresso() {
		return valorIngresso;
	}

	public void setValorIngresso(double valorIngresso) {
		this.valorIngresso = valorIngresso;
	}

	public ArrayList<Ingresso> getIngressosVendidos() {
		return ingressosVendidos;
	}

	public void setIngressosVendidos(ArrayList<Ingresso> ingressosVendidos) {
		this.ingressosVendidos = ingressosVendidos;
	}
	
	

}
