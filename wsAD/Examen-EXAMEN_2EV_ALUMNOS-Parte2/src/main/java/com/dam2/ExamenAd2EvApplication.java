package com.dam2;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.dam2.model.Usuario;
import com.dam2.repository.UsuarioRepository;
import com.dam2.service.PedidoService;


@SpringBootApplication
public class ExamenAd2EvApplication {

		
	public static void main(String[] args) {
		ApplicationContext context = 
				SpringApplication.run(ExamenAd2EvApplication.class, args);
		
		
		//AQUÍ LAS LLAMADAS A LOS MÉTODOS DEL SERVICIO
		PedidoService pedService = context.getBean(PedidoService.class);
		
		
		
//		pedService.insertaPedido(2);
		/*
		pedService.listaProductos(1).forEach(p -> {
			System.out.println(p.getProducto().getNombre() +", "+p.getCantidad()+" u");
		});
		*/
		
		System.out.println(pedService.pedidoMasCaro().toString());
		
	}
	
	

}
