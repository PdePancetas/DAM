package com.pancetas.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pancetas.apirest.models.Pedido;
import com.pancetas.apirest.models.Usuario;
import com.pancetas.apirest.repository.PedidoRepository;
import com.pancetas.apirest.repository.UsuarioRepository;

@Service
public class PedidoService {

	@Autowired
	UsuarioRepository userRepo;
	@Autowired
	PedidoRepository pedRepo;
	
	public List<Pedido> getAllPedidos() {
		return pedRepo.findAll();
	}

	public Pedido savePedido(Pedido ped) {
		return pedRepo.save(ped);
	}

	public void deletePedido(String desc, Long userId) {
		pedRepo.deleteByDescripcionAndUsuarioId(desc,userId);
	}

}
