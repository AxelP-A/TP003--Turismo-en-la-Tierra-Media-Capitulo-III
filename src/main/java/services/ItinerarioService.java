package services;

import java.util.ArrayList;
import java.util.List;

import model.Atraccion;
import model.Promocion;
import model.Usuario;
import persistence.AtraccionDAO;
import persistence.ItinerarioDAO;
import persistence.PromocionDAO;
import persistence.commons.DAOFactory;
import sugeribles.Sugerible;

public class ItinerarioService {

	private List<Promocion> promocionesVigentes = new ArrayList<Promocion>();
	private List<Atraccion> atracciones = new ArrayList<Atraccion>();
	private List<Sugerible> itinerario = new ArrayList<Sugerible>();


	public void cargarAtracciones() {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		this.atracciones = new ArrayList<>(atraccionDAO.findAll());
	}

	public void cargarPromociones() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		this.promocionesVigentes = new ArrayList<>(promocionDAO.findAll(this.atracciones));
	}
	
	
	
	public void cargarItinerario(Usuario usuario) {

		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		this.itinerario = itinerarioDAO.findByUserId(usuario.getId(), this.atracciones, this.promocionesVigentes);		
	}
	
	
	public List<Atraccion> crearListaAtraccionesAceptadas(Usuario usuario) {

		List<Atraccion> atraccionesAceptadas = new ArrayList<Atraccion>();
		List<Sugerible> itinerario = new ArrayList<Sugerible>();

		cargarItinerario(usuario);
		usuario.recibirItinerario(this.itinerario);

		for (int i = 0; i < itinerario.size(); i++) {
			if (itinerario.get(i).esPromocion()) {
				atraccionesAceptadas.addAll(((Promocion) itinerario.get(i)).getArrayAtracciones());
			} else {
				atraccionesAceptadas.add((Atraccion) itinerario.get(i));
			}
		}
		return atraccionesAceptadas;
	}

}
