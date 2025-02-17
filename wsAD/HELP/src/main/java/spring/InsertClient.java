package spring;

import java.util.ArrayList;

/*
import org.springframework.web.reactive.function.client.WebClient;

import com.pancetas.apirest.models.Pedido;
import com.pancetas.apirest.models.Usuario;
*/
public class InsertClient {

	///PRUEBA DE APLICACION USANDO UN SERVICIO DE OTRA

	public static void main(String[] args) {
		/*
		 * WebClient client = WebClient.create("http://localhost:9090");
		 * 
		 * Usuario user = new Usuario(); user.setNombre("Maria");
		 * 
		 * Pedido p = new Pedido(); p.setDescripcion("Pedido de Maria");
		 * 
		 * if (user.getPedidos() == null) { user.setPedidos(new ArrayList<>());
		 * user.getPedidos().add(p); } else user.getPedidos().add(p);
		 * 
		 * Usuario response =
		 * client.post().uri("/usuarios").bodyValue(user).retrieve().bodyToMono(Usuario.
		 * class).block();
		 * 
		 * System.out.println(response.toString());
		 * 
		 * client.get().uri("/usuarios/users").retrieve().bodyToFlux(Usuario.class).
		 * toStream().forEach(System.out::println);
		 */
	}
}
