package com.pancetas.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pancetas.main.modelo.Empleado;


@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String> { //Bean y tipo de dato de la clave primaria
	
	
	
}
