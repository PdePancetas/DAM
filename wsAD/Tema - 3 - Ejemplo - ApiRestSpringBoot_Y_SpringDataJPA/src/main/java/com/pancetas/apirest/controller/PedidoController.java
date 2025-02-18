package com.pancetas.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pancetas.apirest.models.Pedido;
import com.pancetas.apirest.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedService;
	
	@GetMapping("/listPedidos")
	public List<Pedido> getAllPedidos() {
		return pedService.getAllPedidos();
	}
	
	@PostMapping("/create")
	public Pedido createPedido(@RequestBody Pedido ped) {
		return pedService.savePedido(ped);
	}
	
	
}
