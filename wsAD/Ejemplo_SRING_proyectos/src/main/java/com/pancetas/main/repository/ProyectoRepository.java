package com.pancetas.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pancetas.main.modelo.Departamento;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer>{
	
	
	
}
