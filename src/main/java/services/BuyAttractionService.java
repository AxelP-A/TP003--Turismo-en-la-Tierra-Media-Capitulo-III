package services;

import java.util.HashMap;
import java.util.Map;
import model.Atraccion;
import model.Usuario;
import persistence.AtraccionDAO;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class BuyAttractionService {

	AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
	UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();

	public Map<String, String> buy(Integer userId, Integer atraccionId) {
		Map<String, String> errors = new HashMap<String, String>();

		Usuario usuario = usuarioDAO.find(userId);
		Atraccion atraccion = atraccionDAO.find(atraccionId);

		if (!atraccion.comprobarCupo()) {
			
			errors.put("atraccion", "No hay cupo disponible");
		}
		if (!usuario.tieneDinero(atraccion)) {
			errors.put("usuario", "No tienes dinero suficiente");
		}
		if (!usuario.tieneTiempo(atraccion)) {
			errors.put("usuario", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			usuario.agregarAlItinerario(atraccion);
			atraccion.restarCupo(); // Considerar poder poner un número entero y restar ese cupo, luego de comprobar si hay para tanta gente, para así poder comprar un pack familiar.

			// no grabamos para no afectar la base de pruebas
			atraccionDAO.update(atraccion);
			usuarioDAO.update(usuario);
		}
		return errors;
	}
}