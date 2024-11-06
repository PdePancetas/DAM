package Ejercicio2;

public class ManejadorTriangulos extends ManejadorFiguras {
	
	
	public ManejadorTriangulos(FiguraGeometrica[] figuras) {	
		super(figuras);
	}
	public ManejadorTriangulos() {	
	}
	
	public double areaTotalTriangulos() {
		
		double areaTotal=0;
		for(int i=0;i<this.figuras.length;i++)
			if(figuras[i] instanceof Triangulo) {
				double s=(((Triangulo) figuras[i]).getLado1()+((Triangulo) figuras[i]).getLado2()+((Triangulo) figuras[i]).getLado3())/2;
				double area=Math.sqrt(s*(s-((Triangulo) figuras[i]).getLado1()*(s-((Triangulo) figuras[i]).getLado2())*(s-((Triangulo) figuras[i]).getLado3())));
				
				areaTotal+= area;
			}
		return areaTotal;
	}
	
}
