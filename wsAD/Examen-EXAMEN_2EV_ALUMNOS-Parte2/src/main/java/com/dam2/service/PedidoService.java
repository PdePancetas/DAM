package com.dam2.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dam2.model.DetallePedido;
import com.dam2.model.Pedido;
import com.dam2.model.Usuario;
import com.dam2.repository.PedidoRepository;
import com.dam2.repository.ProductoRepository;
import com.dam2.repository.UsuarioRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedRepo;

	@Autowired
	ProductoRepository prodRepo;

	@Autowired
	UsuarioRepository userRepo;

	/**
	 * Método que inserta un pedido (sin productos) para el usuario cuyo id se
	 * indica
	 * 
	 * @param idUsu Id del usuario para el que se inserta pedido nuevo. La fecha del
	 *              pedido será la actual. NOTA: un objeto LocalDateTime con la
	 *              fecha actual se puede crear con: LocalDateTime.now()
	 */
	public void insertaPedido(int idUsu) {

		Pedido p = new Pedido();

		Usuario u = userRepo.getReferenceById(idUsu);
		p.setUsuario(u);
		p.setFechaPedido(LocalDateTime.now());

		pedRepo.save(p);
		
		System.out.println("Pedido insertado");
	}

	/**
	 * Método que lista los productos de un pedido, tanto su nombre como cantidad.
	 * 
	 * @param idPed Id del pedido del cuál se listarán los productos.
	 */
	@Transactional // 🔥 Esto mantiene la sesión abierta hasta que termine el método
	public List<DetallePedido> listaProductos(Integer idPedido) {
		
		Pedido pedido = pedRepo.findById(idPedido)
				.orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

		return pedido.getDetalles();
	}

	/**
	 * Método que lista el pedido cuyos productos suman más cantidad
	 * 
	 * @return El objeto Pedido más caro
	 */
	public Pedido pedidoMasCaro() {

		List<Pedido> pedidos = pedRepo.findAll();

		Pedido masCaro = pedidos.stream()
				.max(Comparator.comparingDouble(p -> p.getDetalles().stream()
						.mapToDouble(detalles -> detalles.getProducto().getPrecio()
								* detalles.getCantidad()).sum()))
				.get();

		return masCaro;
	}

}
