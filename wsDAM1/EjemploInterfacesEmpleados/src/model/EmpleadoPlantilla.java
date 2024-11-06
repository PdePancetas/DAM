package model;
import java.util.Date;

public class EmpleadoPlantilla extends Empleado {
	
	private Date fechaIngreso;
	
	
	public EmpleadoPlantilla() {
	}


	public Date getFechaIngreso() {
		return fechaIngreso;
	}


	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}


	@Override
	public String generarNomina() {
		return "Genero nómina Plantilla";
	}

}
