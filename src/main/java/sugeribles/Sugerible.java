package sugeribles;

import java.util.List;

import model.Atraccion;

public interface Sugerible  {
	
	public String getNombre();
	
	public int getCostoDeVisita();
	
	public double getTiempoNecesario();
	
	//Este metodo trae el tipo de la atraccion o promocion. No el preferido del usuario.
	public String getTipo();
	
	public boolean comprobarCupo();	
	
	public void restarCupo();
	
	public boolean esPromocion();
	
	public void agregarAtraccion(Sugerible sugerible, List<Atraccion> atraccionesAceptadas);
	
	public boolean estaHabilitada();
	
	public int getCupo();

	public int getId();
	
	public String getDescripcion();
	
}