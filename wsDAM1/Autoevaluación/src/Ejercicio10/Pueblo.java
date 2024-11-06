package Ejercicio10;

public class Pueblo {
	
	Casa[] casas= new Casa[0];
	
	
	
	public Pueblo(Casa[] casas) {
		super();
		this.casas = casas;
	}
	public Pueblo() {
	}

	public Casa[] getCasas() {
		return casas;
	}
	public void setCasas(Casa[] casas) {
		this.casas = casas;
	}
	
	
	public void construyeCasa(Casa c) {
		
		Casa[] aux= new Casa[casas.length+1];
		for(int i=0;i<casas.length;i++)
			aux[i]=casas[i];
		aux[aux.length-1] = c;
		casas=aux;
	}	
	public void destruyeCasa(Casa c) {
		
		Casa[] aux= new Casa[casas.length-1];
		for(int i=0;i<casas.length;i++) {
			if(casas[i].equals(c)) {
				casas[i]=null;
				for(int j=i;j<aux.length;j++)
					aux[j]=casas[j+1];	
			}
		}
		casas=aux;
	}	

	public double superficieConstruida() {
		
		int suma=0;
		for(int i=0;i<casas.length;i++)
			suma+=casas[i].getSuperficie();
		
		return suma;
	}
	
	
}
