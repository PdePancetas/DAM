package com.pancetas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import response.Respuestas;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.ConexionBD;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/insertarEmpleado")
@MultipartConfig
public class InsertarEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertarEmpleado() {
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
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String dniEmp = request.getParameter("dniEmp");
			String nombreEmp = request.getParameter("nameEmp");

			if (dniEmp.equals("") || nombreEmp.equals(""))
				throw new Exception();

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = ConexionBD.getConex(getServletContext());
			ps = con.prepareStatement("INSERT INTO empleado VALUES(?,?)");
			ps.setString(1, dniEmp);
			ps.setString(2, nombreEmp);

			ps.executeUpdate();
			Respuestas.mensajeOK(response, "Empleado insertado correctamente", "insertarEmpleado.html");
			
		} catch (Exception e) {
			e.printStackTrace();
			Respuestas.mensajeError(response, "Hubo un error insertando al empleado", "insertarEmpleado.html");
		}

	}

}
