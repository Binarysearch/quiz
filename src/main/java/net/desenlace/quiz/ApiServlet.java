package net.desenlace.quiz;

import java.sql.Connection;
import java.sql.DriverManager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public abstract class ApiServlet extends HttpServlet {
	
	private static final long serialVersionUID = -9089534560668756167L;

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Connection db = DriverManager.getConnection(DbData.DB_URL, DbData.DB_USER, DbData.DB_PASS);
            response.getWriter().append(postResponse(new Request(request), db));
        } catch (SQLException e){
            response.getWriter().append(e.getMessage());
        } catch (ApiException e){
            response.setStatus(e.getErrorCode());
            response.getWriter().append(e.getMessage());
        }
    }
    
    protected abstract String postResponse(Request request, Connection connection) throws SQLException;
}