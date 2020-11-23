
public class ContaBancaria {

	private double saldo;
	private double limite;
	
	public ContaBancaria(double valorSaldo, double valorLimite) {
		
		this.saldo = valorSaldo;
		this.limite = valorLimite;
		
	}
	
	public double getSaldo() {
		
		return this.saldo;
		
	}
	
	public double getLimite() {
		
		return this.limite;
		
	}
	
	public double getSaldoComLimite() {
		
		return (this.saldo + this.limite);
		
	}
	
	public boolean sacar(double valor) throws ContaException {
		
		if(valor <= 0)
			throw new ContaException("Valor inválido.");
		
		if(valor > 500)
			throw new ContaException("Saques acima de $500 não são permitidos.");
		
		if(valor > (this.getSaldo() + this.getLimite()))
			throw new ContaException("Limite excedido.");
		
		this.saldo -= valor;
		return true;
		
	}
	
	public void depositar(double valor) throws ContaException {
		
		if(valor <= 0)
			throw new ContaException("Valor inválido.");
		
		if(valor > 1000)
			throw new ContaException("Depósitos acima de $1000 não são permitidos.");
		
		this.saldo += valor;
		
	}
	
}
