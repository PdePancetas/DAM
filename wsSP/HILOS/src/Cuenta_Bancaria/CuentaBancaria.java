
package Cuenta_Bancaria;

public class CuentaBancaria {

	private int saldo;
	private boolean hayDinero;
	private Object ingresar = new Object();
	private Object retirar = new Object();

	public int getSaldo() {
		return saldo;
	}

	public CuentaBancaria() {
		super();
	}

	public CuentaBancaria(int saldo) {
		super();
		this.saldo = saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public boolean HayDinero() {
		return hayDinero;
	}

	public void setHayDinero(boolean hayDinero) {
		this.hayDinero = hayDinero;
	}

	public void ingresa(int dinero) throws InterruptedException {
		if ((saldo + dinero) <= 1000) {
			saldo += dinero;

			System.out.println(Thread.currentThread().getName() + " ingresa " + dinero);

			synchronized (retirar) {
				retirar.notifyAll();
			}
			synchronized (ingresar) {
				ingresar.notifyAll();
				System.out.println(Thread.currentThread().getName() + " he terminado de operar");
				ingresar.wait();
			}
		} else {
			System.out.println(Thread.currentThread().getName()+" la cuenta ha llegado al limite");
		}
			

	}

	public void retira(int dinero) throws InterruptedException {

		if ((saldo - dinero) >= 0) {
			saldo -= dinero;
			if (saldo == 0) {
				System.out.println(Thread.currentThread().getName() + " la cuenta esta a 0");

				synchronized (ingresar) {
					ingresar.notifyAll();
				}
				synchronized (retirar) {
					retirar.wait();
				}

			} else {
				System.out.println(Thread.currentThread().getName() + " retira " + dinero);
				synchronized (ingresar) {
					ingresar.notifyAll();
				}
				synchronized (retirar) {
					retirar.notifyAll();
					System.out.println(Thread.currentThread().getName() + " he terminado de operar");
					retirar.wait();
				}

			}
		} else {

			System.out.println(Thread.currentThread().getName() + " No queda tanto dinero que retirar");
			synchronized (ingresar) {
				ingresar.notifyAll();
			}
			synchronized (retirar) {
				retirar.wait();
			}
		}
	}
}