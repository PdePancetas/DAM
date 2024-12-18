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

import connection.ConexionBD;

/**
 * Servlet implementation class EliminarProyecto
 */
@WebServlet("/eliminarProyecto")
public class EliminarProyecto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminarProyecto() {
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

		Connection con = null;
		PreparedStatement ps = null;
		try {
			int idProy = Integer.parseInt(request.getParameter("idProy"));

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = ConexionBD.getConex(getServletContext());
			
			Func.eliminarProyecto(idProy, con);
			
			Respuestas.mensajeOK(response, "Proyecto eliminado correctamente", "eliminarProyecto.html");
		} catch (Exception e) {
			e.printStackTrace();
			Respuestas.mensajeError(response, "Hubo un error eliminando el proyecto", "eliminarProyecto.html");
		}

	}

}
