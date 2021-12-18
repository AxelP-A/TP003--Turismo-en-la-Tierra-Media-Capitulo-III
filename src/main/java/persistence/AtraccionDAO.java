package persistence;

import persistence.commons.GenericDAO;
import model.Atraccion;

public interface AtraccionDAO extends GenericDAO<Atraccion> {

	public abstract Atraccion findByAtraccionId(int id);
	public abstract int habilitar(Atraccion atraccion);
	public abstract int updateAtraccion(Atraccion atraccion);
}