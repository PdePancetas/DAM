package logic;

import net.sf.jasperreports.engine.JRException;

public class Main {

	public static void main(String[] args) {


		
		Func.eliminaUsuario(1);
		
		Func.intercambiado(101);
		
		try {
			Func.generaInforme(1);
		} catch (JRException e) {
			e.printStackTrace();
		}
		
	}

}
