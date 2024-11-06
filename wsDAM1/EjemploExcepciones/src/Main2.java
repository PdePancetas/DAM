import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main2 {
	
	public static void main(String[] args) throws ParseException {
		
		String strDateFormat = "dd-MM-yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		
		Factura f = new Factura(1,sdf.parse("17-04-2024"));
		
		try {
			mostrarDatosFactura(f);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PosteriorDateException e) {
			
			System.out.println("La factura tiene una fecha superior, estas fechas no se imprimen.");
		}
		System.out.println("Adios");
	}
	
	static void mostrarDatosFactura(Factura f) throws PosteriorDateException {
		Date fechaActual = new Date();
		
		String strDateFormat = "dd-MM-yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		
		if(f.getFecha().after(fechaActual)) {
			throw new PosteriorDateException();
		} else {
			System.out.println("Factura: N� "+f.getNumero()+" a "+sdf.format(f.getFecha()));
		}
		
	}
}
