import utilidadesTeclado.Teclado;

public class Main {

	public static void main(String[] args) {

		int m = 0;
		while (m != 1)
			try {
				System.out.print("Introduce un numero para calcular su raiz: ");
				int n = Teclado.leerEntero();
				System.out.println("La raiz de " + n + " es: " + metodoRaiz(n));
				m=1;
			} catch (NegativeNumberException e) {
				System.out.println("Error: " + e.getMessage()+"\n");
				m=0;
			}
	}

	/**
	 * @param n
	 * @return si n es negativo, o la raiz si n es >=0
	 */
	static double metodoRaiz(int n) {
		if (n < 0) {
			throw new NegativeNumberException("Ha introducido un numero negativo, y no se puede hacer la raiz.");
		} else {
			return Math.sqrt(n);
		}
	}

}
