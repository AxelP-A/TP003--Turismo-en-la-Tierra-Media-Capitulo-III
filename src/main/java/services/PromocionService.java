package services;

import java.util.List;

import model.Atraccion;
import model.Promocion;
import persistence.PromocionDAO;
import persistence.commons.DAOFactory;
import promociones.PromocionAbsoluta;
import promociones.PromocionAxB;
import promociones.PromocionPorcentual;

public class PromocionService {

	public List<Promocion> list(List<Atraccion> atracciones) {
		return DAOFactory.getPromocionDAO().findAll(atracciones);
	}

	public Promocion create(int id, String nombre, int costo, double tiempo, int cupo, String tipo, List<Atraccion> listaAtracciones) {

		Promocion promocion = null;
		
		switch(tipo) {
		case "PORCENTUAL":
			 promocion = new PromocionPorcentual(-1 , nombre , listaAtracciones, costo);
			break;
		case "AXB":
			 promocion = new PromocionAxB(-1 , nombre, listaAtracciones);
			break;
		case "ABSOLUTA":
			 promocion = new PromocionAbsoluta(-1 , nombre, listaAtracciones, costo);
			break;
		}

		if (promocion.isValid()) {
			PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
			promocionDAO.insert(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
		}
		return promocion;
	}

	public Promocion update(int id, String nombre, int costo) {

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion = promocionDAO.find(id);

		promocion.setNombre(nombre);
		promocion.setCalculoDeCosto(costo);
		
		if (promocion.isValid()) {
			promocionDAO.update(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
		}
		return promocion;
	}

	// Revisar manera de implementar correctamente, ya que nos null no los toma bien.
	public void delete(int id) {
		
		
		Promocion promocion = new PromocionAbsoluta(id);
		
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		promocionDAO.delete(promocion);
	}

	/*public Promocion find(int id) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		return promocionDAO.findByPromocionId(id);
	}*/

}