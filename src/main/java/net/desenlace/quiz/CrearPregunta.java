package net.desenlace.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.servlet.annotation.WebServlet;

@WebServlet("/crear_pregunta")
public class CrearPregunta extends ApiServlet {

	private static final long serialVersionUID = -3911609276462246792L;

	@Override
	protected String postResponse(Request request, Connection db) throws SQLException, ApiException{
		String pregunta = request.getString("pregunta");
		String respuesta1 = request.getString("respuesta1");
		String respuesta2 = request.getString("respuesta2");
		String respuesta3 = request.getString("respuesta3");
		String respuesta4 = request.getString("respuesta4");
		int correcta = request.getInt("correcta");

		PreparedStatement st = db.prepareStatement("insert into quiz.preguntas(pregunta,respuesta1,respuesta2,respuesta3,respuesta4,correcta) values(?,?,?,?,?,?) returning id;");
		st.setString(1, pregunta);
		st.setString(2, respuesta1);
		st.setString(3, respuesta2);
		st.setString(4, respuesta3);
		st.setString(5, respuesta4);
		st.setInt(6, correcta);
		ResultSet r = st.executeQuery();
		if(r.next()){
			int id = r.getInt("id");
			return Json.createObjectBuilder()
				.add("id", id)
				.add("pregunta", pregunta)
				.add("respuesta1", respuesta1)
				.add("respuesta2", respuesta2)
				.add("respuesta3", respuesta3)
				.add("respuesta4", respuesta4)
				.add("correcta", correcta)
				.build()
				.toString();
		}
		throw new ApiException(400, "Ha ocurrido un error al crear la pregunta.");
	}
}