package TEORÍA;

public class TiposVariables {

	public static void main(String[] args) {
		// EJEMPLO CON ENTEROS
				int numero1, numero2;
				numero1 = 138;
				numero2 = -27;

				// Muestra por consola la cadena "numero1":
				System.out.println("numero1:");

				// Muestra por consola el CONTENIDO de la
				// variable que se llama numero 1:
				System.out.println(numero1);

				// Muestra por consola la cadena "numero2":
				System.out.println("numero2:");

				// Muestra por consola el CONTENIDO de la
				// variable que se llama numero 2:
				System.out.println(numero2);

				numero2 = 65 * 9;

				// Vamos a hacer que numero2 tenga el doble
				// del valor de numero1:
				numero2 = numero1 * 2 - numero1;
				
				// -->En la expresion que se usa para obtener
				// el valor a asignar a una variable puede haber a su
				// vez otras variables:

				numero1 = 4;
				numero1 = 5;

				numero2 = numero1 * 2 - numero2;

				// Matices en cuanto a la compatibilidad de datos
				// en las asignaciones:

				long numero3 = 6;

				// La siguiente no la podemos hacer porque podriamos
				// perder parte del contenido de numero3 si este ocupa
				// más de 32 bits:

				// numero2 = numero3;
				int numero11, numero21;
//				numero1 = 138;
				numero21 = -27;

				// Muestra por consola la cadena "numero1":
				System.out.println("numero1:");

				// Muestra por consola el CONTENIDO de la
				// variable que se llama numero 1:
				System.out.println(numero1);

				// Muestra por consola la cadena "numero2":
				System.out.println("numero2:");

				// Muestra por consola el CONTENIDO de la
				// variable que se llama numero 2:
				System.out.println(numero21);

				numero21 = 65 * 9;

				// Vamos a hacer que numero2 tenga el doble
				// del valor de numero1:
				numero21 = numero1 * 2 - numero1;
				// -->En la expresi�n que se usa para obtener
				// el valor a asignar a una variable puede haber a su
				// vez otras variables:

				numero11 = 4;
				numero21 = 5;

				numero21 = numero11 * 2 - numero21;

				// Matices en cuanto a la compatibilidad de datos
				// en las asignaciones:

				long numero31 = 6;

				// La siguiente no la podemos hacer porque podriamos
				// perder parte del contenido de numero3 si este ocupa
				// m�s de 32 bits:

				// numero2 = numero3;

				// Sin embargo la siguiente si podemos porque nunca perderiamos
				// la informacion del contenido de nuemro2: PROMOCI�N AUTOM�TICA DE VARIABLES
				numero31 = numero21;

				// Mostramos el mayor y menor valor almacenable en una variable
				// de tipo long:
				System.out.println(Long.MAX_VALUE);
				System.out.println(Long.MIN_VALUE);

				// Mostramos el mayor y menor valor almacenable en una variable
				// de tipo int:
				System.out.println(Integer.MAX_VALUE);
				System.out.println(Integer.MIN_VALUE);

				// Cuando un programa arranca, en la RAM aparecen muchas variables,
				// ademas de las nuestras
				// Mostramos la variable PI:
				System.out.println(Math.PI);

				// Esta sentencia da error de compilaci�n porque la JVM
				// trata de guardar el n�mero pasado en 32 bits:
				// numero2 = 11111111111;

				// Si a�adimos una L, guarda directamente el numero en 64 bits:
				numero31 = 11111111111L;

				// �QUE PODEMOS HACER SI QUEREMOS ASIGNAR UN DATO 64 BITS A UNA VARIABLE 32?
				// --> CASTING (CONVERSI�N) PELIGROSA!!!

				numero31 = 7654L;

				// HACEMOS UN CASTING DE LONG A INT:
				// ENTRE PAR�NTESIS SE PONE DELANTE DEL VALOR A CONVERTIR EL TIPO FINAL:
				numero21 = (int) numero31;

				System.out.println(numero31);
				System.out.println(numero21);

				// *************************************************************
				// EJEMPLO CON DECIMALES:

				double decimal1 = 56.5;

				float decimal2 = 65.6f;

				decimal2 = (float) decimal1;

				System.out.println(Double.MAX_VALUE);
				System.out.println(Double.MIN_VALUE);

				System.out.println(Float.MAX_VALUE);
				System.out.println(Float.MIN_VALUE);
				// Sin embargo la siguiente si podemos porque nunca perderiamos
				// la informacion del contenido de nuemro2: PROMOCI�N AUTOM�TICA DE VARIABLES
				numero31 = numero21;

				// Mostramos el mayor y menor valor almacenable en una variable
				// de tipo long:
				System.out.println(Long.MAX_VALUE);
				System.out.println(Long.MIN_VALUE);

				// Mostramos el mayor y menor valor almacenable en una variable
				// de tipo int:
				System.out.println(Integer.MAX_VALUE);
				System.out.println(Integer.MIN_VALUE);

				// Cuando un programa arranca, en la RAM aparecen muchas variables,
				// ademas de las nuestras
				// Mostramos la variable PI:
				System.out.println(Math.PI);

				// Esta sentencia da error de compilaci�n porque la JVM
				// trata de guardar el n�mero pasado en 32 bits:
				// numero2 = 11111111111;

				// Si a�adimos una L, guarda directamente el numero en 64 bits:
				numero31 = 11111111111L;

				// ¿QUE PODEMOS HACER SI QUEREMOS ASIGNAR UN DATO 64 BITS A UNA VARIABLE 32?
				// --> CASTING (CONVERSIÓN) PELIGROSA!!!

				numero31 = 7654L;

				// HACEMOS UN CASTING DE LONG A INT:
				// ENTRE PAR�NTESIS SE PONE DELANTE DEL VALOR A CONVERTIR EL TIPO FINAL:
				numero21 = (int) numero31;

				System.out.println(numero31);
				System.out.println(numero21);

				// *************************************************************
				// EJEMPLO CON DECIMALES:

				double decimal11 = 56.5;

				float decimal21 = 65.6f;

				decimal21 = (float) decimal11;

				System.out.println(Double.MAX_VALUE);
				System.out.println(Double.MIN_VALUE);

				System.out.println(Float.MAX_VALUE);
				System.out.println(Float.MIN_VALUE);

	}

}
