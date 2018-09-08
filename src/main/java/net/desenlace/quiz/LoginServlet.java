package net.desenlace.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.servlet.annotation.WebServlet;

@WebServlet("/login")
public class LoginServlet extends ApiServlet {

	private static final long serialVersionUID = -3911609276462246792L;

	@Override
	protected String postResponse(Request request, Connection db) throws SQLException, ApiException{
		String email = request.getString("email");
		String password = request.getString("password");

		PreparedStatement st = db.prepareStatement("insert into quiz.sesiones(usuario,token) select id,util.random_string(50) from quiz.usuarios where email=? and crypt.crypt(?, password)=password returning usuario,token;");
		st.setString(1, email);
		st.setString(2, password);
		ResultSet r = st.executeQuery();
		if(r.next()){
			int usuario = r.getInt("usuario");
			String token = r.getString("token");
			return Json.createObjectBuilder()
				.add("usuario", usuario)
				.add("token", token)
				.add("email", email)
				.build()
				.toString();
		}
		throw new ApiException(400, "Email y/o contraseña icorrectos.");
	}
}