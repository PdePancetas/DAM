package model;
import java.util.Date;

public class EmpleadoTemporal extends Empleado implements EmpleadoDespedible{

	private Date fechaFinContrato;
	
	
	
	public EmpleadoTemporal() {
	}



	public Date getFechaFinContrato() {
		return fechaFinContrato;
	}



	public void setFechaFinContrato(Date fechaFinContrato) {
		this.fechaFinContrato = fechaFinContrato;
	}



	@Override
	public String generarNomina() {
		return "Genero nómina Temporal";
	}



	@Override
	public double generarFiniquito() {
		return Math.random();
	}

}
