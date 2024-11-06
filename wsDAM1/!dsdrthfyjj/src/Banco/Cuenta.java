package Banco;

public class Cuenta {
	
	private String numero; //Numero cuenta bancaria
	private float saldo;
	
	public Cuenta(String numero, float saldo) {
		this.numero = numero;
		this.saldo = saldo;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	public void ingresarDinero(float importe) {
		saldo=saldo+importe;
	}
	
	public void extraerDinero(float importe) {
		/*saldo=saldo-importe;*/
		if ((saldo-importe<0)) 
			throw new java.lang.ArithmeticException("Saldo Negativo");
		else saldo=saldo-importe;
	}
	
	public void mostrarCuenta() {
		System.out.println("Numero de  de cuenta: "+ getNumero());
		System.out.println("Saldo: "+ getSaldo());
	}

}
