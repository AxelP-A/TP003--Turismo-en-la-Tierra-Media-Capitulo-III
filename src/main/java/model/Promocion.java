package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import sugeribles.Sugerible;

public abstract class Promocion implements Sugerible {

	protected List<Atraccion> atraccionesIncluidas = new ArrayList<Atraccion>();
	private String nombre;
	private int id;
	private String habilitada;
	private int calculoDeCosto;
	private String descripcion;
	private String fechaBaja;
	private Map <String, String> errors;

	public Promocion(int id, String nombreDePromocion, List<Atraccion> listaAtracciones, String descripcion, String fechaBaja) {
		this.id = id;
		this.setNombre(nombreDePromocion);
		this.setArrayAtracciones(listaAtracciones);
		this.descripcion = descripcion;
		this.fechaBaja = fechaBaja;
	}
	
	public Promocion(int id, String nombreDePromocion, String descripcion) {
		this.id = id;
		this.setNombre(nombreDePromocion);
		this.atraccionesIncluidas = null;
		this.descripcion = descripcion;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public void restarCupo() {

		for (int i = 0; i < atraccionesIncluidas.size(); i++) {
			atraccionesIncluidas.get(i).restarCupo();
		}
	}

	@Override
	public boolean comprobarCupo() {

		for (int i = 0; i < atraccionesIncluidas.size(); i++) {
			if (!atraccionesIncluidas.get(i).comprobarCupo()) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int getCupo() {
		
		int minimo = atraccionesIncluidas.get(0).getCupo();
		
		for (int i = 0; i < atraccionesIncluidas.size(); i++) {	
			if (minimo > atraccionesIncluidas.get(i).getCupo()) {
				minimo = atraccionesIncluidas.get(i).getCupo();
			}
		}
		return minimo;
	}

	/**
	 * Obtenemos el tipo de la atracci�n de la posici�n 0, ya que todas las
	 * atracciones van a tener el mismo tipo.
	 */
	@Override
	public String getTipo() {
		return this.atraccionesIncluidas.get(0).getTipo();
	}

	@Override
	public double getTiempoNecesario() {
		double tiempoTotal = 0;

		for (int i = 0; i < atraccionesIncluidas.size(); i++) {
			tiempoTotal += atraccionesIncluidas.get(i).getTiempoNecesario();
		}
		return tiempoTotal;
	}

	@Override
	public void agregarAtraccion(Sugerible sugerible, List<Atraccion> lista) {
		for (int i = 0; i < atraccionesIncluidas.size(); i++) {
			lista.add(atraccionesIncluidas.get(i));
		}
	}

	@Override
	public boolean esPromocion() {
		return true;
	}
	
	@Override
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean estanHabilitadas() {

		for (int i = 0; i < atraccionesIncluidas.size(); i++) {
			if (!atraccionesIncluidas.get(i).estaHabilitada()) {
				return false;
			}
		}
		return true;
	}
	
	public List<Atraccion> getAtraccionesIncluidas() {
		return atraccionesIncluidas;
	}
	
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public boolean estaHabilitada() {
		return (this.habilitada == null && estanHabilitadas());
	}

	public int getCalculoDeCosto() {
		return calculoDeCosto;
	}

	public void setCalculoDeCosto(double nuevoCalculo) {
	}

	@Override
	public void imprimirOferta() {

		System.out.println("Usted esta accediendo a la promocion: " + this.getNombre().toUpperCase() + ".");
		System.out.println("Esta promo incluye las siguientes atracciones:");

		for (int i = 0; i < this.getArrayAtracciones().size(); i++) {
			System.out
					.println((i + 1) + ". " + this.getNombreAtraccion(this.getArrayAtracciones().get(i)).toUpperCase());
		}
		System.out.println("El costo de la promocion es: " + this.getCostoDeVisita() + " monedas.");
		System.out.println("La duracion aproximada del recorrido es de: " + this.getTiempoNecesario() + " horas.");
		System.out.println("-----------------------------------------------------------------");
	}

	public String getNombreAtraccion(Atraccion atraccion) {
		return atraccion.getNombre();
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Atraccion> getArrayAtracciones() {
		return this.atraccionesIncluidas;
	}

	/**
	 * Pasamos por par�metro las atracciones que estar�n inclu�das en la promoci�n.
	 * 
	 * @param atraccionesIncluidas
	 */
	public void setArrayAtracciones(List<Atraccion> atraccionesIncluidas) {
		this.atraccionesIncluidas = atraccionesIncluidas;
	}

	public int getId() {
		return id;
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	public void validate() {
		errors = new HashMap<String, String>();
		
		if (this.getCostoDeVisita() <= 0) {
			errors.put("costoDeVisita", "Debe ser positivo");
		}
		if(this.getTiempoNecesario() <= 0) {
			errors.put("tiempoNecesario", "Debe ser positivo");
		}
		if(this.getCupo() <=0) {
			errors.put("cupoDePersonas", "Debe ser positivo");
		}
		if (this.getTipo() == null) {
			errors.put("tipo", "Debe ser un string válido");
		}
		if (this.getDescripcion() == null) {
			errors.put("descripcion", "Debe ser un string válido");
		}
		if (!this.validarAtracciones()) {
			errors.put("atracciones", "Error en atracciones incluidas");
		}
	}
	
	private boolean validarAtracciones() {

		for (int i = 0; i < atraccionesIncluidas.size(); i++) {
			if (!atraccionesIncluidas.get(i).isValid()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(atraccionesIncluidas, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return Objects.equals(atraccionesIncluidas, other.atraccionesIncluidas) && id == other.id
				&& Objects.equals(nombre, other.nombre);
	}
}