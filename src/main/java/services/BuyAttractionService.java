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

	public Map<String, String> buy(Integer userId, int atraccionId) {
		Map<String, String> errors = new HashMap<String, String>();

		Usuario usuario = usuarioDAO.findByUserId(userId);
		Atraccion atraccion = atraccionDAO.findByAtraccionId(atraccionId);

		if (!atraccion.comprobarCupo()) {
			
			errors.put("attraction", "No hay cupo disponible");
		}
		if (!usuario.tieneDinero(atraccion)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!usuario.tieneTiempo(atraccion)) {
			errors.put("user", "No tienes tiempo suficiente");
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