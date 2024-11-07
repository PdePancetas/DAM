package tema1;

public class EjemploRuntime {

	public static void main(String[] args) {

		Runtime r = Runtime.getRuntime();
		try {
			String[] com = {"notepad.exe"};
			Process p = r.exec(com);
			System.out.println("El proceso esta funcionando?: " + p.isAlive());

			System.out.println("Esperando...");
			Thread.sleep(1000);
			p.destroy();
			
			System.out.println("Proceso destruido");
			System.out.println("El proceso esta funcionando?: " + p.isAlive());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
