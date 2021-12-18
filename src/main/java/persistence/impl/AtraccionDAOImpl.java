package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import persistence.AtraccionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;
import model.Atraccion;

public class AtraccionDAOImpl implements AtraccionDAO {

	public int insert(Atraccion atraccion) {
		try {
			String sql = "INSERT INTO ATRACCIONES (NOMBRE_ATRACCION, COSTO_ATRACCION, TIEMPO_ATRACCION, CUPO_ATRACCION, TIPO_ATRACCION, DESCRIPCION) VALUES (?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			statement.setInt(2, atraccion.getCostoDeVisita());
			statement.setDouble(3, atraccion.getTiempoNecesario());
			statement.setInt(4, atraccion.getCupo());
			statement.setString(5, atraccion.getTipo().toString());
			statement.setString(6, atraccion.getDescripcion());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int update(Atraccion atraccion) {
		try {
			String sql = "UPDATE ATRACCIONES SET CUPO_ATRACCION = ?  WHERE ID_ATRACCION = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccion.getCupo());
			statement.setInt(2, atraccion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int updateAtraccion(Atraccion atraccion) {
		try {
			String sql = "UPDATE ATRACCIONES SET NOMBRE_ATRACCION = ? , COSTO_ATRACCION = ?, TIEMPO_ATRACCION = ?,"
		               + " CUPO_ATRACCION = ?, TIPO_ATRACCION = ?, DESCRIPCION = ?  WHERE ID_ATRACCION = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			statement.setInt(2, atraccion.getCostoDeVisita());
			statement.setDouble(3, atraccion.getTiempoNecesario());
			statement.setInt(4, atraccion.getCupo());
			statement.setString(5, atraccion.getTipo());
			statement.setString(6, atraccion.getDescripcion());
			statement.setInt(7, atraccion.getId());
			
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int delete(Atraccion atraccion) {
		try {
			String sql = "UPDATE ATRACCIONES SET ELIMINADA = datetime('now') WHERE ID_ATRACCION = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccion.getId());	
			int rows = statement.executeUpdate();
			
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int habilitar(Atraccion atraccion) {
		try {
			String sql = "UPDATE ATRACCIONES SET ELIMINADA = NULL WHERE ID_ATRACCION = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccion.getId());	
			int rows = statement.executeUpdate();
			
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public Atraccion findByAtraccionId(int id) {
		try {
			String sql = "SELECT * FROM ATRACCIONES WHERE ID_ATRACCION = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = null;

			if (resultados.next()) {
				atraccion = convertirAtraccion(resultados);
			}

			return atraccion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int countAll() {
		try {
			String sql = "SELECT COUNT(*) AS TOTAL FROM ATRACCIONES";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<Atraccion> findAll() {
		try {
			String sql = "SELECT * FROM ATRACCIONES";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Atraccion> atracciones = new LinkedList<Atraccion>();
			while (resultados.next()) {
				atracciones.add(convertirAtraccion(resultados));
				
			}
			return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Atraccion convertirAtraccion(ResultSet resultados) throws SQLException {
		
		return new Atraccion(resultados.getInt("id_atraccion"), resultados.getString("nombre_atraccion"), resultados.getInt("costo_atraccion"), resultados.getDouble("tiempo_atraccion"), resultados.getInt("cupo_atraccion"), resultados.getString("tipo_atraccion"), resultados.getString("descripcion"), resultados.getString("eliminada"));
	}

	@Override
	public Atraccion find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}