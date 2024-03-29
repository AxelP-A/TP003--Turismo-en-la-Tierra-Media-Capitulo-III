package services;

import java.util.ArrayList;
import java.util.List;

import model.Atraccion;
import model.Promocion;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.commons.DAOFactory;
import promociones.PromocionAbsoluta;
import promociones.PromocionAxB;
import promociones.PromocionPorcentual;

public class PromocionService {

	public List<Promocion> list(List<Atraccion> atracciones) {
		return DAOFactory.getPromocionDAO().findAll(atracciones);
	}

	public Promocion create(int id, String nombre, int costo, String tipo, String[] listaAtracciones,
			String descripcion, String fechaBaja) {

		List<Atraccion> atraccionesIncluidas = obtenerAtraccionesIncluidas(listaAtracciones);

		Promocion promocion = null;

		switch (tipo) {
		case "PORCENTUAL":
			promocion = new PromocionPorcentual(-1, nombre, atraccionesIncluidas, costo, descripcion, fechaBaja);
			break;
		case "AXB":
			promocion = new PromocionAxB(-1, nombre, atraccionesIncluidas, descripcion, fechaBaja);
			break;
		case "ABSOLUTA":
			promocion = new PromocionAbsoluta(-1, nombre, atraccionesIncluidas, costo, descripcion, fechaBaja);
			break;
		}
		

		if (promocion.isValid()) {
			PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
			int idPromocion = promocionDAO.insert(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
			for(Atraccion atraccion : promocion.getAtraccionesIncluidas()) {
				promocionDAO.insertarAtraccion(idPromocion, atraccion.getId());
			}
			
		}
		return promocion;
	}

	private List<Atraccion> obtenerAtraccionesIncluidas(String [] listaAtracciones) {

		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();

		for (String atraccion : listaAtracciones) {
			int idAtraccion = Integer.parseInt(atraccion);

			atracciones.add(atraccionDAO.findByAtraccionId(idAtraccion));
		}

		return atracciones;
	}

	public Promocion update(int id, String nombre, int costo, String descripcion,
			String [] atraccionesIncluidas) {

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		
			
		Promocion promocion = promocionDAO.findByPromocionId(id,atraccionDAO.findAll());

		promocion.setNombre(nombre);
		promocion.setCalculoDeCosto(costo);
		promocion.setDescripcion(descripcion);
		
		for(Atraccion atraccionAEli : promocion.getAtraccionesIncluidas()) {
			promocionDAO.eliminarAtraccion(id,atraccionAEli.getId());
		}
		
		List<Atraccion> atraccionesAIncluir = new ArrayList<Atraccion>();
		
		for (String atraccion : atraccionesIncluidas) {
			int idAtraccion = Integer.parseInt(atraccion);
			atraccionesAIncluir.add(atraccionDAO.findByAtraccionId(idAtraccion));
			promocionDAO.insertarAtraccion(id,atraccionDAO.findByAtraccionId(idAtraccion).getId());
		}
		
		promocion.setArrayAtracciones(atraccionesAIncluir);

		if (promocion.isValid()) {
				promocionDAO.update(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
		}
		return promocion;
	}

	public void delete(int id) {

		Promocion promocion = new PromocionAbsoluta(id);

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		promocionDAO.delete(promocion);
	}
	
	public void habilitar(int id) {

		Promocion promocion = new PromocionAbsoluta(id);

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		promocionDAO.habilitar(promocion);
	}

	public Promocion find(int id, List<Atraccion> listaAtracciones) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		return promocionDAO.findByPromocionId(id, listaAtracciones);
	}

}