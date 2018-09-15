package net.desenlace.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.annotation.WebServlet;

@WebServlet("/get_tema_actual")
public class GetTemaActual extends ApiServlet {

	private static final long serialVersionUID = 1L;

	public GetTemaActual() {
		super(true);
	}

	@Override
	protected String postResponse(Request request, Connection db) throws SQLException, ApiException{
		PreparedStatement st = db.prepareStatement("select id,nombre from quiz.temas t where t.id=quiz.tema();");
		ResultSet r = st.executeQuery();
		JsonObjectBuilder ob = Json.createObjectBuilder();
		if(r.next()){
			ob.add("id", r.getInt("id"))
				.add("nombre", r.getString("nombre"));
		}
		return ob.build().toString();
	}
}