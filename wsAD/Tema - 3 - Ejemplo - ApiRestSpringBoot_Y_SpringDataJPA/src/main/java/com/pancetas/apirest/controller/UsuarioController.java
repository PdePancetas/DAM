package com.pancetas.apirest.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pancetas.apirest.models.Usuario;
import com.pancetas.apirest.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService userService;
	
	@GetMapping("/users")
	public List<Usuario> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@PostMapping("/create")
	public Usuario createUser(@RequestBody Usuario user) {
		return userService.saveUser(user);
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Usuario getUser(@PathVariable Long id) {
	    return userService.getUser(id);
	}
	
	
}
