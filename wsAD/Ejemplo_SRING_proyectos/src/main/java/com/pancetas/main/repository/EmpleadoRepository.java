package com.pancetas.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pancetas.main.modelo.Departamento;
import com.pancetas.main.modelo.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> { //Bean y tipo de dato de la clave primaria
	
	
	
	List<Empleado> findByNombre(String nombre);
	
	List<Empleado> findByDepartamento(Departamento departamento);
	
	
	
}
