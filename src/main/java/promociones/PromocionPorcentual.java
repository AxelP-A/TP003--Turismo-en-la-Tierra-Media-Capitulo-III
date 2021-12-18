package promociones;

import java.util.List;

import model.Atraccion;
import model.Promocion;

public class PromocionPorcentual extends Promocion {

	private double porcentajeDescuento;

	public PromocionPorcentual(int id, String nombre, List<Atraccion> listaAtracciones, double porcentaje, String descripcion, String fechaBaja) {
		super(id, nombre, listaAtracciones, descripcion, fechaBaja);
		this.porcentajeDescuento = porcentaje;
	}

	/**
	 * Como el porcentaje estaba definido como int, no estaba realizando el calculo
	 * correctamente al dividir por 100. 
	 */
	@Override
	public int getCostoDeVisita() {

		double costoTotal = 0;

		for (int i = 0; i < super.atraccionesIncluidas.size(); i++) {
				costoTotal += atraccionesIncluidas.get(i).getCostoDeVisita();
			}
		return (int) Math.round(costoTotal * (1 - porcentajeDescuento / 100));
	}
	
	@Override
	public void setCalculoDeCosto(double nuevoCalculo) {
		this.porcentajeDescuento = nuevoCalculo;
	}
	
	@Override
	public int getCalculoDeCosto() {
		return (int) porcentajeDescuento;
	}
}