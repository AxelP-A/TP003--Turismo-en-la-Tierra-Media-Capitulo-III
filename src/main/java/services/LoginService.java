package services;

import model.Usuario;
import model.nullobjects.NullUser;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class LoginService {

	public Usuario login(String username, String password) {
		UsuarioDAO userDao = DAOFactory.getUsuarioDAO();
    	Usuario usuario = userDao.findByUsername(username);
    	
    	if (usuario.isNull() || !usuario.checkPassword(password)) {
    		usuario = NullUser.build();
    	}
    	return usuario;
	}
}