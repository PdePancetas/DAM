package TEORÍA;


public class EJEMPLOSCHARBOOLEAN {

	public static void main(String[] args) {
		
		int numero1 = 87, numero2 = 85;
		char c1,c2;
		int n;
		
		//c = 'L';
		
		c1 = 76;
		c2 = 6;
		
		n = 76;
		
		System.out.println(c1);
		System.out.println(n);
		
		System.out.println("*******");
		
		//CUIDADO: Los operadores algebraicos, si lo necesitan convierten
		//los operandos a n�meros:
		//Lo siguente no muestra el cr�cter ASCII 82,
		//sino el n�mero 82:
		System.out.println(c1+c2);
		
		System.out.println((char)(c1+c2));
		
		char c3 = (char)n;
		
		System.out.println(c3);
		
		
		//Valor ASCII de la letra z + valor ASCII del s�mbolo $ da
		//como resultado el s�mbolo que corresponde (ASCII) al valor del resultado:
		c3 ='2';
		char c4 = '.';
		
		System.out.println((char)(c3+c4));
		
		//*************************************************
		//TIPO BOOLEAN
		//Sirven para guardar informaci�n sobre si algo ocurre o no:
		//Las variables booleanas representan 
		//CONDICIONES, es decir, hechos que se cumplen 
		//o no se cumplen:
		
		boolean pagado;
		
		pagado = true;
		pagado = false;
		
		//A una variable booleana se le pueden asignar 
		//expresiones, siempre y cuando esas expresiones solo tengan 2 
		//posibles resulados: verdadero o falso
		pagado = numero1 > numero2;
		
		//No es valido porque el resultado no es un valor booleano:
		//pagado = numero1 + 6;
		
		//ASIGNACIONES CONDICIONADAS:
		
		//Queremos asignar 0 a numero1 si pagado es true 
		//o 1 si pagado es false:
		numero1 = (pagado)?0:1;
		
		numero1 = (numero1 > numero2)?0:1;
			
		
		
		
	}

}