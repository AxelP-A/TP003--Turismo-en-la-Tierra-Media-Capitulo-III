package services;

import java.util.List;

import model.Usuario;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class UsuarioService {

	public List<Usuario> list() {
		return DAOFactory.getUsuarioDAO().findAll();
	}

	public Usuario create(int id, String nombre, String atraccionPreferida, int presupuesto, double tiempoDisponible, String password, boolean isAdmin) {

		Usuario usuario = new Usuario(-1, nombre, atraccionPreferida, presupuesto, tiempoDisponible, password, isAdmin, null);

		if (usuario.isValid()) {
			UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
			usuarioDAO.insert(usuario);
			// XXX: si no devuelve "1", es que hubo más errores
		}
		return usuario;
	}

	public Usuario updateUsuario(int id, String nombre, String atraccionPreferida, int presupuesto, double tiempoDisponible, String password) {
		
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		Usuario usuario = usuarioDAO.findByUserId(id);
		
		usuario.setNombre(nombre);
		usuario.setAtraccionPreferida(atraccionPreferida);
		usuario.setPresupuesto(presupuesto);
		usuario.setTiempoDisponible(tiempoDisponible);
		usuario.setPassword(password);

		if (usuario.isValid()) {
			usuarioDAO.update(usuario);
			// XXX: si no devuelve "1", es que hubo más errores
		}
		return usuario;
	}

	// Revisar manera de implementar correctamente, ya que nos null no los toma bien.
	public void delete(int id) {
		Usuario usuario = new Usuario(id);
		
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		usuarioDAO.delete(usuario);
	}

	public Usuario find(int id) {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		return usuarioDAO.findByUserId(id);
	}
	
}
