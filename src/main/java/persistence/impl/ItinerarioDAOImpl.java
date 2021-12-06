package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import persistence.commons.ConnectionProvider;
import model.Atraccion;
import model.Promocion;
import persistence.ItinerarioDAO;
import sugeribles.Sugerible;
import persistence.commons.MissingDataException;

public class ItinerarioDAOImpl implements ItinerarioDAO {

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

	public List<Sugerible> findByUserId(int id_usuario, List<Atraccion> listaAtracciones,
			List<Promocion> listaPromociones) {

		List<Sugerible> itinerario = new ArrayList<Sugerible>();

		try {
			String sql = "SELECT group_concat(id_atraccion,';') AS atracciones ,group_concat(id_promocion,';') AS promociones FROM itinerario WHERE id_usuario = ?\r\n"
					+ "    GROUP BY id_usuario"; // devuelve una linea por cada usuario.

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

		// método split (como en promo)x2, lo hacemos para las promos y para las
		// atracciones, buscando por ID. Y las agregamos al itinerario.
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
		// TODO Auto-generated method stub
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

	// Creaci�n de itinerario para cada usuario (revisando por id de usuario, los id
	// de las atracciones o promociones).
	// Cada una de las
	//

	/*
	 * private List<Sugerible> listarAtraccionesIncluidas() {
	 * 
	 * // List <Atraccion> listaAtracciones = new ArrayList<Atraccion>(); //
	 * listaAtracciones = App.getAtracciones();
	 * 
	 * String[] idAtraccionesIncluidas = atracciones.split("|"); List<Atraccion>
	 * atraccionesIncluidas = new ArrayList<Atraccion>();
	 * 
	 * for (String atraccion : idAtraccionesIncluidas) { int idAtraccion =
	 * Integer.parseInt(atraccion); for (int k = 0; k < listaAtracciones.size();
	 * k++) { if (idAtraccion == (listaAtracciones.get(k).getId())) {
	 * atraccionesIncluidas.add(listaAtracciones.get(k)); } } } return
	 * atraccionesIncluidas; }
	 */
}