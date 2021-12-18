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

	
	
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
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
	
	public int admin() {
		if(isAdmin()) {
			return 1;
		}
		return 0;
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
	
	public String encryptedPassword(String password){
		 setPassword(password);
		 return this.password;
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
		this.itinerario.add(sugerible);
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
		if (password == null || password.length() <= 3) {
			errors.put("password", "Debe tener más de 3 caracteres");	
		}
		if (presupuesto <= 0) {
			errors.put("presupuesto", "Debe ser positivo");
		}
		if(tiempoDisponible <= 0) {
			errors.put("tiempoDisponible", "Debe ser positivo");
		}
		if (tipoAtraccionPreferida == null) {
			errors.put("preferida", "Debe ser un string válido");
		}
		if (tipoAtraccionPreferida == null) {
			errors.put("admin", "Debe ser un string válido");
		}
	
	}
	

	public Map<String, String> getErrors() {
		return errors;
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