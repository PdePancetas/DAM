package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class Saludar
 */

@WebServlet(name="/saludame")
public class Saludar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Saludar() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			String nombre = request.getParameter("nombre");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/saludos","root","1234");
			
			PreparedStatement ps = con.prepareStatement("SELECT saludo FROM saludos WHERE nombre = ?");
			ps.setString(1, nombre);
			String saludo = "";
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				saludo = rs.getString("saludo");
			}
			response.getWriter().append("<html><body><h1>");
			response.getWriter().append("Saludo de "+nombre+": "+saludo);
			response.getWriter().append("</h1></body></html>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
