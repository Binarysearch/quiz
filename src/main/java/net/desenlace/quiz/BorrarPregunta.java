package net.desenlace.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.json.Json;
import javax.servlet.annotation.WebServlet;

@WebServlet("/borrar_pregunta")
public class BorrarPregunta extends ApiServlet {

	private static final long serialVersionUID = -3911609276462246792L;

	public BorrarPregunta(){
		super(true);
	}

	@Override
	protected String postResponse(Request request, Connection db) throws SQLException, ApiException{
		int id = request.getInt("id");
		
		PreparedStatement st = db.prepareStatement("delete from quiz.preguntas where id=? and usuario=quiz.usr();");
		st.setInt(1, id);
		st.execute();

		return Json.createObjectBuilder().add("result", "ok").build().toString();
	}
}