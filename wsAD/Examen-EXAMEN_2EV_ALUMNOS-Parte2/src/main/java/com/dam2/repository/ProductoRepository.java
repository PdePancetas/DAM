package com.dam2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam2.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
