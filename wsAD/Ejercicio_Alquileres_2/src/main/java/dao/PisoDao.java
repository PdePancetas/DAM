package dao;

import beans.Piso;

public interface PisoDao {



	boolean addPiso(Piso piso);
	
	/*-	Modificar la mensualidad de un piso*/
	boolean modMensualidad(int codigo, double mensualidad);

	/*-	Cambiar en un piso el empleado que lo lleva*/
	boolean cambiarEmpleadoPiso(Piso piso, int nif);

	/*-	Alquilar piso/dejar de alquilarlo*/
	boolean alquilar_noAlquilar(boolean alquilado, Piso piso);

	/*-	Dado un código de piso, mostrar el nombre del empleado que lo lleva*/
	String mostrarEmpleadoPiso(int id);
}