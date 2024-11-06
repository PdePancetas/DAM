package Ejercicio10;

public class Estado {

	public static void main(String[] args) {

		Casa c1 = new Casa("Calle Marques", 2, 7, 45, 2);
		Casa c2 = new Casa("Calle Pedraza", 9, 5, 59, 4);
		Casa c3 = new Casa("Calle Campos", 5, 5, 13, 3);
		Casa c4 = new Casa("Calle Margarita", 16, 7, 30, 1);

		 ////1.  PROBANDO CONSTRUYECASA(c)
		Pueblo p1 = new Pueblo();
		p1.construyeCasa(c1);
		p1.construyeCasa(c2);
		System.out.println("casas de p1: " + p1.getCasas().length);
		System.out.println("_________________________________________________________________");

		////2.  PROBANDO DIRECCION() Y NUMHABITACIONES()
		System.out.print("Datos c1: ");
		p1.getCasas()[0].direccion();
		System.out.println("Nº Habitaciones de c2: " + p1.getCasas()[1].numHabitaciones());
		System.out.println("_________________________________________________________________");

		////3.  PROBANDO ESTAENAREA(codArea)
		System.out.println("c1 esta en area 5? " + p1.getCasas()[0].estaEnArea(5));
		System.out.println("c1 esta en area 6? " + p1.getCasas()[0].estaEnArea(6));
		System.out.println("_________________________________________________________________");

		////4.  PROBANDO DESTRUYECASA(c)
		System.out.println("Numero de casas de p1: " + p1.getCasas().length);
		p1.destruyeCasa(c1);
		System.out.println("Numero de casas de p1 (destruyendo c1): " + p1.getCasas().length);
		System.out.println("_________________________________________________________________");

		////5.  CREO OTRO PUEBLO CON VARIAS CASAS
		Pueblo p2 = new Pueblo();
		p2.construyeCasa(c3);
		p2.construyeCasa(c4);

		////6.  PROBANDO SUPERFICIE CONSTRUIDA()
		System.out.println("Superficie construida de p2: " + p2.superficieConstruida());
		System.out.println("_________________________________________________________________");

		////7.  CREO REGION Y PRUEBO FUNDANUEVOPUEBLO()
		Region r1 = new Region();
		r1.fundaNuevoPueblo();
		r1.fundaNuevoPueblo();
		r1.fundaNuevoPueblo();
		r1.fundaNuevoPueblo();
		r1.fundaNuevoPueblo();
		r1.fundaNuevoPueblo();
		r1.fundaNuevoPueblo();
		r1.fundaNuevoPueblo();
		r1.fundaNuevoPueblo();
		r1.fundaNuevoPueblo();

		/*
		 * Se puede comprobar que funciona en 
		 * el apartado variables al hacer debug
		 */
		
		///8.  CREO OTRA REGION Y LE ASIGNO P1 Y P2
		Region r2 = new Region();
		r2.pueblos[0] = p1;
		r2.pueblos[1] = p2;

		////9.  PROBANDO CUENTACASAS() y CUENTACASAS(codArea)
		System.out.println("Nº de casas de r2: " + r2.cuentaCasas());
		System.out.println("Nº de casas en el area 5: " + r2.cuentaCasas(5));
		
		/*
		 * Si hubiera puesto codArea 7, solo habría una (c4) ya que c1 se destruyó
		 * anteriormente
		 */

		System.out.println("_________________________________________________________________");

		////10. PROBANDO HABITACIONESENLAREGION()
		r2.habitacionesEnLaRegion();
		
		

	}

}
