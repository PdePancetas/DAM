package dao;

import beans.Empleado;

public interface EmpleadoDao {

	boolean addEmpleado(Empleado emp);
	double sueldoEmpleado(int nif);
	Empleado getMejorEmpleado();
	Empleado getEmpleado(int nif);
}
