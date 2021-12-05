package persistence;

import java.util.List;

import model.Atraccion;
import model.Promocion;
import sugeribles.Sugerible;
import persistence.commons.GenericDAO;

public interface ItinerarioDAO extends GenericDAO<Sugerible> {

	public abstract List<Sugerible> findByUserId(int id_usuario, List<Atraccion> listaAtracciones, List<Promocion> listaPromociones);
	
	public abstract int insertAtraccion (int id_usuario, int id_atraccion);
	
	public abstract int insertPromocion (int id_usuario, int id_promocion);
}
