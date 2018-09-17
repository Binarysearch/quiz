package net.desenlace.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.servlet.annotation.WebServlet;

@WebServlet("/crear_tema")
public class CrearTema extends ApiServlet {

	private static final long serialVersionUID = -3911609276462246792L;

	public CrearTema(){
		super(true);
	}

	@Override
	protected String postResponse(Request request, Connection db) throws SQLException, ApiException{
		String nombre = request.getString("nombre");
		
		PreparedStatement st = db.prepareStatement("insert into quiz.temas(nombre) values(?) returning id;");
		st.setString(1, nombre);
		
		ResultSet r = st.executeQuery();
		if(r.next()){
			int id = r.getInt("id");

			PreparedStatement st2 = db.prepareStatement("update quiz.usuarios set tema_actual=? where id=quiz.usr();");
			st2.setInt(1, id);
			st2.execute();

			return Json.createObjectBuilder()
				.add("id", id)
				.add("nombre", nombre)
				.build()
				.toString();
		}
		throw new ApiException(400, "Ha ocurrido un error al crear el tema.");
	}
}