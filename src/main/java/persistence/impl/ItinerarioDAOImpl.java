package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import persistence.commons.ConnectionProvider;
import model.Atraccion;
import model.Promocion;
import persistence.ItinerarioDAO;
import sugeribles.Sugerible;
import persistence.commons.MissingDataException;

public class ItinerarioDAOImpl implements ItinerarioDAO {
	
	@Override
	public int insertAtraccion(int id_usuario, int id_atraccion) {

		try {
			String sql = "INSERT OR IGNORE INTO Itinerario (id_usuario, id_atraccion) VALUES (?, ?)";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, id_usuario);
			statement.setInt(2, id_atraccion);

			int rows = statement.executeUpdate();
			return rows;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	@Override
	public int insertPromocion(int id_usuario, int id_promocion) {

		try {
			String sql = "INSERT OR IGNORE INTO Itinerario (id_usuario, id_promocion) VALUES (?, ?)";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, id_usuario);
			statement.setInt(2, id_promocion);

			int rows = statement.executeUpdate();
			return rows;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	@Override
	public List<Sugerible> findByUserId(int id_usuario, List<Atraccion> listaAtracciones,
			List<Promocion> listaPromociones) {

		List<Sugerible> itinerario = new ArrayList<Sugerible>();
		try {
			String sql = "SELECT group_concat(id_atraccion,';') AS atracciones ,group_concat(id_promocion,';') AS promociones FROM itinerario WHERE id_usuario = ?\r\n"
					+ "    GROUP BY id_usuario";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id_usuario);
			ResultSet resultados = statement.executeQuery();

			if (resultados.next()) {
				String atracciones = resultados.getString("atracciones");
				String promociones = resultados.getString("promociones");
				itinerario = seleccionarAtraccionesYpromociones(atracciones, promociones, listaAtracciones,
						listaPromociones);
			}
			return itinerario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	private List<Sugerible> seleccionarAtraccionesYpromociones(String atracciones, String promociones,
			List<Atraccion> listaAtracciones, List<Promocion> listaPromociones) {

		List<Sugerible> itinerario = new ArrayList<Sugerible>();
		if (atracciones != null) {
			String[] idAtraccionesIncluidas = atracciones.split(";");
			for (String atraccion : idAtraccionesIncluidas) {
				int idAtraccion = Integer.parseInt(atraccion);
				for (int k = 0; k < listaAtracciones.size(); k++) {
					if (idAtraccion == (listaAtracciones.get(k).getId())) {
						itinerario.add(listaAtracciones.get(k));
					}
				}
			}
		}
		if (promociones != null) {
			String[] idPromocionesIncluidas = promociones.split(";");
			for (String promocion : idPromocionesIncluidas) {
				int idPromocion = Integer.parseInt(promocion);
				for (int k = 0; k < listaPromociones.size(); k++) {
					if (idPromocion == (listaPromociones.get(k).getId())) {
						itinerario.add(listaPromociones.get(k));
					}
				}
			}
		}
		return itinerario;
	}
	
	@Override
	public List<Sugerible> findAll() {
		return null;
		
	}
	
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int insert(Sugerible t) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int update(Sugerible t) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int delete(Sugerible t) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Sugerible find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}