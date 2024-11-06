package Ejercicio10;

public class Region {

	Pueblo[] pueblos = new Pueblo[3];

	public Region(Pueblo[] pueblos) {
		super();
		this.pueblos = pueblos;
	}

	public Region() {
	}

	public void fundaNuevoPueblo() {
		Pueblo pueblo = new Pueblo();
		
		for (int i = 0; i < pueblos.length; i++) {
			if(pueblos[i]!=null) {
				
			} else {
				pueblos[i]=pueblo;
				return;
			}
				
			
		}
		
		Pueblo[] aux = new Pueblo[pueblos.length * 2];
			for (int j = 0; j < pueblos.length; j++)
				aux[j] = pueblos[j];
			for (int k = 0; k < aux.length; k++)
				if (aux[k] == null) {
					aux[k] = pueblo;
					pueblos = aux;
					return;
				}
			

	}		
			
	

	public int cuentaCasas() {

		int cont = 0;
		for (int i = 0; i < pueblos.length; i++) {
			if(pueblos[i]!=null)
				cont += pueblos[i].getCasas().length;
		}
			

		return cont;
	}

	public int cuentaCasas(int codArea) {

		int cont = 0;
		for (int i = 0; i < pueblos.length; i++)
			if(pueblos[i]!=null)
				for (int j = 0; j < pueblos[i].getCasas().length; j++)
					if (pueblos[i].getCasas()[j].getCodArea() == codArea)
						cont++;

		return cont;
	}

	public void habitacionesEnLaRegion() {
		int suma = 0;
		for (int i = 0; i < pueblos.length; i++)
			if(pueblos[i]!=null)
				for (int j = 0; j < pueblos[i].getCasas().length; j++)
					suma += pueblos[i].getCasas()[j].numHabitaciones();

		System.out.println("Hay " + suma + " habitaciones en la region");
	}

}
