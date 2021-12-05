package persistence;

import persistence.commons.GenericDAO;
import model.Atraccion;


public interface AtraccionDAO extends GenericDAO<Atraccion> {

	public abstract Atraccion findByAtraccionId(int id);
	
}