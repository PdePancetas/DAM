package logic;

import java.util.LinkedList;

import beans.Cuenta;

public class Func {

	public static double mostrarDineroTotal(String nombreTitular, LinkedList<Cuenta> cuentas) {
		double dineroTotal=0;
		for(Cuenta c: cuentas) {
			for(String titular: c.getTitulares())
				if(titular.equals(nombreTitular))
					dineroTotal+=c.getCantidad();
		}
		
		return dineroTotal;
	}

}
