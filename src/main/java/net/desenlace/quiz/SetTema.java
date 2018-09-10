package net.desenlace.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.servlet.annotation.WebServlet;

@WebServlet("/set_tema")
public class SetTema extends ApiServlet {

	private static final long serialVersionUID = -3911609276462246792L;

	public SetTema(){
		super(true);
	}

	@Override
	protected String postResponse(Request request, Connection db) throws SQLException, ApiException{
		int id = request.getInt("id");
		
		if(!esTemaDeUsuario(id,db)){
			throw new ApiException(400, "El tema no es valido");
		}

		PreparedStatement st = db.prepareStatement("update quiz.usuarios set tema_actual=? where id=quiz.usr();");
		st.setInt(1, id);
		st.execute();

		return Json.createObjectBuilder()
			.add("id", id)
			.build()
			.toString();
		
	}

	/**
	 * Devuelve true si el usuario es propietario del tema con el id indicado
	 * @param id El id del tema
	 * @param db La conexion con la BBDD
	 * @return true si el usuario es propietario del tema con el id indicado
	 * @throws SQLException
	 */
	private boolean esTemaDeUsuario(int id, Connection db) throws SQLException {
		PreparedStatement st = db.prepareStatement("select exists(select 1 from quiz.temas where usuario=quiz.usr() and id=?);");
		st.setInt(1, id);
		ResultSet r = st.executeQuery();
		if(r.next()){
			return r.getBoolean(1);
		}
		return false;
	}
}