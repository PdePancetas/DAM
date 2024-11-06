package Ejercicio9;

import utilidadesTeclado.Teclado;

public class Salmones {
	
	private Salmon[] salmones;
	
	public Salmones() {
		
	}
	public Salmones(int n) {
		salmones= new Salmon[n];
	}
	
	public Salmon[] getSalmones() {
		return salmones;
	}
	public void setSalmones(Salmon[] salmones) {
		this.salmones = salmones;
	}
	
	public void todosNadan() {
		
		for(int i=0;i<salmones.length;i++)
			salmones[i].nadar();	
	}
	public void salmonMeeting() {
		for(int i=0;i<salmones.length;i++)
			System.out.println(salmones[i].describirSalmon()+" y soy un salmon");	
	}

	public static void main(String[] args) {
		
		System.out.print("Numero de salmones: ");
		int n=Teclado.leerEntero();
		Salmones grupoSalmon= new Salmones(n);
		for(int i=0;i<grupoSalmon.getSalmones().length;i++) {
			System.out.print("Nombre salmon "+(i+1)+": ");
			String nombre=Teclado.leerCadena();
			
			System.out.print("Peso salmon "+(i+1)+": ");
			double peso=Teclado.leerDecimal();
			
			System.out.print("Region salmon "+(i+1)+": ");
			String region=Teclado.leerCadena();

			grupoSalmon.getSalmones()[i] = new Salmon(nombre, peso, region);
			System.out.println();
		}
		grupoSalmon.todosNadan();
		System.out.println();
		grupoSalmon.salmonMeeting();
			
	}
}
