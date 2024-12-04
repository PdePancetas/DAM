package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import beans.Datos;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import properties.Properties;

public class IO {

	public static File getFichero() throws FileNotFoundException, IOException {
		File f = new File(Properties.getConfig().getProperty("fichero"));
		return f;
	}

	public static Document leerFicheroXML()
			throws ParserConfigurationException, FileNotFoundException, SAXException, IOException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		return db.parse(getFichero());
	}

	private static void escribirFicheroXML(Document doc)
			throws FileNotFoundException, IOException, TransformerFactoryConfigurationError, TransformerException {

		DOMSource source = new DOMSource(doc);

		StreamResult result = new StreamResult(getFichero());

		Transformer transformer;
		transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		transformer.transform(source, result);
	}

	public static Datos leerFichero(File f) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(Datos.class);

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		Datos pisos = (Datos) unmarshaller.unmarshal(f);

		return pisos;
	}

	public static void escribirFichero(Datos pisos) throws JAXBException, FileNotFoundException, IOException {

		JAXBContext jaxbContext = JAXBContext.newInstance(Datos.class);

		Marshaller marshaller = jaxbContext.createMarshaller();

		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		marshaller.marshal(pisos, getFichero());
	}
	
	public static void genInforme(Datos pisos) throws JRException {

		String ficheroJasper = Properties.getConfig().getProperty("ficheroJasper");
		String informePdf = Properties.getConfig().getProperty("ficheroPdf");

		JRBeanCollectionDataSource camposInforme = new JRBeanCollectionDataSource(pisos.getPisos());


		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(ficheroJasper);

		Map<String, Object> params = new HashMap<String, Object>();

		JasperPrint informe = JasperFillManager.fillReport(jasperReport, params, camposInforme);

		JasperExportManager.exportReportToPdfFile(informe, informePdf);

	}

}
