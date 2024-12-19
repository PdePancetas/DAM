package com.pancetas;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.Func;
import response.Respuestas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

        ServletContext servletContext = getServletContext();
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        Connection con = ConexionBD.getConex(servletContext); // Configura correctamente tu conexión

        try (PrintWriter out = response.getWriter()) {
            // Obtén la lista de IDs de proyectos desde la base de datos
            List<String> ids = Func.obteneridProys(con, servletContext);

            // Genera el HTML dinámicamente
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<link rel='stylesheet' type='text/css' href='estilos/eliminarProyecto.css'>");
            out.println("<title>Eliminar Proyecto</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Inserta el id del proyecto</h3>");
            out.println("<form action='eliminarProyecto' method='post'>");
            out.println("<label for='idProy'>Escribe el id:</label>");
            out.println("<select name='idProy' id='idProy'>");

            // Agrega los IDs como opciones en el select
            for (String id : ids) {
                out.println("<option value='" + id + "'>" + id + "</option>");
            }

            out.println("</select>");
            out.println("<br><br>");
            out.println("<input type='submit' value='Eliminar proyecto'>");
            out.println("<input type='button' name='volverMenú' id='volverMenú' onclick=\"window.location.href='/Ejercicio_AppWebGestionProyectos/inicio.html'\" value='Volver al menú'>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException e) {
            throw new ServletException("Error al obtener los IDs de proyectos", e);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection con = null;
		try {
			int idProy = Integer.parseInt(request.getParameter("idProy"));

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = ConexionBD.getConex(getServletContext());

			Func.eliminarProyecto(idProy, con);

			Respuestas.mensajeOK(response, "Proyecto eliminado correctamente", "eliminarProyecto");
		} catch (Exception e) {
			e.printStackTrace();
			Respuestas.mensajeError(response, "Hubo un error eliminando el proyecto", "eliminarProyecto");
		}

	}

}
