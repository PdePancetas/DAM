package com.pancetas.apirest.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pancetas.apirest.models.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, BigInteger> { //Bean y tipo de dato de la clave primaria
	
	
	
}
