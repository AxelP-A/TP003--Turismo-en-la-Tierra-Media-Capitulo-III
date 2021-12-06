package services;

import java.util.List;

import model.Atraccion;
import persistence.AtraccionDAO;
import persistence.commons.DAOFactory;

public class AttractionService {

	public List<Atraccion> list() {
		return DAOFactory.getAtraccionDAO().findAll();
	}

	public Atraccion create(String name, Integer cost, Double duration, Integer capacity, String type) {

		Atraccion atraccion = new Atraccion(-1, name, cost, duration, capacity, type);

		if (atraccion.isValid()) {
			AtraccionDAO attractionDAO = DAOFactory.getAtraccionDAO();
			attractionDAO.insert(atraccion);
			// XXX: si no devuelve "1", es que hubo más errores
		}
		return atraccion;
	}

	public Atraccion update(Integer id, String nombre, int costo, Double duracion, int cupo) {

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Atraccion atraccion = atraccionDAO.find(id);

		atraccion.setNombre(nombre);
		atraccion.setCostoDeVisita(costo);
		atraccion.setTiempoNecesario(duracion);
		atraccion.setCupo(cupo);

		if (atraccion.isValid()) {
			atraccionDAO.update(atraccion);
			// XXX: si no devuelve "1", es que hubo más errores
		}
		return atraccion;
	}

	// Revisar manera de implementar correctamente, ya que nos null no los toma bien.
	public void delete(int id) {
		Atraccion atraccion = new Atraccion(id, null, null, null, null, null);
		
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		atraccionDAO.delete(atraccion);
	}

	public Atraccion find(int id) {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		return atraccionDAO.find(id);
	}
}