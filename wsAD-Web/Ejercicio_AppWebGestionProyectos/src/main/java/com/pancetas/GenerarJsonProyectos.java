package com.pancetas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.Func;
import models.Proyecto;
import response.Respuestas;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;

/**
 * Servlet implementation class GenerarJsonProyectos
 */
@WebServlet("/generarJsonProyectos")
public class GenerarJsonProyectos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerarJsonProyectos() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			
			List<Proyecto> proyectos = Func.genProy(request, getServletContext());

			JSONArray jsonData = new JSONArray(proyectos);
	        
	        // Configurar la respuesta para que sea un archivo descargable
	        response.setContentType("application/json"); // Tipo MIME para JSON
	        response.setHeader("Content-Disposition", "attachment; filename=\"proyectos.json\""); // Esto hace que se descargue el archivo con el nombre "proyectos.json"
	        response.setCharacterEncoding("UTF-8");

	        // Escribir el contenido del JSON en el flujo de salida
	        response.getWriter().println(jsonData.toString(5));
	        response.getWriter().flush();
			
			
			/////////////////////////////// OTTRA FORMA ///////////////////////////
			
		} catch (Exception e) {
			e.printStackTrace();
			Respuestas.mensajeError(response, "Hubo un error al generar el fichero Json", "generarJsonProyectos.html");
		}

	}

}
