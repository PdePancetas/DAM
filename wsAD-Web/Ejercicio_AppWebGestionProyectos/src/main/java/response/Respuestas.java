package response;

import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;

public class Respuestas {

	public static void mensajeOK(HttpServletResponse response, String msg, String paginaAnterior) throws IOException {
		response.getWriter().println("<html>");
		response.getWriter().println("<head>");
		response.getWriter().println("<meta charset=\"UTF-8\">");
		response.getWriter().println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilos/responseOK.css\">");
		response.getWriter().println("<title>Operación realizada</title>");
		response.getWriter().println("</head>");
		response.getWriter().println("<body>");
		response.getWriter().println("<h3>"+msg+"</h3>");
		response.getWriter().println("<input type=\"button\" name=\"volver\" id=\"volver\" class=\"volver\" onclick=\"window.location.href='/Ejercicio_AppWebGestionProyectos/"+paginaAnterior+"'\" value=\"Volver\">");
		response.getWriter().println("</body>");
		response.getWriter().println("</html>");

	}
	
	public static void mensajeError(HttpServletResponse response, String msg, String paginaAnterior) throws IOException {
		response.getWriter().println("<html>");
		response.getWriter().println("<head>");
		response.getWriter().println("<meta charset=\"UTF-8\">");
		response.getWriter().println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilos/responseError.css\">");
		response.getWriter().println("<title>Operación no realizada</title>");
		response.getWriter().println("</head>");
		response.getWriter().println("<body>");
		response.getWriter().println("<h3 class=\"error\">"+msg+"</h3>");
		response.getWriter().println("<input type=\"button\" name=\"volver\" id=\"volver\" class=\"volver\" onclick=\"window.location.href='/Ejercicio_AppWebGestionProyectos/"+paginaAnterior+"'\" value=\"Volver\">");
		response.getWriter().println("</body>");
		response.getWriter().println("</html>");

	}
	
}
