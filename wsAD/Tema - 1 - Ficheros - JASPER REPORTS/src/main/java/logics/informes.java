package logics;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import DatosAlumno.datosAlumno;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class informes {

	public static void genInforme(LinkedList<datosAlumno> datos) throws JRException {

//		String plantilla;
		String ficheroJasper = "reports/listadoAlumnos.jasper";
		String informePdf = "reports/listadoAlumnos.pdf";

		// Transformamos los datos a una fuente de datos que JasperReports entiende
		JRBeanCollectionDataSource camposInforme = new JRBeanCollectionDataSource(datos);

		// Compilamos la plantilla
//		JasperReport jasperReport = JasperCompileManager.compileReport(plantilla);

		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(ficheroJasper);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("grupo", "2 DAM");
		// Rellenamos el informe con la fuente de datos
		JasperPrint informe = JasperFillManager.fillReport(jasperReport, params, camposInforme);

		// Exportamos a pdf
		JasperExportManager.exportReportToPdfFile(informe, informePdf);

	}
}
