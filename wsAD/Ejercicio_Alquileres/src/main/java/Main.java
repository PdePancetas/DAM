import beans.Datos;
import func.Funcionalidades;

public class Main {

	public static void main(String[] args) {
		
		Datos datos = new Datos();
		Funcionalidades.addEmpleado(1, "pepe", 2000);
		Funcionalidades.addEmpleado(2, "Jose", 3000);
		Funcionalidades.addPiso("1", 200, 1);
		Funcionalidades.addPiso("2", 300, 2);
		Funcionalidades.addPiso("3", 500, 2);
		
//		Funcionalidades.mostrarPisos();
//		System.out.println(Funcionalidades.mostrarEmpleadoPiso(1));
//		System.out.println(Funcionalidades.sueldoMensual(1));
		
		
		System.out.println(Funcionalidades.masPisosAlquilados().getNombre());
		
	}

}
