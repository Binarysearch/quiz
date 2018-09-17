package net.desenlace.quiz;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/index.html")
public class IndexServlet extends HttpServlet {
	
	private static final long serialVersionUID = -6806410570570438670L;

	@Override
	protected void doGet(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("Hola!");
	}
}