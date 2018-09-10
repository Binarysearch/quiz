package net.desenlace.quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import javax.servlet.http.Cookie;

import javax.json.Json;

public abstract class ApiServlet extends HttpServlet {
	
	private static final long serialVersionUID = -9089534560668756167L;
	private boolean sesionIniciada = false;
	private boolean requiereInicioSesion = false;

	public ApiServlet(){
		this.requiereInicioSesion = false;
	}

	public ApiServlet(boolean requiereInicioSesion){
		this.requiereInicioSesion = requiereInicioSesion;
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection db = null;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		try{
			db = DriverManager.getConnection(DbData.DB_URL, DbData.DB_USER, DbData.DB_PASS);
			sesionIniciada = iniciarSesion(request, db);
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
    
	/**
	 * @return true si se ha enviado un token de sesión valido
	 */
	protected boolean isSesionIniciada() {
		return sesionIniciada;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection db = null;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		try{
			db = DriverManager.getConnection(DbData.DB_URL, DbData.DB_USER, DbData.DB_PASS);
			sesionIniciada = iniciarSesion(request, db);
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

    private boolean iniciarSesion(HttpServletRequest request, Connection db) throws SQLException {
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie c : cookies){
				if(c.getName().equals("token")){
					PreparedStatement st = db.prepareStatement("select quiz.iniciar_sesion(?);");
					st.setString(1, c.getValue());
					ResultSet r = st.executeQuery();
					if(r.next()){
						return r.getBoolean("iniciar_sesion");
					}
				}
			}
		}
		if(requiereInicioSesion){
			throw new ApiException(401, "Debes iniciar sesión");
		}
		return false;
	}

	protected String postResponse(Request request, Connection connection) throws SQLException {
		throw new ApiException(405, "Metodo no permitido");
	}
    protected String getResponse(Request request, Connection connection) throws SQLException{throw new ApiException(405, "Metodo no permitido");}
}
