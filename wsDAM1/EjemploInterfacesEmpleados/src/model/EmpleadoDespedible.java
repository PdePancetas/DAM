package model;

public interface EmpleadoDespedible {

	//No pueden tener propiedades específicas, pero --> int num;
	//si pueden tener propiedades ESTATICAS (static) Y CONSTANTES (final) -->static final int num;

	static final int porcentaje = 8;
	
	//Las interfaces tienen métodos vacíos y no hace falta poner abstract.
	//Son obligatoriamente public así que es opcional ponerlo.
	
	/*public*/ double generarFiniquito();
	
	
}
