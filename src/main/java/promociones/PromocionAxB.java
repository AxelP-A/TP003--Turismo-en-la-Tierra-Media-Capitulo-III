package promociones;

import java.util.List;

import model.Atraccion;
import model.Promocion;

public class PromocionAxB extends Promocion {

	public PromocionAxB(int id, String nombre, List<Atraccion> arrayAtracciones, String descripcion, String fechaBaja) {
		super(id, nombre, arrayAtracciones, descripcion, fechaBaja);
	}

	@Override
	public int getCostoDeVisita() {

		int costoTotal = 0;

		for (int i = 0; i < super.atraccionesIncluidas.size() - 1; i++) {
			costoTotal += atraccionesIncluidas.get(i).getCostoDeVisita();
		}
		return costoTotal;
	}

	@Override
	public void setCalculoDeCosto(double nuevoCalculo) {
	}

	@Override
	public int getCalculoDeCosto() {
		return 0;
	}
}