package examRA1;

import java.util.Scanner;

public class MyProcessor {
	

	public static void main(String[] args) {
		// IMPORTANTE: ESTE FICHERO NO SE PUEDE MODIFICAR
		Scanner scan = new Scanner(System.in);
//		String id = args[0];
		
		while (true) {
			//MyProcessor recibe un numero
			int num = Integer.parseInt(scan.nextLine());
			
			//MyProcessor procesa el numero 
			try {
				//simulando que tarda 3 segundos para procesar el número
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//MyProcesor avisa que ha procesado el numero
			System.out.println("Soy el MyProcessor " + /*id + */" y he procesado el numero " + num);
		}
		// IMPORTANTE: ESTE FICHERO NO SE PUEDE MODIFICAR
	}
}
