package Ejercicio2;

public class ManejadorCuadrados extends ManejadorFiguras {
	
	
	public ManejadorCuadrados(FiguraGeometrica[] figuras) {	
		super(figuras);
	}
	public ManejadorCuadrados() {	
	}
	
	public double areaTotalCuadrados() {
		
		double areaTotal=0;
		for(int i=0;i<this.figuras.length;i++)
			if(figuras[i] instanceof Cuadrado)
				areaTotal+= ((Cuadrado) figuras[i]).getLado()*((Cuadrado) figuras[i]).getLado();
				
		
		return areaTotal;
	}
	
}
