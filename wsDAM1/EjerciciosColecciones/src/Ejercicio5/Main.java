package Ejercicio5;

import utilidadesTeclado.Teclado;

public class Main {

//	5.	Realizar una clase Discoteca con propiedades: temática (String), conjunto de discos
//	(conjunto de objetos de clase Disco: autor y título).
//	Realizar una aplicación con las siguientes funcionalidades:
//	-	Añadir y eliminar varios discos que se soliciten por teclado (usar método removeAll)
//	-	Consultar por autor
//	-	Consultar por título
//	Dado otro objeto Discoteca, indicar si hay discos repetidos. 
//	Si los hay, ir mostrando cada uno de ellos, solicitando de qué discoteca eliminar

	public static void main(String[] args) {
		
		
		Discoteca discoteca = new Discoteca();
		llenarDatos(discoteca);
		
		int op;
		do {
			System.out.println("_____________________________________________________");
			System.out.println("Elige una opción");
			System.out.println("	1. Devolver el cd con más duración");
			System.out.println("	2. ");
			System.out.println("	3. ");
			System.out.println("	4. ");
			System.out.println("0. Salir \n");
			System.out.print("-> ");
			op = Teclado.leerEntero();

			switch (op) {
			case 1:
				Disco d = cdMasDuracion(discoteca);
				System.out.println("El cd con más duración es "+((CD)d).getTitulo());

				break;

			case 2:

				break;
			case 3:

				break;
			case 4:

				break;
			case 0:
				break;
			default:
			}

		} while (op != 0);

	}

	private static void llenarDatos(Discoteca discoteca) {

		discoteca.setTematica("Jazz");

		CD cd1 = new CD();
		CD cd2 = new CD();
		cd1.setTitulo("Perro");
		cd1.getPistas().add(new Pista("Pista1", 1000));
		cd1.getPistas().add(new Pista("Pista2", 2000));
		cd1.getPistas().add(new Pista("Pista3", 500));
		cd1.getPistas().add(new Pista("Pista4", 3000));
		
		cd2.setTitulo("Pájaros");
		cd2.getPistas().add(new Pista("Pista1", 100));
		cd2.getPistas().add(new Pista("Pista2", 20000));
		cd2.getPistas().add(new Pista("Pista3", 5000));
		cd2.getPistas().add(new Pista("Pista4", 30000));
		
		discoteca.getDiscos().add(cd1);
		discoteca.getDiscos().add(cd2);
		Vinilo v = new Vinilo();
		v.setTitulo("Gatos");
		v.setNrpm(33);

		discoteca.getDiscos().add(v);

	}

	private static Disco cdMasDuracion(Discoteca discoteca) {
		int sumaDuracion = 0;
		int sumaMaximo = 0;
		boolean existeCD = false;
		Disco maximo = null;
		int cont = 0;
		for (int i = 0; i < discoteca.getDiscos().size() && !existeCD; i++) {
			cont++;
			if (discoteca.getDiscos().get(i) instanceof CD) {
				maximo = discoteca.getDiscos().get(i);
				existeCD = true;
				for (Pista p : ((CD) discoteca.getDiscos().get(i)).getPistas())
					sumaMaximo += p.getDuracion();
				
			}
		}

		if (!existeCD) {
			System.out.println("No hay CDs en esta discoteca");
			return null;
		} else {
			for (int i = cont; i < discoteca.getDiscos().size(); i++) {
				if (discoteca.getDiscos().get(i) instanceof CD) {
					for (Pista p : ((CD) discoteca.getDiscos().get(i)).getPistas())
						sumaDuracion += p.getDuracion();
					if(sumaDuracion>sumaMaximo)
						maximo = discoteca.getDiscos().get(i);
				}
			}

		return maximo;
		}
	}
}
