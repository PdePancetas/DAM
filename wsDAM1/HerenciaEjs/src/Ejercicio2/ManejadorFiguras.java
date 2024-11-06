package Ejercicio2;

public class ManejadorFiguras {
	
	protected FiguraGeometrica[] figuras;
	
	
	public FiguraGeometrica[] getFiguras() {
		return figuras;
	}
	public void setFiguras(FiguraGeometrica[] figuras) {
		this.figuras = figuras;
	}
	
	public ManejadorFiguras() {
	}
	public ManejadorFiguras(FiguraGeometrica[] figuras) {
		this.figuras=figuras;
	}
	
	public double calcularPerimetroTotal() {
		double perimetroTotal=0;
		for(int i=0;i<figuras.length;i++)
			perimetroTotal+= calcularPerimetroFigura(figuras[i]);
		
		return perimetroTotal;
	}
	private static double calcularPerimetroFigura(FiguraGeometrica f) {
		
		if(f instanceof Triangulo)
			return ((Triangulo) f).getLado1()+((Triangulo) f).getLado2()+((Triangulo) f).getLado3();
		else if(f instanceof Cuadrado)
			return ((Cuadrado) f).getLado()*4;
		else
			return 2*Math.PI*((Circulo) f).getRadio();
	}
	
	
}
