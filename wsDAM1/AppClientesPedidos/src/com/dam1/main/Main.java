package com.dam1.main;

import com.dam1.entidades.Pedido;
import utilidadesTeclado.Teclado;
import com.dam1.entidades.Item;
import com.dam1.entidades.Cliente;

class Main {

	public static void main(String[] args) {
		
		int op=0;
		Pedido[] pedidos=new Pedido[100];
		int posPedidos=0, posClientes=0;
		
		Cliente[] clientes= new Cliente[100];
		
		do {
		
		System.out.println("_____________________________ \n");
		System.out.println("1. Crear pedido");
		System.out.println("2. Añadir item a pedido");
		System.out.println("3. Mostrar precio de un pedido");
		System.out.println("4. Mostrar precio de un pedido con IVA");
		System.out.println("5. Eliminar item");
		System.out.println("6. Mostrar elementos de un pedido");
		System.out.println("7. Fijar IVA");
		System.out.println("8. Incorporar pedido a cliente");
		System.out.println("9. Mostrar datos de un cliente que ha hecho un pedido");
		System.out.println("10. Insertar cliente");
		System.out.println("11. Mostrar pedidos de un cliente");
		System.out.println("");
		System.out.println("0. Salir \n");
		System.out.print("-->");
		
		op=Teclado.leerEntero();
		
			switch(op) {
				case 1: System.out.print("Codigo del pedido: ");
						String cod=Teclado.leerCadena();
						System.out.print("Descripcion del pedido: ");
						String desc=Teclado.leerCadena();
						pedidos[posPedidos++] = new Pedido(cod, desc);
						System.out.println("Pedido creado.");
					break;
				case 2:System.out.print("Codigo del pedido: ");
						cod=Teclado.leerCadena();
						System.out.print("Descripcion del item: ");
						desc=Teclado.leerCadena();
						System.out.print("Cantidad del item: ");
						int cant=Teclado.leerEntero();
						System.out.print("Precio/Unidad del item: ");
						double precio=Teclado.leerDecimal();
						for(int i=0;i<posPedidos;i++)
							if(pedidos[i].getCod().equals(cod)) {
								pedidos[i].addItem(new Item(desc, cant, precio));
								System.out.println("Item añadido con exito a "+pedidos[i].getCod());
							}
					break;
				case 3: System.out.print("Codigo del pedido: ");
						cod = Teclado.leerCadena();
						boolean pedidoExiste=false;
						for(int i=0;i<posPedidos;i++)
							if(pedidos[i].getCod().equals(cod)) {
								pedidoExiste=true;
								System.out.println("Precio total del pedido: "+pedidos[i].devolverPrecioTotal());
							}
						if(!pedidoExiste)
							System.out.println("El pedido no existe.");
						
					break;
				case 4: System.out.print("Codigo del pedido: ");
						cod = Teclado.leerCadena();
						pedidoExiste=false;
						for(int i=0;i<posPedidos;i++)
							if(pedidos[i].getCod().equals(cod)) {
								pedidoExiste=true;
								System.out.println("Precio total del pedido: "+pedidos[i].devolverPrecioTotalconIVA());
							}
						if(!pedidoExiste)
							System.out.println("El pedido no existe.");
				
					break;
				case 5: System.out.print("Codigo del pedido: ");
						cod=Teclado.leerCadena();
						System.out.print("Descripcion del item: ");
						desc=Teclado.leerCadena();
						for(int i=0;i<posPedidos;i++)
							if(pedidos[i].getCod().equals(cod)) {
								pedidos[i].eliminarItem(desc);
								System.out.println("Item eliminado con exito de "+pedidos[i].getCod());
							}
					break;
				case 6: System.out.print("Codigo del pedido: ");
						cod=Teclado.leerCadena();
						pedidoExiste=false;
						for(int i=0;i<posPedidos;i++)
							if(pedidos[i].getCod().equals(cod)) {
								pedidoExiste=true ;
//								for(Item item : pedidos[i].getItemsPedido()) {
//									System.out.println("Item "+i+": "+item.getDesc()+" ,cantidad"+": "+item.getCant()+" ,precio/Unidad"+": "+item.getPrecioUnidad());
//								}
								pedidos[i].mostrarItems();
							}
						if(!pedidoExiste)
							System.out.println("El pedido no existe.");
					break;
				case 7: System.out.print("Elige el IVA: ");
						int IVA= Teclado.leerEntero();
						Pedido.setIVA(IVA);
						System.out.println("IVA: "+Pedido.IVA);
					break;
				case 8: System.out.println("Codigo del pedido: ");
						cod=Teclado.leerCadena();
						System.out.println("NIF del cliente: ");
						String nif=Teclado.leerCadena();
						Pedido pedidoIncorporar= null;
						for(int i=0;i<posPedidos;i++)
							if(pedidos[i].getCod().equals(cod))
								pedidoIncorporar = pedidos[i];
						
						for(int i=0;i<posClientes;i++)
							if(clientes[i].getNif().equals(nif))
									clientes[i].incorporarPedido(pedidoIncorporar);
						
					break;
				case 9: System.out.println("Codigo del pedido: ");
						cod = Teclado.leerCadena();
						for(int i=0;i<posClientes;i++)
							if(clientes[i].tienePedido(cod))
								System.out.println("Este pedido lo pidio "+clientes[i].getNif()+" de nombre "+clientes[i].getNombre()+" y direccion: "+clientes[i].getDireccion()+".");	
					break;
				case 10: System.out.println("NIF del cliente: ");
						 nif= Teclado.leerCadena();
						 System.out.print("Nombre del cliente: ");
						 String nombre=Teclado.leerCadena();
						 System.out.print("Direccion del cliente: ");
						 String direccion=Teclado.leerCadena();
						 
						 clientes[posClientes++] = new Cliente(nif, nombre, direccion);
					break;
				case 11: System.out.println("NIF del cliente: ");
						 nif= Teclado.leerCadena();
						 for(int i=0;i< posClientes;i++)
							 if(clientes[i].getNif().equals(nif))
								clientes[i].mostrarDatos();
					break;
				case 0: System.out.println("Cerrando programa");
					break;
				default: System.out.println("Opcion incorrecta!");
			}//switch
			
		}while(op!=0);
		
	}

}
