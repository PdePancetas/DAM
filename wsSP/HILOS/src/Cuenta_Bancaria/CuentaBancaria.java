package Cuenta_Bancaria;

public class CuentaBancaria {

	private int saldo;
	private Object monProductor = new Object();
	private Object monConsumidor = new Object();

	public int getSaldo() {
		return saldo;
	}

	public CuentaBancaria() {
		super();
	}

	public CuentaBancaria(int saldo, Object monProductor, Object monConsumidor) {
		super();
		this.saldo = saldo;
		this.monProductor = monProductor;
		this.monConsumidor = monConsumidor;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public Object getMonProductor() {
		return monProductor;
	}

	public void setMonProductor(Object monProductor) {
		this.monProductor = monProductor;
	}

	public Object getMonConsumidor() {
		return monConsumidor;
	}

	public void setMonConsumidor(Object monConsumidor) {
		this.monConsumidor = monConsumidor;
	}

}
