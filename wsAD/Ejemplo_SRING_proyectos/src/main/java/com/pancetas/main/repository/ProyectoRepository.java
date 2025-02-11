package com.pancetas.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import com.pancetas.main.modelo.Proyecto;

import java.util.List;


@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer>{
	
	List<Proyecto> findByIdProy(Integer idProy);
	void deleteByIdProy(Integer idProy);
	Streamable<Proyecto> streamByIdProy(Integer idProy);
	List<Proyecto> getByIdProy(Integer idProy);
	List<Proyecto> getByNomProy(String nomProy);
	long countByIdProy(Integer idProy);
	
	
	
	
}
