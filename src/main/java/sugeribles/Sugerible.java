package sugeribles;

import java.util.List;

import model.Atraccion;

public interface Sugerible  {
	
	public String getNombre();
	
	public int getCostoDeVisita();
	
	public double getTiempoNecesario();
	
	//Este m�todo trae el tipo de la atracci�n o promoci�n. No el preferido del usuario.
	public String getTipo();
	
	public boolean comprobarCupo();	
	
	public void restarCupo();
	
	public void imprimirOferta();
	
	public boolean esPromocion();
	
	public void agregarAtraccion(Sugerible sugerible, List<Atraccion> atraccionesAceptadas);
	
	//public int getCupo();
	
	// public int getId();
}