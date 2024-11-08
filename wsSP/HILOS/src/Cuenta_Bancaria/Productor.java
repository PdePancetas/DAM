package Cuenta_Bancaria;

public class Productor implements Runnable {

	private CuentaBancaria cuenta;

	@Override
	public void run() {

	}

	public Productor() {
		super();
	}

	public Productor(CuentaBancaria cuenta) {
		super();
		this.cuenta = cuenta;
	}

	public CuentaBancaria getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaBancaria cuenta) {
		this.cuenta = cuenta;
	}

}
