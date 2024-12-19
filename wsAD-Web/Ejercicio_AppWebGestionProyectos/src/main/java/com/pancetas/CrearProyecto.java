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
import java.util.ArrayList;
import java.util.Arrays;
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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletContext servletContext = getServletContext();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = ConexionBD.getConex(servletContext);

        try (PrintWriter out = response.getWriter()) {
            
            ArrayList<String> empleados = Func.obtenerDnisEmp(con, servletContext);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<link rel='stylesheet' type='text/css' href='estilos/crearProyecto.css'>");
            out.println("<title>Crear Proyecto</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Rellena los datos para crear el proyecto</h3>");
            out.println("<form action='crearProyecto' method='post'>");
            out.println("<label for='nameProy'>Escribe el nombre del proyecto:</label>");
            out.println("<input type='text' name='nameProy' id='nameProy' />");
            out.println("<br>");

            out.println("<label for='dniJefeProy'>Selecciona el DNI del jefe de proyecto:</label>");
            out.println("<select name='dniJefeProy' id='dniJefeProy'>");
            //Si se selecciona un dni, los dnis anteriores no muestran el nombre, por lo que si se quieren ver de nuevo
            //todos los nombres disponibles, elegir la primera opción (de esta forma aparecen todos los nombres)
            //y luego escoger
            for (String dni : empleados) {
                String nombreEmpleado = Func.obtenerEmp(dni, con).getNombre(); 
                out.println("<option value='" + dni + "' title='" + nombreEmpleado + "'>" + dni + "</option>");
            }
            out.println("</select>");
            out.println("<br>");
            
            out.println("<label for='dniEmpProy'>Escribe los DNIs de los empleados asociados:</label>");
            out.println("<input type='text' name='dniEmpProy' id='dniEmpProy' placeholder='Ej: 1,2,3,...(excluir el DNI del jefe)' />");
            out.println("<br><br>");

            out.println("<input type='submit' value='Crear proyecto'>");
            out.println("<input type='button' name='volverMenú' id='volverMenú' " +
                        "onclick=\"window.location.href='/Ejercicio_AppWebGestionProyectos/inicio.html'\" value='Volver al menú'>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException e) {
            throw new ServletException("Error al obtener los DNIs de empleados", e);
        }
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

			Respuestas.mensajeOK(response, "Proyecto creado correctamente", "crearProyecto");
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			Respuestas.mensajeError(response, "Hubo un error al crear el proyecto", "crearProyecto");
		} catch (Exception e) {
			e.printStackTrace();
			Respuestas.mensajeError(response, "Hubo un error al crear el proyecto", "crearProyecto");
		} 
	}

}
