package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.*; // Necesario para la creaci�n del panel
import sugeribles.Sugerible;
import utils.Crypt;

public class Usuario {

	private int id;
	private String nombre;
	protected int presupuesto;
	protected double tiempoDisponible;
	private String tipoAtraccionPreferida;
	private List<Sugerible> itinerario = new ArrayList<Sugerible>();
	private String password;
	private boolean isAdmin;
	private String habilitado;
	private Map<String, String> errors;

	public Usuario(int id, String nombre, String atraccionPreferida, int presupuesto, double tiempoDisponible) {
		this.id = id;
		this.nombre = nombre;
		this.tipoAtraccionPreferida = atraccionPreferida;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
	}

	public Usuario(Integer id, String nombre, String atraccionPreferida, int presupuesto, double tiempoDisponible,
			String password, boolean isAdmin, String fechaBaja) {
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.tipoAtraccionPreferida = atraccionPreferida;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.isAdmin = isAdmin;
		this.habilitado = fechaBaja;
	}

	public Usuario(int id) {
		this.id = id;
		this.nombre = null;
		this.password = null;
		this.tipoAtraccionPreferida = null;
		this.presupuesto = 0;
		this.tiempoDisponible = 0;
		this.isAdmin = false;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getPassword() {
		return password;
	}

	public boolean checkPassword(String password) {
		// this.passwpord es el hash de la pw.
		return Crypt.match(password, this.password);
	}

	public String getAtraccionPreferida() {
		return this.tipoAtraccionPreferida;
	}

	public int getPresupuesto() {
		return this.presupuesto;
	}

	public double getTiempoDisponible() {
		return this.tiempoDisponible;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public boolean isNull() {
		return false;
	}

	public List<Sugerible> getItinerario() {
		return this.itinerario;
	}

	public void setNombre(String nuevoNombre) {
		this.nombre = nuevoNombre;
	}

	public void setPassword(String newPassword) {
		this.password = Crypt.hash(newPassword);
	}

	public void setTiempoDisponible(double nuevoTiempoDisponible) {
		this.tiempoDisponible = nuevoTiempoDisponible;
	}

	public void setPresupuesto(int nuevoPresupuesto) {
		this.presupuesto = nuevoPresupuesto;
	}

	public void setAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public void setAtraccionPreferida(String tipoAtraccionPreferida) {
		this.tipoAtraccionPreferida = tipoAtraccionPreferida;
	}

	public boolean tieneDinero(Sugerible sugerible) {
		return sugerible.getCostoDeVisita() <= this.presupuesto;
	}

	public boolean tieneTiempo(Sugerible sugerible) {
		return sugerible.getTiempoNecesario() <= this.tiempoDisponible;
	}

	public void recibirItinerario(List<Sugerible> itinerario) {
		this.itinerario = itinerario;
	}

	public void agregarAlItinerario(Sugerible sugerible) {
		this.itinerario.add(sugerible); // Esto actualmente se realiza en el método aceptaOferta de la App.
		aceptoOfertaSugeridaYseDescontoTiempoYpresupuesto(sugerible);

	}

	public void aceptoOfertaSugeridaYseDescontoTiempoYpresupuesto(Sugerible sugerencia) {

		this.tiempoDisponible -= sugerencia.getTiempoNecesario();
		this.presupuesto -= sugerencia.getCostoDeVisita();
	}

	public boolean estaActivo() {
		return this.habilitado == null;
	}

	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}

	public void validate() {
		errors = new HashMap<String, String>();
		
		if (nombre == null) {
			errors.put("nombre", "Debe ser un string válido");
		}
		if (password == null || password.length() <= 4) {
			errors.put("password", "Debe tener más de 4 caracteres");	
		}
		if (presupuesto <= 0) {
			errors.put("presupuesto", "Debe ser positivo");
		}
		if(tiempoDisponible <= 0) {
			errors.put("tiempo", "Debe ser positivo");
		}
		if (tipoAtraccionPreferida == null) {
			errors.put("preferencia", "Debe ser un string válido");
		}
	}

	/*
	 * public boolean noCompro(Atraccion atraccion) {
	 * 
	 * List<Atraccion> listaAtraccion =
	 * itinerarioService.crearListaAtraccionesAceptadas(); if(listaAtraccion ==
	 * null) { return true; } for (Atraccion Atr : listaAtraccion) { if
	 * (Atr.equals(atraccion)) { return false; } } return true; }
	 */

	/*
	 * Mostramos la promo o atracci�n para que la misma pueda ser visualizada por
	 * consola por el usuario. Inicializamos un String respuesta en null, el cual va
	 * a tomar el valor de que sea ingresado por el usuario, un String S y uno N que
	 * los creamos solo para que el c�digo se vea m�s elegante. Dentro del Do, va a
	 * estar todo lo que va a ejecutarse antes de que el while compruebe si tiene
	 * que volver a repetir todo lo que realicemos dentro del do. Lo primero que
	 * tenemos dentro del do, es la solicitud al usuario de que ingrese Si o No, ya
	 * que es el mensaje que queremos repetir cada vez que se ingrese un dato
	 * incorrecto, aunque esto no pasar� gracias al panel ( y la primera vez que
	 * interact�e el usuario tambi�n). Luego de eso, al String respuesta le damos el
	 * valor de la pr�xima l�nea que lea el scanner (leer es el scanner creado, y
	 * nextLine el m�todo invocado por el mismo). Luego, comprobamos si la respuesta
	 * de la persona equivale al valor del String "S" (el cual fue definido como
	 * �Si�), ignorando si fue escrita en min�scula o may�scula gracias al
	 * equalsIgnorecase, que es un m�todo de los String (recordemos que S fue
	 * declarado como un String), si lo ingresado por el usuario coincide con el
	 * valor almacenado en S, entonces cerramos el scanner y devolvemos True, sino
	 * comprobamos si la respuesta de la persona equivale al valor del String N (el
	 * cual fue definido como �No�, nuevamente no importa si fue escrito en
	 * min�scula o may�scula gracias al equalsIgnorecase, si lo ingresado por el
	 * usuario coincide con el valor almacenado en N, entonces cerramos el scanner y
	 * devolvemos false, ya que significa que el usuario rechaz� la oferta. Si
	 * tampoco ingres� �No�, significa que entonces ingres� un valor no correcto,
	 * por lo tanto, tenemos un While que nos va a devolver al inicio del ciclo si
	 * la respuesta no es Si o No. En ese caso, se le volver� a pedir al usuario que
	 * ingrese una respuesta, que ser� comprobada nuevamente hasta que la misma sea
	 * correcta.
	 */
	public boolean aceptaOferta(Sugerible sugerible) {

		sugerible.imprimirOferta();
		System.out.println("¿Acepta la oferta?");
		String respuesta = null, S = "Si", N = "No";

		do {
			System.out.println("");
			System.out.println("Por favor, ingrese Si o No");
			respuesta = crearPanelSiOno();

			if (respuesta.equalsIgnoreCase(S)) {
				avisoCompraAceptada();
				System.out.println("------------------------------");
				return true;
			}
			if ((respuesta.equalsIgnoreCase(N))) {
				avisoCompraNoAceptada();
				System.out.println("------------------------------");
				return false;
			}
		} while (!(respuesta.equalsIgnoreCase(S) || respuesta.equalsIgnoreCase(N)));
		System.out.println("El programa se ha comportado de forma inesperada");
		return false;
	}

	/*
	 * Con este m�todo, creamos un panel gr�fico que muestra las opciones Si o no,
	 * lo utilizamos para no tener que tipear la respuesta por consola y como m�todo
	 * de firma personal del grupo 404 Adem�s de retornar el valor necesario para el
	 * m�todo aceptaOferta, tambi�n lo muestra por consola para cumplir con la
	 * premisa de que la interacci�n se vea representada en la consola.
	 * 
	 */
	public String crearPanelSiOno() {

		JFrame jframe = new JFrame();
		jframe.setAlwaysOnTop(true);

		// las 2 líneas de arriba no existían, y el jframe era null en el parámetro.

		int seleccion = JOptionPane.showOptionDialog(jframe, "¿Acepta la compra?", "Seleccione opcion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, // null para icono por defecto.
				null, new Object[] { "Si", "No" }, null);

		if (seleccion == 0) {
			System.out.println("Si");
			return "Si";
		}
		System.out.println("No");
		return "No";
	}

	public void avisoCompraAceptada() {

		JFrame jframe = new JFrame();
		jframe.setAlwaysOnTop(true);

		JOptionPane.showMessageDialog(jframe, "Usted ha aceptado la oferta");
		System.out.println("Usted ha aceptado la oferta");
	}

	public void avisoCompraNoAceptada() {

		JFrame jframe = new JFrame();
		jframe.setAlwaysOnTop(true);

		JOptionPane.showMessageDialog(jframe, "Usted no ha aceptado la oferta");
		System.out.println("Usted no ha aceptado la oferta");
	}

	@Override
	public String toString() {

		return '\n' + "[ " + "Id usuario: " + id + " - Nombre usuario: " + nombre + " - Tipo de atraccion preferida: "
				+ tipoAtraccionPreferida + " - Presupuesto: " + presupuesto + " monedas" + " - Tiempo disponible: "
				+ tiempoDisponible + " hs" + " - ¿Es admin?: " + isAdmin + " ]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, itinerario, nombre, presupuesto, tiempoDisponible, tipoAtraccionPreferida);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return id == other.id && Objects.equals(itinerario, other.itinerario) && Objects.equals(nombre, other.nombre)
				&& presupuesto == other.presupuesto
				&& Double.doubleToLongBits(tiempoDisponible) == Double.doubleToLongBits(other.tiempoDisponible)
				&& Objects.equals(tipoAtraccionPreferida, other.tipoAtraccionPreferida);
	}


}