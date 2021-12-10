package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import sugeribles.Sugerible;

public class Atraccion implements Sugerible {

	private String nombre;
	protected int costoDeVisita;
	protected double tiempoNecesario;
	protected int cupoDePersonas;
	private String tipo;
	private int id;
	private String habilitada;
	private Map <String, String> errors;

	public Atraccion(int id, String nombre, int costo, double tiempo, int cupo, String tipo)  { 
					
		this.id = id;
		this.nombre = nombre;;
		this.costoDeVisita = costo;
		this.tiempoNecesario = tiempo;
		this.cupoDePersonas = cupo;
		this.tipo = tipo;
	}

	public Atraccion(String nombre, int costo, double tiempo, int cupo, String tipo)  { 
		
		this.nombre = nombre;;
		this.costoDeVisita = costo;
		this.tiempoNecesario = tiempo;
		this.cupoDePersonas = cupo;
		this.tipo = tipo;
	}
	
	public Atraccion(int id, String nombre, int costo, double tiempo, int cupo, String tipo, String fechaBaja)  { 
		
		this.id = id;
		this.nombre = nombre;;
		this.costoDeVisita = costo;
		this.tiempoNecesario = tiempo;
		this.cupoDePersonas = cupo;
		this.tipo = tipo;
		this.habilitada = fechaBaja;
	}
	


	public Atraccion(int id) {
		this.id = id;
		this.nombre = null;
		this.costoDeVisita = 0;
		this.tiempoNecesario = 0;
		this.cupoDePersonas = 0;
		this.tipo = null;
	}
	
	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public int getCostoDeVisita() {
		return costoDeVisita;
	}

	@Override
	// Considerar pooner un int en el cupo, para poder comprar por ejemplo, un pack familiar.
	public boolean comprobarCupo() {
		return this.cupoDePersonas > 0;
	}

	@Override
	public String getTipo() {
		return tipo;
	}

	@Override
	public double getTiempoNecesario() {
		return this.tiempoNecesario;
	}

	@Override
	// Considerar pooner un int en el cupo, para poder comprar por ejemplo, un pack familiar.
	public void restarCupo() {
		this.cupoDePersonas--;
	}
	
	@Override
	public boolean esPromocion() {
		return false;
	}
	
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();
		
		if (costoDeVisita <= 0) {
			errors.put("costoDeVisita", "Debe ser positivo");
		}
		if(tiempoNecesario <= 0) {
			errors.put("tiempoNecesario", "Debe ser positivo");
		}
		if(cupoDePersonas <=0) {
			errors.put("cupoDePersonas", "Debe ser positivo");
		}
		if (tipo == null) {
			errors.put("tipo", "Debe ser un string válido");
		}
	}
	
	@Override 
	public void agregarAtraccion(Sugerible sugerible, List<Atraccion> lista) {
		lista.add((Atraccion) sugerible);
	}
	
	@Override
	public void imprimirOferta() {
		
		System.out.println("Usted esta accediendo a la atraccion: " + this.getNombre().toUpperCase() + ".");
		System.out.println("El costo de la atraccion es: " + this.getCostoDeVisita() + " monedas.");
		System.out.println("La duracion aproximada del recorrido es de: " + this.getTiempoNecesario() + " horas.");
		System.out.println("-----------------------------------------------------------------");
	}
	
	public void setNombre(String nuevoNombre) {
		this.nombre = nuevoNombre;
	}
	
	public void setTiempoNecesario(double tiempoNecesario) {
		this.tiempoNecesario = tiempoNecesario;
	}
	
	public void setCostoDeVisita(int costoDeVisita){
		this.costoDeVisita = costoDeVisita;
	}
	
	public void setTipo(String nuevoTipo) {
		this.tipo = nuevoTipo;
	}
	
	public int getId() {
		return id;
	}

	public int getCupo() {
		return this.cupoDePersonas;
	}
	
	public void setCupo(int nuevoCupo) {
		this.cupoDePersonas = nuevoCupo;
	}
	
	public boolean estaHabilitada() {
		return this.habilitada == null;
	}
	
	
	

	@Override
	public String toString() {
		return "Atraccion [nombre=" + nombre + ", costoDeVisita=" + costoDeVisita + ", tiempoNecesario="
				+ tiempoNecesario + ", cupoDePersonas=" + cupoDePersonas + ", tipo=" + tipo + ", id=" + id
				+ ", habilitada=" + habilitada + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(costoDeVisita, cupoDePersonas, id, nombre, tiempoNecesario, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return costoDeVisita == other.costoDeVisita && cupoDePersonas == other.cupoDePersonas && id == other.id
				&& Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(tiempoNecesario) == Double.doubleToLongBits(other.tiempoNecesario)
				&& Objects.equals(tipo, other.tipo);
	}
}