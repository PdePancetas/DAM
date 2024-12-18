package com.pancetas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import logic.Func;
import models.Empleado;
import models.Empleados;
import response.Respuestas;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.ConexionBD;

/**
 * Servlet implementation class InsertarEmpleadosXML
 */
@WebServlet("/insertarEmpleadosXML")
@MultipartConfig
public class InsertarEmpleadosXML extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertarEmpleadosXML() {
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

		Part filePart = request.getPart("ficheroEmpleadosXML");
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Empleados empleados = Func.leerFichero(filePart);
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = ConexionBD.getConex(getServletContext());
			con.setAutoCommit(false);
			
			ps = con.prepareStatement("INSERT INTO empleado (dni, nom_emp) VALUES (?, ?)");

			for (Empleado empleado : empleados.getEmpleados()) {
				ps.setString(1, empleado.getDni());
				ps.setString(2, empleado.getNombre());

				ps.addBatch();
			}

			int[] result = ps.executeBatch();

			con.commit();
			
			Respuestas.mensajeOK(response, "Empleados insertados correctamente", "insertarEmpleadosXML.html");
		} catch (SQLException e) {
			e.printStackTrace();
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException rollbackEx) {
					rollbackEx.printStackTrace();
				}
			}
			Respuestas.mensajeError(response, "Hubo un error al insertar a los empleados", "insertarEmpleadosXML.html");
		} catch (Exception e) {
			e.printStackTrace();
			Respuestas.mensajeError(response, "Hubo un error al insertar a los empleados", "insertarEmpleadosXML.html");
		}

	}

	

}
