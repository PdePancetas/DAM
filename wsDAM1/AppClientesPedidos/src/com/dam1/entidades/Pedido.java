package com.dam1.entidades;

public class Pedido {

	private String cod, desc;
	private Item[] itemsPedido = new Item[0];
	public static int IVA;
	
	
	
	
	public Pedido(String cod, String desc, Item[] itemsPedido) {

		this.cod = cod;
		this.desc = desc;
		this.itemsPedido =itemsPedido;
	}
	public Pedido(){
	
	}
	//Cuando una clase tiene una propieda que es
	//un conjunto de otras cosas, es habitual crear
	// un constructor que no inicialice esta propiedad,
	//de tal manera que ese conjunto se vaya llenando posteriormente.
	public Pedido(String cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public Item[] getItemsPedido() {
		return itemsPedido;
	}
	public void setItemsPedido(Item[] itemsPedido) {
		this.itemsPedido = itemsPedido;
	}

	public void addItem(Item item){
		
		Item[] nuevo = new Item[itemsPedido.length+1];
		for (int i = 0; i < itemsPedido.length; i++)
			nuevo[i] = itemsPedido[i];
		nuevo[nuevo.length-1] = item;
		itemsPedido=nuevo;
	}
	public void eliminarItem(String desc){
		//elimina del array itemsPedido el item cuya descripcion 
		//es igual al parámetro pasado.
		
		Item[] aux= new Item[itemsPedido.length-1];
		
		for(int i=0,j=0;i<itemsPedido.length;i++)
			if(!itemsPedido[i].getDesc().equals(desc))
				aux[j++]= itemsPedido[i];

		itemsPedido = aux;
		
		
	}
	public void mostrarItems() {
		System.out.println("Pedido: "+desc+"\n");
		for(Item item : itemsPedido)
			System.out.println("\t Item "+item.getDesc()+", cantidad"+": "+item.getCant()+", precio/Unidad"+": "+item.getPrecioUnidad());
	}
	public double devolverPrecioTotalconIVA(){
		
		double precioTotal=0;
		
		for(Item i: itemsPedido)
			precioTotal+= i.getPrecioUnidad() * i.getCant();
		
		return precioTotal +precioTotal*IVA/100;
	}
	public double devolverPrecioTotal(){
		
		double precioTotal=0;
		
		for(Item i: itemsPedido)
			precioTotal+= i.getPrecioUnidad() * i.getCant();
		
		return precioTotal;
	}
	public static void setIVA(int IVA){
		Pedido.IVA=IVA;
	}

}
