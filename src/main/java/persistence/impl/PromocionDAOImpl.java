package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import persistence.PromocionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;
import model.Atraccion;
import model.Promocion;
import promociones.PromocionAbsoluta;
import promociones.PromocionAxB;
import promociones.PromocionPorcentual;

public class PromocionDAOImpl implements PromocionDAO {

	public Promocion findByPromocion(String nombre) {

		try {
			String sql = "SELECT * FROM PROMOCIONES WHERE NOMBRE_PROMOCION = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();

			Promocion promocion = null;

			if (resultados.next()) {
			}
			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int countAll() {
		try {
			String sql = "SELECT COUNT(*) AS TOTAL FROM PROMOCIONES";
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

	@Override
	public int update(Promocion promocion) {
		try {
			String sql = "UPDATE promociones SET costo_promocion = ?, nombre_promocion = ?, "
					+ "descripcion = ? WHERE id_promocion = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promocion.getCalculoDeCosto());
			statement.setString(2, promocion.getNombre());
			statement.setString(3, promocion.getDescripcion());
			statement.setInt(4, promocion.getId());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Promocion> findAll(List<Atraccion> listaAtracciones) {
		try {
			String sql = "SELECT promociones.id_promocion, promociones.tipo_promocion, promociones.nombre_promocion, promociones.costo_promocion, promociones.descripcion,promociones.eliminada ,"
					+ " group_concat(atracciones_en_promo.id_atraccion, ';') AS 'atracciones_en_promocion'\n"
					+ "FROM promociones JOIN atracciones_en_promo ON atracciones_en_promo.id_promocion = promociones.id_promocion\n"
					+ "group by promociones.id_promocion";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			Promocion promocion = null;

			List<Promocion> promociones = new LinkedList<Promocion>();

			while (resultados.next()) {
				List<Atraccion> atraccionesIncluidas = new ArrayList<Atraccion>();
				String tipoPromocion = resultados.getString("tipo_promocion");
				String atracciones = resultados.getString("atracciones_en_promocion");
				atraccionesIncluidas = listarAtraccionesIncluidas(atracciones, listaAtracciones);

				switch (tipoPromocion) {
				case "PORCENTUAL":
					promocion = new PromocionPorcentual(resultados.getInt("id_promocion"),
							resultados.getString("nombre_promocion"), atraccionesIncluidas,
							resultados.getDouble("costo_promocion"), resultados.getString("descripcion"),
							resultados.getString("eliminada"));
					break;
				case "AXB":
					promocion = new PromocionAxB(resultados.getInt("id_promocion"),
							resultados.getString("nombre_promocion"), atraccionesIncluidas,
							resultados.getString("descripcion"), resultados.getString("eliminada"));
					break;
				case "ABSOLUTA":
					promocion = new PromocionAbsoluta(resultados.getInt("id_promocion"),
							resultados.getString("nombre_promocion"), atraccionesIncluidas,
							(int) resultados.getDouble("costo_promocion"), resultados.getString("descripcion"),
							resultados.getString("eliminada"));
					break;
				}
				promociones.add(promocion);
			}
			return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private List<Atraccion> listarAtraccionesIncluidas(String atracciones, List<Atraccion> listaAtracciones) {

		String[] idAtraccionesIncluidas = atracciones.split(";");
		List<Atraccion> atraccionesIncluidas = new ArrayList<Atraccion>();

		for (String atraccion : idAtraccionesIncluidas) {
			int idAtraccion = Integer.parseInt(atraccion);
			for (int k = 0; k < listaAtracciones.size(); k++) {
				if (idAtraccion == (listaAtracciones.get(k).getId())) {
					atraccionesIncluidas.add(listaAtracciones.get(k));
				}
			}
		}
		return atraccionesIncluidas;
	}

	public Promocion findByPromocionId(int id, List<Atraccion> listaAtracciones) {

		try {
			String sql = "SELECT promociones.id_promocion, promociones.tipo_promocion, promociones.nombre_promocion, promociones.costo_promocion, promociones.descripcion, promociones.eliminada,"
					+ " group_concat(atracciones_en_promo.id_atraccion, ';') AS 'atracciones_en_promocion'\n"
					+ "FROM promociones JOIN atracciones_en_promo ON atracciones_en_promo.id_promocion = promociones.id_promocion\n"
					+ "WHERE promociones.id_promocion = ?" + "group by promociones.id_promocion";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Promocion promocion = null;

			List<Atraccion> atraccionesIncluidas = new ArrayList<Atraccion>();

			String tipoPromocion = resultados.getString("tipo_promocion");
			String atracciones = resultados.getString("atracciones_en_promocion");
			atraccionesIncluidas = listarAtraccionesIncluidas(atracciones, listaAtracciones);

			switch (tipoPromocion) {
			case "PORCENTUAL":
				promocion = new PromocionPorcentual(resultados.getInt("id_promocion"),
						resultados.getString("nombre_promocion"), atraccionesIncluidas,
						resultados.getDouble("costo_promocion"), resultados.getString("descripcion"),
						resultados.getString("eliminada"));
				break;
			case "AXB":
				promocion = new PromocionAxB(resultados.getInt("id_promocion"),
						resultados.getString("nombre_promocion"), atraccionesIncluidas,
						resultados.getString("descripcion"), resultados.getString("eliminada"));
				break;
			case "ABSOLUTA":
				promocion = new PromocionAbsoluta(resultados.getInt("id_promocion"),
						resultados.getString("nombre_promocion"), atraccionesIncluidas,
						(int) resultados.getDouble("costo_promocion"), resultados.getString("descripcion"),
						resultados.getString("eliminada"));
				break;
			}

			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Promocion promocion) {
		try {
			String sql = "UPDATE PROMOCIONES SET ELIMINADA = datetime('now') WHERE ID_PROMOCION = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promocion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int habilitar(Promocion promocion) {
		try {
			String sql = "UPDATE PROMOCIONES SET ELIMINADA = NULL WHERE ID_PROMOCION = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promocion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int eliminarAtraccion(int idPromocion, int idAtraccion) {
		try {
			String sql = "DELETE FROM atracciones_en_promo where id_promocion = ? and id_atraccion = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idPromocion);
			statement.setInt(2, idAtraccion);
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	@Override
	public int insertarAtraccion(int idPromocion, int idAtraccion) {
		try {
			String sql = "INSERT INTO atracciones_en_promo (id_promocion, id_atraccion)" + "VALUES ( ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idPromocion);
			statement.setInt(2, idAtraccion);
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	@Override
	public List<Promocion> findAll() {
		return null;
	}

	@Override
	public int insert (Promocion promocion) {
		try {
			String sql = "INSERT INTO promociones (tipo_promocion, costo_promocion, nombre_promocion, descripcion)" 
		               + "VALUES ( ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, promocion.getTipoProm());
			statement.setInt(2, promocion.getCalculoDeCosto());
			statement.setString(3, promocion.getNombre());
			statement.setString(4, promocion.getDescripcion());
			int rows = statement.executeUpdate();
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			int idGenerado = rows;

			if (generatedKeys.next()) {
				idGenerado = generatedKeys.getInt(1);
			}
			return idGenerado;
				
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	@Override
	public Promocion find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}