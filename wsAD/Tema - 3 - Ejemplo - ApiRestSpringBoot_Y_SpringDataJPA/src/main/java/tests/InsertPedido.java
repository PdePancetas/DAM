package tests;

import java.math.BigInteger;

import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pancetas.apirest.models.Pedido;
import com.pancetas.apirest.models.Usuario;

public class InsertPedido {

	public static void main(String[] args) throws JsonProcessingException {
		WebClient client = WebClient.create("http://localhost:9090");

		Usuario user = client.get().uri("/usuarios/1") // Ajusta la URI según la API
				.retrieve().bodyToMono(Usuario.class)
				.doOnError(e -> System.err.println("Error en la petición: " + e.getMessage())).block(); // Bloquea hasta
																										// que obtenga
																										// el usuario
		System.out.println(user.toString());
		Pedido p = new Pedido();

		p.setUsuario(user);
		p.setDescripcion("Pedido Extra del 1");
		
		System.out.println(p.getUsuario());
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonPedido = objectMapper.writeValueAsString(p);
		System.out.println("JSON enviado: " + jsonPedido);
		
		
		
		Pedido response = client.post().uri("/pedidos/create").bodyValue(p).retrieve().bodyToMono(Pedido.class)
				.doOnError(e -> System.err.println("Error en la petición: " + e.getMessage())).block();

		System.out.println(response.toString());

		client.get().uri("/pedidos/listPedidos").retrieve().bodyToFlux(Pedido.class).toStream()
				.forEach(System.out::println);
	}
}
