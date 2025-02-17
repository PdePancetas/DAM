package com.pancetas.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pancetas.apirest.repository.PedidoRepository;
import com.pancetas.apirest.repository.UsuarioRepository;

@Service
public class PedidoService {

	@Autowired
	UsuarioRepository userRepo;
	@Autowired
	PedidoRepository pedRepo;
	
	
	
}
