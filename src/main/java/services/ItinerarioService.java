package services;

import java.util.ArrayList;
import java.util.List;
import model.Atraccion;
import model.Promocion;
import model.Usuario;
import persistence.ItinerarioDAO;
import persistence.commons.DAOFactory;
import sugeribles.Sugerible;

public class ItinerarioService {

	CargadorDeSugeriblesService cargadorDeSugeribles = new CargadorDeSugeriblesService(); 
	private List<Sugerible> itinerario = new ArrayList<Sugerible>();

	
	public void cargarItinerario(Usuario usuario) {	
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		this.itinerario = itinerarioDAO.findByUserId(usuario.getId(), cargadorDeSugeribles.getAtracciones(), cargadorDeSugeribles.getPromocionesVigentes());		
	}
	
	
	public List<Atraccion> crearListaAtraccionesAceptadas(Usuario usuario) {
		
		List<Atraccion> atraccionesAceptadas = new ArrayList<Atraccion>();
		cargadorDeSugeribles.cargarAtracciones();
		cargadorDeSugeribles.cargarPromociones();
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
	
	
	
	/*public static void main(String[] args) throws InvalidNumberException, IOException {
		
		ItinerarioService itinerarioS = new ItinerarioService();
		
		Usuario Bruno = new Usuario(1, "Bruno", "AVENTURA", 10000, 12.00, "12345", true);
		
		itinerarioS.crearListaAtraccionesAceptadas(Bruno);	
		
	}*/

}
