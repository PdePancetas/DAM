package com.pancetas.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pancetas.apirest.models.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {


	@Transactional
	void deleteByDescripcionAndUsuarioId(@Param("desc") String desc, @Param("userId") Long userId);
}
