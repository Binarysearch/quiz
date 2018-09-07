package net.desenlace.quiz;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;


@WebServlet("/register")
public class RegisterServlet extends ApiServlet {

	private static final long serialVersionUID = -3911609276462246792L;

	@Override
	protected String postResponse(Request request, Connection db) throws SQLException, ApiException{
		String email = request.getString("email");
		String password = request.getString("password");
		PreparedStatement st = db.prepareStatement("insert into quiz.usuarios(email,password) select ?,crypt.crypt(?, crypt.gen_salt('bf', 8)) where not exists(select 1 from quiz.usuarios where email=?) returning id");
		st.setString(1, email);
		st.setString(2, password);
		st.setString(3, email);
		ResultSet r = st.executeQuery();
		if(r.next()){
			int id = r.getInt("id");
			return id+"";
		}
		return "ss";
	}
}