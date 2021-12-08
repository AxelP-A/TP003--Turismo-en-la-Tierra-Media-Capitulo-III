package app;

import java.io.*;
import java.util.*;

import excepciones.InvalidNumberException;
import model.Atraccion;
import model.Promocion;
import model.Usuario;
import sugeribles.ComparadorDeSugeribles;
import sugeribles.Sugerible;
import persistence.AtraccionDAO;
import persistence.commons.DAOFactory;
import persistence.ItinerarioDAO;
import persistence.PromocionDAO;
import persistence.UsuarioDAO;

public class App {

	private List<Promocion> promocionesVigentes = new ArrayList<Promocion>();
	private List<Atraccion> atracciones = new ArrayList<Atraccion>();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private List<Sugerible> sugerencias = new ArrayList<Sugerible>();

	public void agregarUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	public void agregarAtraccion(Atraccion atraccion) {
		this.atracciones.add(atraccion);
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void cargarUsuarios() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();
		this.usuarios = new ArrayList<>(userDAO.findAll());
	}

	public void cargarAtracciones() {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		this.atracciones = new ArrayList<>(atraccionDAO.findAll());
	}

	public void cargarPromociones() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		this.promocionesVigentes = new ArrayList<>(promocionDAO.findAll(this.atracciones));
	}

	public void setSugerenciasParaTest(Sugerible sugerible) {
		this.sugerencias.add(sugerible);
	}

	public List<Sugerible> getSugerenciasParaTest() {
		return this.sugerencias;
	}

	/**
	 * Recibimos los par�metros para crear una promo y agregarla al listado de
	 * promocionesVigentes.En
	 * 
	 * @param nombre
	 * @param listaDeAtracciones
	 * @param descuento
	 * @param tipo
	 */
	public void agregarPromocion(Promocion promocion) {
		this.promocionesVigentes.add(promocion);
	}

	public void ordenar(String preferida) {
		Collections.sort(sugerencias, new ComparadorDeSugeribles(preferida));
	}

	public void listaSugeribles(Usuario usuario) {

		this.sugerencias.addAll(this.promocionesVigentes);
		this.sugerencias.addAll(this.atracciones);
		this.ordenar(usuario.getAtraccionPreferida());
	}

	/**
	 * Tenemos una lista de atracciones y una de promociones. El printWriter del
	 * m�todo cargar promociones, va a leer y cargar la lista de las promociones, lo
	 * mismo con las atracciones, las cuales van a estar divididas entre Promociones
	 * preferidas y no preferidas, y atracciones aceptadas y no aceptadas, [tenemos
	 * en cuenta que esto puede reducirse con la implementaci�n de Sugerible] estos
	 * arrays van a ir a un ciclo for con el siguiente orden: promociones
	 * preferidas, atracciones preferidas, promociones no preferidas y atracciones
	 * no preferidas. En cada ciclo, vamos a comprobar que el usuario tenga oro y
	 * tiempo para acceder a la atracci�n, si los tiene, le va a consultar en cada
	 * ciclo al usuario si acepta la atracci�n. Si no acepta, pasamos al siguiente
	 * elemento y volvemos a hacer la comprobaci�n y la consulta. Si acepta, vamos a
	 * restar 1 cupo en la atracci�n, vamos a guardar en una variable el dato del
	 * costo y el tiempo necesario para hacer esa atracci�n, y se los restamos al
	 * usuario que la acept�, guardamos la atracci�n aceptada en un array que vamos
	 * a mostrar en el retorno.
	 * 
	 * @param usuario
	 * @return
	 * @throws IOException
	 * @throws InvalidNumberException
	 */
	public void ofertarMientrasQueHayaOroYtiempo(Usuario usuario) throws IOException, InvalidNumberException {

		List<Atraccion> atraccionesAceptadas = new ArrayList<Atraccion>();
		List<Sugerible> itinerario = new ArrayList<Sugerible>();

		itinerario = cargarItinerario(usuario);
		usuario.recibirItinerario(itinerario);
		
		for (int i = 0; i < itinerario.size(); i++) {
			if (itinerario.get(i).esPromocion()) {
				atraccionesAceptadas.addAll(((Promocion) itinerario.get(i)).getArrayAtracciones());
			} else {
				atraccionesAceptadas.add((Atraccion) itinerario.get(i));
			}
		}

		this.listaSugeribles(usuario);

		if (itinerario.isEmpty()) {
			mensajeBienvenida();
		} else {
			mensajeBienvenida2(usuario);
		}

		for (int i = 0; i < sugerencias.size(); i++) {
			if (sugerencias.get(i).esPromocion()) {
				if (!seRepiteAtraccionEnPromocion((Promocion) sugerencias.get(i), atraccionesAceptadas) // contains
						&& sugerencias.get(i).getTiempoNecesario() <= usuario.getTiempoDisponible()
						&& sugerencias.get(i).getCostoDeVisita() <= usuario.getPresupuesto()
						&& sugerencias.get(i).comprobarCupo()) {

					aceptaOferta(usuario, atraccionesAceptadas, itinerario, i);
				}
			} else {
				if (!estaAtraccionEnAtracciones((Atraccion) sugerencias.get(i), atraccionesAceptadas)
						&& sugerencias.get(i).getTiempoNecesario() <= usuario.getTiempoDisponible()
						&& sugerencias.get(i).getCostoDeVisita() <= usuario.getPresupuesto()
						&& sugerencias.get(i).comprobarCupo()) {

					aceptaOferta(usuario, atraccionesAceptadas, itinerario, i);
				}
			}
		}
		actualizarAtraccionesAceptadasYusuario(usuario, atraccionesAceptadas, itinerario);
		this.imprimirItinerario(usuario);
	}

	private void actualizarAtraccionesAceptadasYusuario(Usuario usuario, List<Atraccion> atraccionesAceptadas,
			List<Sugerible> itinerario) {

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();

		for (int i = 0; i < atraccionesAceptadas.size(); i++) {
			atraccionDAO.update(atraccionesAceptadas.get(i));
		}
		usuarioDAO.update(usuario);

		for (int i = 0; i < itinerario.size(); i++) {
			if (itinerario.get(i).esPromocion()) {
				itinerarioDAO.insertPromocion(usuario.getId(), ((Promocion) itinerario.get(i)).getId());
			} else {
				itinerarioDAO.insertAtraccion(usuario.getId(), ((Atraccion) itinerario.get(i)).getId());
			}
		}
	}

	private void mensajeBienvenida() {
		System.out.println("");
		System.out.println("Bienvenido/a a nuestra empresa de Turismo. ¡Esperamos que disfrute su estadía!");
		System.out.println(
				"El 5% de lo recaudado en la actividad, será donado para la investigacion de metodos sofisticados de limpieza e higiene personal");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------");
	}

	private void mensajeBienvenida2(Usuario usuario) {
		System.out.println("");
		System.out.println("Bienvenido/a nuevamente a nuestra empresa de Turismo. ¡Esperamos que disfrute su estadía!");
		System.out.println("Su presupuesto actual es de: " + usuario.getPresupuesto()
				+ " monedas, y su tiempo disponible es de: " + usuario.getTiempoDisponible() + " hs");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------");
	}

	private void aceptaOferta(Usuario usuario, List<Atraccion> atraccionesAceptadas, List<Sugerible> itinerario,
			int i) {

		if (usuario.aceptaOferta(sugerencias.get(i))) {
			sugerencias.get(i).restarCupo();
			usuario.aceptoOfertaSugeridaYseDescontoTiempoYpresupuesto(sugerencias.get(i));
			itinerario.add(sugerencias.get(i));
			System.out.println("Su presupuesto actual es de: " + usuario.getPresupuesto());
			System.out.println("Su tiempo disponible es de: " + usuario.getTiempoDisponible());
			System.out.println("------------------------------------------------------------");
			sugerencias.get(i).agregarAtraccion(sugerencias.get(i), atraccionesAceptadas);
		}
	}

	public List<Sugerible> cargarItinerario(Usuario usuario) {

		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		List<Sugerible> sugerencias = new ArrayList<Sugerible>();
		
		sugerencias = itinerarioDAO.findByUserId(usuario.getId(), this.atracciones, this.promocionesVigentes);
		System.out.println(sugerencias);
		return sugerencias;
	}

	public void ofertarMientrasQueHayaOroYtiempoAtodosLosUsuarios() throws IOException, InvalidNumberException {

		for (int i = 0; i < usuarios.size(); i++) {
			System.out.println("Bienvenido/a: " + usuarios.get(i).getNombre());
			ofertarMientrasQueHayaOroYtiempo(usuarios.get(i));
			sugerencias.clear();
		}
	}

	/**
	 * Revisamos entre las promociones y atracciones que entre las promos sugeridas
	 * si se repiten o no, devolvemos un boolean. Funciona, crean en nosotros.
	 * 
	 * @param unaAtraccion
	 * @return
	 */
	private boolean estaAtraccionEnAtracciones(Atraccion atraccion, List<Atraccion> atraccionesAceptadas) {

		for (Atraccion Atr : atraccionesAceptadas) {
			if (Atr.equals(atraccion)) {
				return true;
			}
		}
		return false;
	}

	private boolean seRepiteAtraccionEnPromocion(Promocion promocion, List<Atraccion> atraccionesAceptadas) {

		for (Atraccion unaAtraccion : promocion.getArrayAtracciones()) { // atracciones inclu�das.
			if (estaAtraccionEnAtracciones(unaAtraccion, atraccionesAceptadas)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * El m�todo almacena el costo y el tiempo de cada atracci�n o promoci�n
	 * aceptada y los suma hasta terminar de recorrer la lista, mostramos ambos
	 * resultados al final de la ejecuci�n. Luego, mostramos en consola (y al mismo
	 * tiempo almacenamos en el archivo de salida) el nombre de cada atracci�n o
	 * promoci�n aceptada. El DateTimeFormatter y el LocalDateTime se utilizan para
	 * generar un nombre de archivo �nico (en base a la fecha y hora), junto con el
	 * nombre de usuario.
	 * 
	 * @throws IOException
	 */
	public void imprimirItinerario(Usuario usuario) throws IOException {

		int costoTotal = 0;
		double tiempoTotal = 0.0;

		System.out.println("Ud. ha adquirido: ");
		for (int i = 0; i < usuario.getItinerario().size(); i++) {
			if (usuario.getItinerario().get(i) != null) {
				costoTotal += usuario.getItinerario().get(i).getCostoDeVisita();
				tiempoTotal += usuario.getItinerario().get(i).getTiempoNecesario();
				
				if (usuario.getItinerario().get(i).esPromocion()) {
					System.out.println((i + 1) + ". " + usuario.getItinerario().get(i).getNombre().toUpperCase() + ", con un costo total de " + usuario.getItinerario().get(i).getCostoDeVisita()
							+ " monedas,"  + " y un tiempo necesario para realizar el recorrido de " + usuario.getItinerario().get(i).getTiempoNecesario() + " horas.");
					System.out.println("Que contiene las atracciones:");
					
					for(int j = 0; j < ((Promocion)usuario.getItinerario().get(i)).getArrayAtracciones().size(); j++){			
					System.out.println("* " + ((Promocion)usuario.getItinerario().get(i)).getArrayAtracciones().get(j).getNombre());
					}
				} else {			
					System.out.println((i + 1) + ". " + usuario.getItinerario().get(i).getNombre().toUpperCase() + ", con un costo total de " + usuario.getItinerario().get(i).getCostoDeVisita()
							+ " monedas,"  + " y un tiempo necesario para realizar el recorrido de " + usuario.getItinerario().get(i).getTiempoNecesario() + " horas.");
				}
			}
		}
		System.out.println("");
		System.out.println("El costo total de su itinerario es: " + costoTotal + " monedas.");
		System.out.println("La duracion total de su itinerario es de: " + tiempoTotal + " horas.");
		System.out.println("Muchas gracias por haber elegido nuestros servicios.¡Esperamos que disfrute su recorrido!");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
	}

	public static void main(String[] args) throws InvalidNumberException, IOException {

		Usuario Bruno = new Usuario(1, "Bruno", "AVENTURA", 10000, 12.00, "12345", true);
		
	
		App sistema = new App();
		/*sistema.cargarUsuarios();
		sistema.cargarAtracciones();
		sistema.cargarPromociones();*/
		
		
		sistema.cargarItinerario(Bruno);
		
		/*Bruno.setPassword("1234");
		System.out.println(Bruno.getPassword());	*/
		
		/*sistema.ofertarMientrasQueHayaOroYtiempoAtodosLosUsuarios();*/
	}
}