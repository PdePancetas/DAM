package com.pancetas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Servlet implementation class Upload
 */
//@WebServlet("/upload")
@MultipartConfig
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadFileServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Sacar el part del fichero
		Part filePart = request.getPart("file");
		// Asociar un flujo a la parte
		InputStream is = filePart.getInputStream();
		// Sacar el nombre del archivo
//		String fileName = filePart.getSubmittedFileName();
		String fileName = getSubmittedFileName(filePart);
		//Me quedo solo con el nombre del archivo, sin la ruta local
		if(fileName.contains(File.separator)) {
			int index = fileName.lastIndexOf(File.separator);
			fileName = fileName.substring(index+1);
		}
		
		
		//Ruta donde se guardaran los archivos subidos al servidor
		File f = new File("C:\\Users\\Alumno\\Desktop\\Archivos_Subidos\\"+fileName);
		FileOutputStream fos = new FileOutputStream(f);
		
		int n;
		while((n=is.read())!=-1)
			fos.write(n);
		
		is.close();
		fos.close();
		
		//Construimos el formulario de respuesta:
		response.getWriter().println("<html>");
		response.getWriter().println("<head>");
		response.getWriter().println("<meta charset=\"UTF-8\">");
		response.getWriter().println("<title>Descargar fichero</title>");
		response.getWriter().println("</head>");
		response.getWriter().println("<body>");
		response.getWriter().println("<h1>Archivo subido con éxito</h1>");
		response.getWriter().println("</body>");
		response.getWriter().println("</html>");

	}

	private String getSubmittedFileName(Part part) {

		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}
