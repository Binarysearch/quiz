package net.desenlace.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.servlet.annotation.WebServlet;

@WebServlet("/get_temas")
public class GetTemas extends ApiServlet {

	private static final long serialVersionUID = -3911609276462246792L;

	public GetTemas(){
		super(true);
	}

	@Override
	protected String postResponse(Request request, Connection db) throws SQLException, ApiException{
		PreparedStatement st = db.prepareStatement("select id,nombre from quiz.temas where usuario=quiz.usr() order by id;");
		ResultSet r = st.executeQuery();
		JsonArrayBuilder ab = Json.createArrayBuilder();
		while(r.next()){
			ab.add(
				Json.createObjectBuilder()
				.add("id", r.getInt("id"))
				.add("nombre", r.getString("nombre"))
			);
		}
		return ab.build().toString();
	}
}