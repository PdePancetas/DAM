package model;

public class EmpleadoExterno extends Empleado implements EmpleadoDespedible{

	private String nombreEmpresa;
	
	
	
	public EmpleadoExterno() {
	}
	
	
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	

	@Override
	public String generarNomina() {
		return "Genero nómina Externa";
	}


	@Override
	public double generarFiniquito() {
		return Math.random();
	}

}
