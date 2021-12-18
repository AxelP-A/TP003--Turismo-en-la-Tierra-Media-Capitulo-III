package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;
import model.Usuario;
import persistence.UsuarioDAO;

public class UsuarioDAOImpl implements UsuarioDAO {

	public int insert(Usuario user) {
		try {
			String sql = "INSERT INTO USUARIOS (NOMBRE_USUARIO, TIPO_ATRACCION, PRESUPUESTO_USUARIO, TIEMPO_DISPONIBLE, PASSWORD, ADMIN) VALUES (?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getNombre());
			statement.setString(2, user.getAtraccionPreferida());
			statement.setInt(3, user.getPresupuesto());
			statement.setDouble(4, user.getTiempoDisponible());
			statement.setString(5, user.encryptedPassword(user.getPassword()));
			statement.setInt(6, user.admin());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int update(Usuario user) {
		try {
			String sql = "UPDATE USUARIOS SET PRESUPUESTO_USUARIO = ?, TIEMPO_DISPONIBLE = ?, PASSWORD = ?, TIPO_ATRACCION = ?, NOMBRE_USUARIO = ?, ADMIN = ?  WHERE ID_USUARIO = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, user.getPresupuesto());
			statement.setDouble(2, user.getTiempoDisponible());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getAtraccionPreferida());
			statement.setString(5, user.getNombre());
			statement.setInt(6, user.admin());
			statement.setInt(7, user.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(Usuario user) {
		try {
			String sql = "UPDATE USUARIOS SET ELIMINADO = datetime('now') WHERE ID_USUARIO = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, user.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int habilitar(Usuario user) {
		try {
			String sql = "UPDATE USUARIOS SET ELIMINADO = NULL WHERE ID_USUARIO = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, user.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	
	public Usuario findByUserId(Integer id) {
		try {
			String sql = "SELECT * FROM USUARIOS WHERE ID_USUARIO = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Usuario user = null;

			if (resultados.next()) {
				user = convertirUsuario(resultados);
			}
			return user;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int countAll() {
		try {
			String sql = "SELECT COUNT(*) AS TOTAL FROM USUARIOS";
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

	public List<Usuario> findAll() {
		try {
			String sql = "SELECT * FROM USUARIOS";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Usuario> usuarios = new LinkedList<Usuario>();
			while (resultados.next()) {
				usuarios.add(convertirUsuario(resultados));
			}

			return usuarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	private Usuario convertirUsuario(ResultSet resultados) throws SQLException {
			
		return new Usuario(resultados.getInt("id_usuario"), resultados.getString("nombre_usuario"), resultados.getString("tipo_atraccion"), resultados.getInt("presupuesto_usuario"),
				resultados.getDouble("tiempo_disponible"), resultados.getString("password"), resultados.getBoolean("admin"), resultados.getString("eliminado"));
	}

	@Override
	public Usuario find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario findByUsername(String username) {
		try {
			String sql = "SELECT * FROM USUARIOS WHERE NOMBRE_USUARIO = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet resultados = statement.executeQuery();
			
			Usuario user = null;

			if (resultados.next()) {
				user = convertirUsuario(resultados);
			}
			return user;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}