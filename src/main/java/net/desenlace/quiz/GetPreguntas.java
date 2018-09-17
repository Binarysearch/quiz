package net.desenlace.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.annotation.WebServlet;

@WebServlet("/get_preguntas")
public class GetPreguntas extends ApiServlet {

	private static final long serialVersionUID = -3911609276462246792L;

	public GetPreguntas(){
		super(true);
	}

	@Override
	protected String postResponse(Request request, Connection db) throws SQLException, ApiException{
		PreparedStatement st = db.prepareStatement("select id,tema,pregunta,respuesta1,respuesta2,respuesta3,respuesta4,correcta from quiz.preguntas where usuario=quiz.usr() order by id;");
		ResultSet r = st.executeQuery();
		JsonArrayBuilder ab = Json.createArrayBuilder();
		while(r.next()){
			JsonObjectBuilder ob = Json.createObjectBuilder()
				.add("id", r.getInt("id"))
				.add("tema", r.getInt("tema"))
				.add("pregunta", r.getString("pregunta"))
				.add("respuesta1", r.getString("respuesta1"))
				.add("respuesta2", r.getString("respuesta2"));

			if(r.getString("respuesta3") != null){
				ob.add("respuesta3", r.getString("respuesta3"));
				if(r.getString("respuesta4") != null){
					ob.add("respuesta4", r.getString("respuesta4"));
				}
			}
				
			ob.add("correcta", r.getInt("correcta"));
			ab.add(ob);
		}
		return ab.build().toString();
	}
}