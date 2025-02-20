package tests;

import org.springframework.web.reactive.function.client.WebClient;

import com.pancetas.apirest.dto.PedidoRequest;
import com.pancetas.apirest.dto.PedidoResponse;

import utilidadesTeclado.Teclado;

public class InsertPedido {
	public static void main(String[] args) {
		
		WebClient client = WebClient.create("http://localhost:9090");

		// 2. Construir el objeto DTO
		
		System.out.print("Id de usuario: ");
		int id = Teclado.leerEntero();
		
		System.out.print("Descripcion del pedido: ");
		
		String desc = Teclado.leerCadena();
		
		PedidoRequest request = new PedidoRequest();
		request.setDescripcion(desc);
		request.setUsuarioId(Long.valueOf(id));

		// 3. Hacer la petición POST a "/pedidos/create"
		PedidoResponse response = client.post().uri("/pedidos/createPedido").bodyValue(request).retrieve()
				.bodyToMono(PedidoResponse.class) 
				.doOnError(e -> System.err.println("Error en la petición: " + e.getMessage())).block(); 

		// 4. Mostrar la respuesta
		if (response != null) {
			System.out.println("Nuevo pedido creado: ");
			System.out.println("ID: " + response.getId());
			System.out.println("Descripcion: " + response.getDescripcion());
			System.out.println("Usuario ID: " + response.getUsuarioId());
		} else {
			System.err.println("No se recibió respuesta del servidor.");
		}

		System.out.println("Pedidos existentes: \n");
		client.get().uri("/pedidos/listPedidos").retrieve().bodyToFlux(PedidoResponse.class) // O Pedido.class
				.toStream().forEach(p -> System.out.println("Pedido "+p.getId()+": " + p.getDescripcion()));
	}
}
