package persistence;

import java.util.List;

import model.Atraccion;
import model.Promocion;
import persistence.commons.GenericDAO;


public interface PromocionDAO extends GenericDAO<Promocion> {
	
	public abstract Promocion findByPromocion(String promocion);
	public abstract List<Promocion> findAll(List<Atraccion> listaAtracciones);
}