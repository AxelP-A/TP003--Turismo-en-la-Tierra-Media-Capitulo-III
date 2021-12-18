package persistence;

import persistence.commons.GenericDAO;
import model.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {

	public abstract Usuario findByUserId(Integer id);
	public abstract Usuario findByUsername(String username);
	public abstract int habilitar(Usuario user);
}