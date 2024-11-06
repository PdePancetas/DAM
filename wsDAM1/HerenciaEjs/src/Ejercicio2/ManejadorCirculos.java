package Ejercicio2;

public class ManejadorCirculos extends ManejadorFiguras {
	
	
	public ManejadorCirculos(FiguraGeometrica[] figuras) {	
		super(figuras);
	}
	public ManejadorCirculos() {	
	}
	
	public double areaTotalCirculos() {
		
		double areaTotal=0;
		for(int i=0;i<this.figuras.length;i++)
			if(figuras[i] instanceof Circulo)
				areaTotal+= Math.PI*((Circulo) figuras[i]).getRadio()*((Circulo) figuras[i]).getRadio();
				
		return areaTotal;
	}
	
}
