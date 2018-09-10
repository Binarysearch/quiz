package net.desenlace.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.annotation.WebServlet;

@WebServlet("/crear_pregunta")
public class CrearPregunta extends ApiServlet {

	private static final long serialVersionUID = -3911609276462246792L;

	@Override
	protected String postResponse(Request request, Connection db) throws SQLException, ApiException{
		String pregunta = request.getString("pregunta");
		String respuesta1 = request.getString("respuesta1");
		String respuesta2 = request.getString("respuesta2");
		String respuesta3 = request.getString("respuesta3", null);
		String respuesta4 = request.getString("respuesta4", null);
		int correcta = request.getInt("correcta");
		int max = 2;
		if(respuesta3 != null){
			max=3;
			if(respuesta4 != null){
				max=4;
			}
		}

		if (correcta < 1 || correcta > max) {
			throw new ApiException(400, "El parametro 'correcta' no es valido. De be tener un valor entre 1 y "+max+"");
		}

		PreparedStatement st = db.prepareStatement("insert into quiz.preguntas(pregunta,respuesta1,respuesta2,respuesta3,respuesta4,correcta) values(?,?,?,?,?,?) returning id;");
		int i=1;
		st.setString(i++, pregunta);
		st.setString(i++, respuesta1);
		st.setString(i++, respuesta2);
		if(respuesta3 != null){
			st.setString(i++, respuesta3);
			if(respuesta4 != null){
				st.setString(i++, respuesta4);
			}else{
				st.setNull(i++, Types.OTHER);
			}
		}else{
			st.setNull(i++, Types.OTHER);
			st.setNull(i++, Types.OTHER);
		}
		st.setInt(i++, correcta);
		ResultSet r = st.executeQuery();
		if(r.next()){
			int id = r.getInt("id");
			JsonObjectBuilder ob = Json.createObjectBuilder()
				.add("id", id)
				.add("pregunta", pregunta)
				.add("respuesta1", respuesta1)
				.add("respuesta2", respuesta2);

			if(respuesta3 != null){
				ob.add("respuesta3", respuesta3);
				if(respuesta4 != null){
					ob.add("respuesta4", respuesta4);
				}
			}

			return ob.add("correcta", correcta)
				.build()
				.toString();
		}
		throw new ApiException(400, "Ha ocurrido un error al crear la pregunta.");
	}
}