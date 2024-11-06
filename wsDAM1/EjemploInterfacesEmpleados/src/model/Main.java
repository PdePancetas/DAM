package model;

public class Main {

	public static void main(String[] args) {

		Empleado e;

		e = new EmpleadoPlantilla();
		e = new EmpleadoTemporal();
		e = new EmpleadoExterno();

		e.generarNomina();

		EmpleadoDespedible[] despedir = new EmpleadoDespedible[20];
		
//		despedir[0] = new EmpleadoPlantilla();
		despedir[0] = new EmpleadoTemporal();
		despedir[1] = new EmpleadoExterno();
		
		despedir[0].generarFiniquito();
		despedir[1].generarFiniquito();
	}

}
