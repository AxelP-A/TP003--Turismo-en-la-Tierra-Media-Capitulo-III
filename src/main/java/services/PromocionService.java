package services;

import java.util.List;

import model.Promocion;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.commons.DAOFactory;
import promociones.PromocionAbsoluta;
import promociones.PromocionAxB;
import promociones.PromocionPorcentual;

public class PromocionService {

	public List<Promocion> list() {
		return DAOFactory.getPromocionDAO().findAll();
	}

	public Promocion create(int id, String nombre, int costo, double tiempo, int cupo, String tipo) {

		switch(tipo) {
		case "PORCENTUAL":
			PromocionPorcentual promocionPorcentual = new PromocionPorcentual(-1 ,tipo, nombre , costo);
			break;
		case "AXB":
			PromocionAxB promocionAxB = new PromocionAxB(-1 ,tipo, nombre);
			break;
		case "ABSOLUTA":
			PromocionAbsoluta promocionAbsoluta = new PromocionAbsoluta(-1  ,tipo, nombre, costo);
			break;
		}
		
		Promocion promocion = new Promocion(-1, nombre, costo, tiempo, cupo, tipo);	//acá va el tipo de promo, necesitamos los 3 constructores.
		

		if (promocion.isValid()) {
			PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
			promocionDAO.insert(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
		}
		return promocion;
	}

	public Promocion update(int id, String nombre, int costo, double tiempo, int cupo, String tipo) {

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion = promocionDAO.find(id);

		promocion.setNombre(nombre);
		promocion.setCostoDeVisita(costo);
		promocion.setTiempoNecesario(tiempo);
		promocion.setCupo(cupo);
		promocion.setTipo(tipo);

		if (promocion.isValid()) {
			promocionDAO.update(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
		}
		return promocion;
	}

	// Revisar manera de implementar correctamente, ya que nos null no los toma bien.
	public void delete(int id) {
		Promocion atraccion = new Atraccion(id, null, null, null, null, null);
		
		AtraccionDAO atraccionDAO = DAOFactory.getPromocionDAO();
		atraccionDAO.delete(atraccion);
	}

	public Promocion find(int id) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		return promocionDAO.findByPromocionId(id);
	}

}