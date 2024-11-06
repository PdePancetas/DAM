package com.dam1.entidades;
public class Cliente {

	
	private String nombre, direccion, nif;
	private Pedido[] pedidos;

	public Cliente(String nombre, String direccion, String nif, Pedido[] pedidos) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.nif = nif;
		this.pedidos=pedidos;
	}
	public Cliente() {
	}
	public Cliente(String nif, String nombre, String direccion) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.nif = nif;
	}
	
	public Pedido[] getPedidos() {
		return pedidos;	
	}
	public void setPedidos(Pedido[] pedidos) {
		this.pedidos=pedidos;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	
	public void emitirFactura() {
		
		System.out.println("NIF del cliente "+this.nif);
		System.out.println("Nombre del cliente "+this.nombre);
		System.out.println("Direccion del cliente "+this.direccion);
		System.out.println("Factura: ");
		
		double sumTotal=0;
		double sumTotIva=0;
		
		for(int i=0;i<pedidos.length;i++) {
			pedidos[i].mostrarItems();
			System.out.println();
			sumTotal+=pedidos[i].devolverPrecioTotal();
			sumTotIva+=pedidos[i].devolverPrecioTotalconIVA();
		}
		
		System.out.println("Precio total sin IVA: "+sumTotal);	
		System.out.println("precio total con IVA: "+sumTotIva);
	}	
	public void incorporarPedido(Pedido p) {
		if(pedidos==null) {
			pedidos= new Pedido[1];
			pedidos[0]=p;
			return;
		}
		
		Pedido[] aux= new Pedido[pedidos.length+1];
		for(int i=0;i< pedidos.length;i++)
			aux[i]= pedidos[i];
		
		aux[aux.length-1]=p;
		pedidos=aux;
		
		
	}
	public boolean tienePedido(String cod) {
		
		boolean result=false;
		
		for(int i=0;i<pedidos.length&&!result;i++)
			if(pedidos[i].getCod().equals(cod))
				result=true;
		
		return result;
	}
	public void mostrarDatos() {
		System.out.println("NIF: "+nif);
		System.out.println("Nombre: "+nombre);
		System.out.println("Direccion: "+direccion);
		System.out.println("Pedidos hechos por "+nif+": ");
		if(pedidos==null)
			System.out.println("\n No tiene pedidos a su nombre.");
		else {
			for(Pedido p: pedidos) {
				
				p.mostrarItems();
			}
		}
			
		
	}
	
}
