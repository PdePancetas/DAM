package com.dam2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam2.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
