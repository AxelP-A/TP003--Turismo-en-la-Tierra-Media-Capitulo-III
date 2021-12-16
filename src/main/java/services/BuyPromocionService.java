package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Atraccion;
import model.Usuario;
import model.Promocion;
import persistence.AtraccionDAO;
import persistence.ItinerarioDAO;
import persistence.PromocionDAO;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class BuyPromocionService {
	PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
	UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
	ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
	AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();

	ItinerarioService itinerarioService = new ItinerarioService();

	public Map<String, String> buy(Integer userId, int promocionId) {
		Map<String, String> errors = new HashMap<String, String>();

		Usuario usuario = usuarioDAO.findByUserId(userId);
		List<Atraccion> listaAtracciones = atraccionDAO.findAll();
		Promocion promocion = promocionDAO.findByPromocionId(promocionId, listaAtracciones);
		
		
		if (!promocion.comprobarCupo()) {	
			errors.put("promocion", "No hay cupo disponible");
		}
		if (!usuario.tieneDinero(promocion)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!usuario.tieneTiempo(promocion)) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			
			usuario.agregarAlItinerario(promocion);
			Atraccion atraccionAux = null;
			
			for (Atraccion atraccion : promocion.getAtraccionesIncluidas()) {
				atraccionAux = atraccionDAO.findByAtraccionId(atraccion.getId());
				atraccionAux.restarCupo();
				atraccionDAO.update(atraccionAux);
			}	
			
			usuarioDAO.update(usuario);
			itinerarioDAO.insertPromocion(userId, promocionId);
			
		}
		return errors;
	}

}