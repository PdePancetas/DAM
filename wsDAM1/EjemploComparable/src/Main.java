import java.util.Arrays;

import utilidadesTeclado.Teclado;

public class Main {
	public static void main(String[] args) {

		
		int op;
		do {
			System.out.println("_______________________________");
			System.out.println("Elige una opcion: ");
			System.out.println("1. Insertar alumno");
			System.out.println("2. Mostrar porcentaje aprobados");
			System.out.println("3. Mostrar listado");
			System.out.println("4. Mostar listado ordenado por nota ascendente");
			System.out.println("0. Salir");
			System.out.print("\n ->");
			op=Teclado.leerEntero();
			
			switch(op) {
			case 1:
				System.out.println("Introduzca nif nombre y nota");
				Repositorio.addAlumno(new Alumno(Teclado.leerCadena(), Teclado.leerCadena(), Teclado.leerEntero()));
				
				break;
			case 2:
				int cont=0;
				for(Alumno a: Repositorio.alumnos)
					if(a.getNota()>=5)
						cont++;
				System.out.println("Hay un "+(cont*100)/Repositorio.alumnos.length+"% de aprobados.");
				break;
			case 3:
				for(Alumno a: Repositorio.alumnos)
					System.out.println(a.getNombre()+": "+a.getNota());
				break;
			case 4:
				Alumno[] aux= Repositorio.alumnos;
				Arrays.sort(aux);
				for(Alumno a: aux)
					System.out.println(a.getNombre()+": "+a.getNota());
				break;
			case 5: System.out.println("Saliendo...");
				break;
			default: System.out.println("Opcion no valida");
			}
		
		}while(op!=0);
		
		
		
		
	}

}
