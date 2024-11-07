package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.json.JSONArray;

import beans.Pedido;
import beans.Producto;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class Func {

	public static void genInforme(Pedido pedido) throws JRException {

		String ficheroJasper = "C:\\Users\\Alumno\\JaspersoftWorkspace\\MyReports\\listadoPedidos.jasper";
		String informePdf = "reports\\pedidos.pdf";

		JRBeanCollectionDataSource camposInforme = new JRBeanCollectionDataSource(pedido.getProductos());


		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(ficheroJasper);

		Map<String, Object> params = new HashMap<String, Object>();
		
		
		JasperPrint informe = JasperFillManager.fillReport(jasperReport, params, camposInforme);

		
		JasperExportManager.exportReportToPdfFile(informe, informePdf);

	}
	
	public static JSONArray leerFicheroJSON(String nombreFichero) throws IOException {

		String texto = "";
		String linea;
		InputStream is = Main.class.getClassLoader().getResourceAsStream(nombreFichero);
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			while ((linea = br.readLine()) != null)
				texto += linea;
		}

		JSONArray pedidos = new JSONArray(texto);

		return pedidos;
	}

	
	
}
