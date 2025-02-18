package spring;

import java.util.LinkedList;

import org.springframework.web.reactive.function.client.WebClient;

import com.pancetas.apirest.models.Pedido;
import com.pancetas.apirest.models.Usuario;


public class UsuarioClient {
	
	///IGUAL QUE INSERTCLIENT, PERO ESTE ES EL EJEMPLO DE LA PROFESORA
	
	
    public static void main(String[] args) {
        WebClient webClient = WebClient.create("http://localhost:8080");

        Usuario usuario = new Usuario();
        usuario.setNombre("Ana");
        Pedido p = new Pedido();
        p.setDescripcion("cuadernos");
        if(usuario.getPedidos() == null)
        	usuario.setPedidos(new LinkedList<Pedido>());
        usuario.getPedidos().add(p);

        Usuario response = webClient.post()
                .uri("/usuarios")
                .bodyValue(usuario)
                .retrieve()
                .bodyToMono(Usuario.class)
                .block();  // Bloquea hasta obtener respuesta

        System.out.println("Usuario creado: " + response.getNombre());
    }
}
