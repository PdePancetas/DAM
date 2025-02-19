package tests;

import org.springframework.web.reactive.function.client.WebClient;

import com.pancetas.apirest.dto.PedidoRequest;
import com.pancetas.apirest.dto.PedidoResponse;

public class InsertPedido2 {
	public static void main(String[] args) {
		// 1. Crear un WebClient apuntando a tu API
		WebClient client = WebClient.create("http://localhost:9090");

		// 2. Construir el objeto DTO que enviarás al endpoint
		PedidoRequest request = new PedidoRequest();
		request.setDescripcion("Pedido Extra del 1");
		request.setUsuarioId(1L); // ID del usuario que ya existe en BD

		// 3. Hacer la petición POST a "/pedidos/create"
		PedidoResponse response = client.post().uri("/pedidos/create").bodyValue(request).retrieve()
				.bodyToMono(PedidoResponse.class) // O Pedido.class, si tu endpoint devuelve la entidad
				.doOnError(e -> System.err.println("Error en la petición: " + e.getMessage())).block(); // Bloquea hasta
																										// obtener la
																										// respuesta

		// 4. Mostrar la respuesta
		if (response != null) {
			System.out.println("Nuevo pedido creado: ");
			System.out.println("ID: " + response.getId());
			System.out.println("Descripcion: " + response.getDescripcion());
			System.out.println("Usuario ID: " + response.getUsuarioId());
		} else {
			System.err.println("No se recibió respuesta del servidor.");
		}

		// 5. (Opcional) Obtener la lista de pedidos para verificar
		client.get().uri("/pedidos/listPedidos").retrieve().bodyToFlux(PedidoResponse.class) // O Pedido.class
				.toStream().forEach(p -> System.out.println("Pedido listado: " + p.getDescripcion()));
	}
}
