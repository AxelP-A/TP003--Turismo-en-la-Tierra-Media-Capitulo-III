package services;

import java.util.List;

import model.Atraccion;
import persistence.AtraccionDAO;
import persistence.commons.DAOFactory;

public class AttractionService {

	public List<Atraccion> list() {
		return DAOFactory.getAtraccionDAO().findAll();
	}

	public Atraccion create(int id, String nombre, int costo, double tiempo, int cupo, String tipo, String descripcion) {

		Atraccion atraccion = new Atraccion(-1, nombre, costo, tiempo, cupo, tipo, descripcion);

		if (atraccion.isValid()) {
			AtraccionDAO attractionDAO = DAOFactory.getAtraccionDAO();
			attractionDAO.insert(atraccion);
			// XXX: si no devuelve "1", es que hubo más errores
		}
		return atraccion;
	}

	public Atraccion updateAtraccion(int id, String nombre, int costo, double tiempo, int cupo, String tipo, String descripcion) {
		
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Atraccion atraccion = atraccionDAO.findByAtraccionId(id);
		
		atraccion.setNombre(nombre);
		atraccion.setCostoDeVisita(costo);
		atraccion.setTiempoNecesario(tiempo);
		atraccion.setCupo(cupo);
		atraccion.setTipo(tipo);
		atraccion.setDescripcion(descripcion);

		if (atraccion.isValid()) {
			atraccionDAO.updateAtraccion(atraccion);
			// XXX: si no devuelve "1", es que hubo más errores
		}
		return atraccion;
	}

	public void delete(int id) {
		Atraccion atraccion = new Atraccion(id);
		
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		atraccionDAO.delete(atraccion);
	}
	
	public void habilitar(int id) {
		Atraccion atraccion = new Atraccion(id);
		
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		atraccionDAO.habilitar(atraccion);
	}

	public Atraccion find(int id) {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		return atraccionDAO.findByAtraccionId(id);
	}
}