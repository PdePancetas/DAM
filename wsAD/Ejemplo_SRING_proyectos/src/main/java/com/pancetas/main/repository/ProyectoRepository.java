package com.pancetas.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import com.pancetas.main.modelo.Proyecto;

import java.util.List;


@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer>{ //Bean y tipo de dato de la clave primaria
	
	List<Proyecto> findByIdProyGreaterThan(int idCorte);
	
}
