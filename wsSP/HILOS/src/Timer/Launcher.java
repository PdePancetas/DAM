package Timer;

public class Launcher {

	public static void main(String[] args) {

		// Crar un Thread timer que implemente un reloj que cuenta los segundos
		// Otros threads usaran este thread para escribir por pantalla un mensaje cada
		// segundo

		Integer contador = 0;
		Timer t = new Timer(contador);
		Thread Timer = new Thread(t, "contador");
		Timer.start();
		for (int i = 0; i < 5; i++) {
			new Thread(new Escritor(t), "escritor " + i).start();
		}

	}

}
