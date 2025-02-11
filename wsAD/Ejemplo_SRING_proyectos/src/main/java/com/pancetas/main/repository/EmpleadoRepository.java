package com.pancetas.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import com.pancetas.main.modelo.Empleado;


@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String> { //Bean y tipo de dato de la clave primaria
	
	List<Empleado> findByDni(String dni);
	void deleteByDni(String dni);
	Streamable<Empleado> streamByDni(String dni);
	List<Empleado> getByDni(String dni);
	List<Empleado> getByNomEmp(String nomEmp);
	long countByDni(String dni);
	
	
}
