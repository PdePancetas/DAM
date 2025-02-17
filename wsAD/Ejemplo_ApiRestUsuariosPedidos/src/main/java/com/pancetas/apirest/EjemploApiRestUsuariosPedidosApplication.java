package com.pancetas.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.pancetas.apirest.controller.UsuarioController;
import com.pancetas.apirest.repository.PedidoRepository;
import com.pancetas.apirest.repository.UsuarioRepository;
import com.pancetas.apirest.service.PedidoService;
import com.pancetas.apirest.service.UsuarioService;

@SpringBootApplication
public class EjemploApiRestUsuariosPedidosApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(EjemploApiRestUsuariosPedidosApplication.class, args);

		UsuarioRepository userRepo = context.getBean(UsuarioRepository.class, args);
		PedidoRepository pedRepo = context.getBean(PedidoRepository.class, args);

		UsuarioService userService = new UsuarioService();
		PedidoService pedService = new PedidoService();

		UsuarioController userControl = new UsuarioController();

	}

}
