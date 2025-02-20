package com.pancetas.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pancetas.apirest.dto.PedidoRequest;
import com.pancetas.apirest.dto.PedidoResponse;
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
	public ResponseEntity<StringBuilder> getAllPedidos() {
		
		StringBuilder pedidos = new StringBuilder();
		pedidos.append("<html><body><ul>");
		pedService.getAllPedidos().stream().forEach(p -> pedidos.append("<li>"+p.toString()+"</li>"));
		pedidos.append("</ul></body></html>");
		
		return 	ResponseEntity.ok(pedidos);
	}
		
	@PostMapping("/createPedido")
    public ResponseEntity<PedidoResponse> createPedido(@RequestBody PedidoRequest request) {
        // 1. Buscar el usuario en la BD
        Usuario usuario = userRepo.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        // 2. Crear el pedido
        Pedido pedido = new Pedido();
        pedido.setDescripcion(request.getDescripcion());
        pedido.setUsuario(usuario); // Asegurarte de que no sea null

        Pedido ped = pedService.savePedido(pedido);

        // 4. Construir la respuesta con el constructor que asigna usuarioId
        return ResponseEntity.ok(new PedidoResponse(ped));
    }
	
	@GetMapping("/create")
	public ResponseEntity<PedidoResponse> createPedido(@RequestParam String desc, @RequestParam Long userId) {
		
		// 1. Buscar el usuario en la BD
        Usuario usuario = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        // 2. Crear el pedido
        Pedido pedido = new Pedido();
        pedido.setDescripcion(desc);
        pedido.setUsuario(usuario); // Asegurarte de que no sea null

        Pedido ped = pedService.savePedido(pedido);

        // 4. Construir la respuesta con el constructor que asigna usuarioId
        return ResponseEntity.ok(new PedidoResponse(ped));
		
	}
	
	@GetMapping("/delete")
	public ResponseEntity<String> deletePedido(@RequestParam String desc, @RequestParam Long userId) {
	    pedService.deletePedido(desc, userId);
	    return ResponseEntity.ok("<html><body><h1>Pedido eliminado con éxito</h1></body></html>");
	}
}
