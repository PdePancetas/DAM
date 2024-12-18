package com.pancetas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

		String idEmp = request.getParameter("idEmp");
		String nombreEmp = request.getParameter("nameEmp");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = ConexionBD.getConex(getServletContext());
			PreparedStatement ps = con.prepareStatement("INSERT INTO empleado VALUES(?,?)");
			ps.setString(1, idEmp);
			ps.setString(2, nombreEmp);

			ps.executeUpdate();
			response.getWriter().println("<html>");
			response.getWriter().println("<head>");
			response.getWriter().println("<meta charset=\"UTF-8\">");
			response.getWriter().println("<title>Empleado insertado</title>");
			response.getWriter().println("</head>");
			response.getWriter().println("<body>");
			response.getWriter().println("<h1>Empleado insertado con éxito en la base de datos</h1>");
			response.getWriter().println("</body>");
			response.getWriter().println("<html>");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
