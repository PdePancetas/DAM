package Clase_Alumno_Profesor;

import java.util.ArrayList;

public class Launcher {

//	Simular, usando wait() y notifyAll() el principio de una clase:
//		Los alumnos vienen a clase y, si el profesor no está, esperan.
//		Cuando llega el profesor, saludará a los alumnos, y determinará que la clase ha iniciado, 
//		avisando a los alumnos que ha iniciado.
//		Los alumnos que estaban esperando, saludan al profesor y empiezan a seguir la clase.
//
//		Nota: Alumnos y Profesor necesitarán compartir un objeto sobre el que harán 
//		wait() y notifyAll()… Si llamo la notifyAll o la wait sobre objetos distintos 
//		(por ejemplo cada alumno llama wait() directamente, llamandola entonces sobre si mismo) 
//		el programa no va a funcionar.

	public static void main(String[] args) {
		
		Clase clase = new Clase();
		Profesor p = new Profesor(clase);
		Thread t = new Thread(p,"Prof");
		for(int i=0;i<5;i++) {
			Thread a = new Thread(new Alumno(clase),"Alumno "+i);
			a.start();
		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t.start();
		
	}

}
