package Cuenta_Bancaria;

public class Persona implements Runnable {

	private CuentaBancaria cuenta;

	@Override
	public void run() {
		while (cuenta.getSaldo() < 1000) {
			double prob = Math.random();
			int cant = (int) (Math.random() * 10) + 1;
			if (prob < 0.5) {

				try {
					cuenta.retira(cant);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			} else {
				try {
					cuenta.ingresa(cant);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
			
		}

		}

	public Persona() {
		super();
	}

	public Persona(CuentaBancaria cuenta) {
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
