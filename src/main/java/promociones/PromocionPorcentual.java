package promociones;

import java.util.List;

import model.Atraccion;
import model.Promocion;

public class PromocionPorcentual extends Promocion {

	private final double PORCENTAJE_DESCUENTO;

	public PromocionPorcentual(int id, String nombre, List<Atraccion> listaAtracciones, double porcentaje) {
		super(id, nombre, listaAtracciones);
		this.PORCENTAJE_DESCUENTO = porcentaje;
	}

	/**
	 * Como el porcentaje estaba definido como int, no estaba realizando el c�lculo
	 * correctamente al dividir por 100. 
	 */
	@Override
	public int getCostoDeVisita() {

		double costoTotal = 0;

		for (int i = 0; i < super.atraccionesIncluidas.size(); i++) {
				costoTotal += atraccionesIncluidas.get(i).getCostoDeVisita();
			}
		return (int) Math.round(costoTotal * (1 - PORCENTAJE_DESCUENTO / 100));
	}

}