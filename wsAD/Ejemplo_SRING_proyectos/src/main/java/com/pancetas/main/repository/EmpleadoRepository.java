package com.pancetas.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pancetas.main.modelo.Empleado;


@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String> { //Bean y tipo de dato de la clave primaria
	
	
	List<Empleado> findByNomEmp(String nomEmp);
	
	//SENTENCIAS DE CUALQUIER TIPO
	@Query("SELECT empleadoJefe FROM Proyecto WHERE nomProy = :nomProyecto")
	List<Empleado> findJefesDeNomProy(String nomProyecto);
	
}
