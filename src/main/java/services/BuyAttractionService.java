package services;

import java.util.HashMap;
import java.util.Map;
import model.Atraccion;
import model.Usuario;
import persistence.AtraccionDAO;
import persistence.ItinerarioDAO;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class BuyAttractionService {

	AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
	UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
	ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
	
	ItinerarioService itinerarioService = new ItinerarioService(); 

	public Map<String, String> buy(Integer userId, int atraccionId) {
		Map<String, String> errors = new HashMap<String, String>();

		Usuario usuario = usuarioDAO.findByUserId(userId);
		Atraccion atraccion = atraccionDAO.findByAtraccionId(atraccionId);
		
		
		itinerarioService.crearListaAtraccionesAceptadas(usuario);
		
		if (!atraccion.comprobarCupo()) {	
			errors.put("atraccion", "No hay cupo disponible");
		}
		if (!usuario.tieneDinero(atraccion)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!usuario.tieneTiempo(atraccion)) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			
			usuario.agregarAlItinerario(atraccion);
			atraccion.restarCupo();
			
			atraccionDAO.update(atraccion);
			usuarioDAO.update(usuario);
			
			itinerarioDAO.insertAtraccion(userId, atraccionId);		
		}
		return errors;
	}
	
	
}