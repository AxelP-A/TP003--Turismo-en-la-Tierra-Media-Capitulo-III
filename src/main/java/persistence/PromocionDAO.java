package persistence;

import java.util.List;

import model.Atraccion;
import model.Promocion;
import persistence.commons.GenericDAO;


public interface PromocionDAO extends GenericDAO<Promocion> {
	
	public abstract Promocion findByPromocion(String promocion);
	public abstract List<Promocion> findAll(List<Atraccion> listaAtracciones);
	public abstract Promocion findByPromocionId(int promocionId,List<Atraccion> listaAtracciones);
	public abstract int eliminarAtraccion(int id, int id2);
	public abstract int habilitar(Promocion promocion);
	int insertarAtraccion(int idPromocion, int idAtraccion);
	
	
}