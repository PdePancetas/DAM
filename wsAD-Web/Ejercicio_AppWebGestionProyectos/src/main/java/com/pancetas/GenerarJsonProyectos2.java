package com.pancetas;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.Func;
import models.Empleado;
import models.Proyecto;
import response.Respuestas;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Servlet implementation class GenerarJsonProyectos
 */
@WebServlet("/generarJsonProyectos2")
public class GenerarJsonProyectos2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerarJsonProyectos2() {
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
			
			JSONArray arrayProyectos = new JSONArray(proyectos);
			/*
			JSONArray arrayProyectos = new JSONArray();
			
			for(Proyecto p : proyectos) {
				JSONObject proyJSON = new JSONObject();
				proyJSON.append("id", p.getId_proy());
				proyJSON.append("nombre", p.getNom_proy());
				proyJSON.append("dniJefe", p.getDni_jefe_proy());
				JSONArray emps = new JSONArray();
				for(Empleado emp : p.getEmpleados()) {
					JSONObject empJSON = new JSONObject();
					empJSON.append("dni", emp.getDni());
					empJSON.append("nombre", emp.getNombre());
					emps.put(empJSON);
				}
				proyJSON.append("empleados", emps);
				arrayProyectos.put(proyJSON);
			}
			*/
			String datosDescarga = arrayProyectos.toString(4);
			
			//O BIEN SI USAMOS JACKSON, QUE SE ENTIENDE CON LAS ANOTACIONES:
			ObjectMapper mapper = new ObjectMapper();
		    mapper.enable(SerializationFeature.INDENT_OUTPUT); 
		    String fichJSON = mapper.writeValueAsString(proyectos);
			
			response.setContentType("application/json");
	        response.setHeader("Content-Disposition", "attachment; filename=\"proyectos.json\""); 
	        
	        ServletOutputStream sos = response.getOutputStream();
	        sos.write(fichJSON.getBytes());
	        
	        sos.close();
	        
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}

}
