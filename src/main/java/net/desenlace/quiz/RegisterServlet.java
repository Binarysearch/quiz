package net.desenlace.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.servlet.annotation.WebServlet;

@WebServlet("/register")
public class RegisterServlet extends ApiServlet {

	private static final long serialVersionUID = -3911609276462246792L;

	@Override
	protected String postResponse(Request request, Connection db) throws SQLException, ApiException{
		String email = request.getString("email");
		String password = request.getString("password");

		if (existeEmail(db, email)) {
			throw new ApiException(400, "El email '"+email+"' ya existe.");
		}

		PreparedStatement st = db.prepareStatement("insert into quiz.usuarios(email,password) select ?,crypt.crypt(?, crypt.gen_salt('bf', 8)) returning id;");
		st.setString(1, email);
		st.setString(2, password);
		ResultSet r = st.executeQuery();
		if(r.next()){
			int id = r.getInt("id");
			return Json.createObjectBuilder().add("id", id).build().toString();
		}
		throw new ApiException(500, "No se ha podido crear la cuenta de usuario");
	}

	/**
	 * Comprueba si existe el email en la tabla de usuarios.
	 * @param db la conexion con la base de datos
	 * @param email el email a buscar
	 * @return true si el email se encuentra en la tabla usuarios
	 * @throws SQLException
	 */
	private boolean existeEmail(Connection db, String email) throws SQLException {
		PreparedStatement st = db.prepareStatement("select 1 from quiz.usuarios where email=?;");
		st.setString(1, email);
		ResultSet r = st.executeQuery();
		if(r.next()){
			return true;
		}
		return false;
	}
}