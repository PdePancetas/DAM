package com.pancetas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.Func;
import models.Empleado;
import models.Proyecto;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;

import com.fasterxml.jackson.databind.ObjectMapper;

import connection.ConexionBD;

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
		// TODO Auto-generated constructor stub
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

		List<Empleado> empleados = new ArrayList<Empleado>();
		List<Proyecto> proyectos = new ArrayList<Proyecto>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = ConexionBD.getConex(getServletContext());
			List<String> datosProyectos = Func.obtenerIdNomProys(con);
			List<String> dniEmpleados = new ArrayList<>();
			for (int idProy : datosProyectos.stream().map(datos -> Integer.parseInt(datos.split(",")[0])).toList()) {
				dniEmpleados.add(Func.obtenerEmpProy(idProy, con).stream().collect(Collectors.joining(",")));
			}

			for (String datos : datosProyectos) {
				proyectos.add(
						new Proyecto(Integer.parseInt(datos.split(",")[0]), datos.split(",")[1], datos.split(",")[2]));
			}

			for (int i = 0; i < proyectos.size(); i++) {
				empleados.clear();
				String[] empleadosData = dniEmpleados.get(i).split(",");
				for (int j = 0; j < dniEmpleados.get(i).split(",").length; j++) {
					empleados.add(Func.obtenerEmp(empleadosData[j], con));
				}
				proyectos.get(i).setEmpleados(empleados);
			}

			ObjectMapper mapper = new ObjectMapper();
			String path = getServletContext().getRealPath("/archivos"); //REVISAR
			File file = new File(path, "archivo.json");
			file.createNewFile();
			mapper.writeValue(file, proyectos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
