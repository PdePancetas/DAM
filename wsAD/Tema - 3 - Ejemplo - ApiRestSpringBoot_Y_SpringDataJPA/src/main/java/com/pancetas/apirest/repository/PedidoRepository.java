package com.pancetas.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pancetas.apirest.models.Pedido;

import java.math.BigInteger;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{ //Bean y tipo de dato de la clave primaria
	
	
}
