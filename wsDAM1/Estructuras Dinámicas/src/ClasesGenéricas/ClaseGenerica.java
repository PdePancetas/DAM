package ClasesGenéricas;

public class ClaseGenerica<T,V> {

	int cod;
	
	T dato1;
	V dato2;
		
	public void mostrar() {
		System.out.println("Contenido dato 1: "+dato1);
		System.out.println("Contenido dato 2: "+dato2);
	}
	
	public static void main(String[] args) {
		
		ClaseGenerica<String, Integer> datos = new ClaseGenerica<String, Integer>();
		
		datos.dato1="we";
		datos.dato2=2;
		
	}
	
	
}
