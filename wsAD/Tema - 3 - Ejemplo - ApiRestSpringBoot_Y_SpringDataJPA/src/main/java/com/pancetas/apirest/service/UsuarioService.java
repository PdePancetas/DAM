package com.pancetas.apirest.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pancetas.apirest.models.Usuario;
import com.pancetas.apirest.repository.PedidoRepository;
import com.pancetas.apirest.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository userRepo;
	@Autowired
	PedidoRepository pedRepo;

	public List<Usuario> getAllUsers() {
		return userRepo.findAll();
	}

	public Usuario saveUser(Usuario usuario) {
		return userRepo.save(usuario);
	}

	public Usuario getUser(Long i) {
		return userRepo.getReferenceById(i);
	}

}
