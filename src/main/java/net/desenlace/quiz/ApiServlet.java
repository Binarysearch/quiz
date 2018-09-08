package net.desenlace.quiz;

import java.sql.Connection;
import java.sql.DriverManager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import javax.json.Json;

public abstract class ApiServlet extends HttpServlet {
	
	private static final long serialVersionUID = -9089534560668756167L;

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection db = null;
		try{
            db = DriverManager.getConnection(DbData.DB_URL, DbData.DB_USER, DbData.DB_PASS);
            response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
            response.getWriter().append(postResponse(new Request(request), db));
        } catch (SQLException e){
            response.setStatus(500);
            response.getWriter().append(Json.createObjectBuilder().add("error", e.getMessage()).build().toString());
        } catch (ApiException e){
            response.setStatus(e.getErrorCode());
            response.getWriter().append(Json.createObjectBuilder().add("error", e.getMessage()).build().toString());
        } finally {
			if(db != null){
				try {db.close();} catch (SQLException e) {}
			}
		}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection db = null;
		try{
			db = DriverManager.getConnection(DbData.DB_URL, DbData.DB_USER, DbData.DB_PASS);
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(getResponse(new Request(request), db));
		} catch (SQLException e){
			response.setStatus(500);
			response.getWriter().append(Json.createObjectBuilder().add("error", e.getMessage()).build().toString());
		} catch (ApiException e){
			response.setStatus(e.getErrorCode());
			response.getWriter().append(Json.createObjectBuilder().add("error", e.getMessage()).build().toString());
		} finally {
			if(db != null){
				try {db.close();} catch (SQLException e) {}
			}
		}
	}

    protected String postResponse(Request request, Connection connection) throws SQLException{throw new ApiException(405, "Metodo no permitido");}
    protected String getResponse(Request request, Connection connection) throws SQLException{throw new ApiException(405, "Metodo no permitido");}
}
