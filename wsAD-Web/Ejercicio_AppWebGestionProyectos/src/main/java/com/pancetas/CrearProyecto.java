package com.pancetas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.Func;
import response.Respuestas;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import connection.ConexionBD;

/**
 * Servlet implementation class InsertarProyecto
 */
@WebServlet("/crearProyecto")
public class CrearProyecto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CrearProyecto() {
		super();
		// TODO Auto-generated constructor stub
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

		String dniJefe = request.getParameter("dniJefeProy");
		String nomProy = request.getParameter("nameProy");
		ArrayList<String> empleados = new ArrayList<>(Arrays.asList(request.getParameter("dniEmpProy").split(",")));

		Connection con = null;
		int claveGenerada = -1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = ConexionBD.getConex(getServletContext());
			con.setAutoCommit(false);

			// Comprobamos que existe el empleado jefe
			boolean estanTodos = false;
			if (Func.existeEmpleado(dniJefe, "empleado", con)) {
				for (String dni : empleados)
					if (Func.existeEmpleado(dni, "empleado", con))
						estanTodos = true;
					else
						estanTodos = false;
			}

			// Habiendo comprobado que todos los empleados implicados en el proyecto existen
			// en la base de datos,
			// insertamos el nuevo proyecto, y las asignaciones de los empleados a la clave
			// del proyecto asignada
			if (estanTodos) {
				claveGenerada = Func.crearProyecto(nomProy, dniJefe, empleados, con);
				Func.generarAsig_proy(dniJefe, claveGenerada, con);
				for (String dni : empleados) {
					Func.generarAsig_proy(dni, claveGenerada, con);
				}
			}

			con.commit();

			Respuestas.mensajeOK(response, "Proyecto creado correctamente", "crearProyecto.html");
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			Respuestas.mensajeError(response, "Hubo un error al crear el proyecto", "crearProyecto.html");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			Respuestas.mensajeError(response, "Hubo un error al crear el proyecto", "crearProyecto.html");
		}
	}

}
