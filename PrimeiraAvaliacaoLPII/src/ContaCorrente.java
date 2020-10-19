
public class ContaCorrente {

	private int num_agencia;
	private int num_conta;
	private String banco;
	private float saldo;
	
	public ContaCorrente() {}
	
	public ContaCorrente(int num_agencia, int num_conta, String banco, float saldo) {
		
		this.num_agencia = num_agencia;
		this.num_conta = num_conta;
		this.banco = banco;
		this.saldo = saldo;
	
	}
	/*Usando getters e setters até dentro da classe para preservar coerência
	 * no cenário hipotético do código desses métodos ser mais complexo ou 
	 * precisar tornar-se mais complexo futuramente.*/
	public boolean depositar(float quantia) {
		
		if(quantia > 0) {
			
			float saldo = this.getSaldo();
			this.setSaldo((saldo + quantia));
			return true;
			
		}
		
		return false;
		
	}
	
	public boolean sacar(float quantia) {
		
		if(quantia > 0 && quantia <= this.getSaldo()) {
			
			float saldo = this.getSaldo();
			saldo -=  quantia + (0.005 * quantia);
			this.setSaldo(saldo);
			return true;
			
		}
		
		return false;
		
	}
	
	public float obterSaldo() {
		
		return this.getSaldo();
		
	}
	
	public int getNum_agencia() {
		return num_agencia;
	}
	public void setNum_agencia(int num_agencia) {
		this.num_agencia = num_agencia;
	}
	public int getNum_conta() {
		return num_conta;
	}
	public void setNum_conta(int num_conta) {
		this.num_conta = num_conta;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
}
