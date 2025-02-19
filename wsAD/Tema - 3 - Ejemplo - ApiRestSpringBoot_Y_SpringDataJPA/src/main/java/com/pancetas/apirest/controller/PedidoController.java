package com.pancetas.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pancetas.apirest.dto.PedidoRequest;
import com.pancetas.apirest.models.Pedido;
import com.pancetas.apirest.models.Usuario;
import com.pancetas.apirest.repository.UsuarioRepository;
import com.pancetas.apirest.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedService;
	
	@Autowired
	private UsuarioRepository userRepo;
	
	@GetMapping("/listPedidos")
	public List<Pedido> getAllPedidos() {
		return pedService.getAllPedidos();
	}
	
	@PostMapping("/create")
    public ResponseEntity<Pedido> createPedido(@RequestBody PedidoRequest pedidoRequest) {
        // 1. Buscar el usuario en la BD para que sea una entidad "managed" por JPA
        Usuario usuario = userRepo.findById(pedidoRequest.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario con ID " 
                                      + pedidoRequest.getUsuarioId() + " no existe."));

        // 2. Crear el Pedido a partir del DTO
        Pedido pedido = new Pedido();
        pedido.setDescripcion(pedidoRequest.getDescripcion());
        pedido.setUsuario(usuario);

        // 3. Guardar el pedido
        Pedido savedPedido = pedService.savePedido(pedido);

        return ResponseEntity.ok(savedPedido);
    }
}
