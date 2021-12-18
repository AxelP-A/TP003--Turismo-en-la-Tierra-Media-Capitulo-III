package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Atraccion;
import model.Promocion;
import model.Usuario;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.commons.DAOFactory;
import sugeribles.ComparadorDeSugeribles;
import sugeribles.Sugerible;

public class CargadorDeSugeriblesService {

	private List<Promocion> promocionesVigentes = new ArrayList<Promocion>();
	private List<Atraccion> atracciones = new ArrayList<Atraccion>();
	//private List<Sugerible> sugerencias = new ArrayList<Sugerible>();
	
	public void cargarAtracciones() {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		this.atracciones = new ArrayList<>(atraccionDAO.findAll());
	}

	public void cargarPromociones() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		this.promocionesVigentes = new ArrayList<>(promocionDAO.findAll(this.atracciones));
	}
	
	private void ordenar(String preferida, List<Sugerible> sugerenciasOrdenadas) {
		Collections.sort(sugerenciasOrdenadas, new ComparadorDeSugeribles(preferida));
	}
	
	public void listaSugeribles(Usuario usuario, List<Sugerible> sugerenciasOrdenadas) {
		this.ordenar(usuario.getAtraccionPreferida(),sugerenciasOrdenadas);
	}
		
	public List<Promocion> getPromocionesVigentes() {
		return promocionesVigentes;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}
	
}