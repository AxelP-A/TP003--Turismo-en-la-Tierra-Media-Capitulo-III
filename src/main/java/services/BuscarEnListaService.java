package services;

import java.util.List;

import model.Atraccion;
import model.Promocion;
import sugeribles.Sugerible;

public class BuscarEnListaService {

	public boolean noCompro(Sugerible sugerible, List<Atraccion> listaAtraccion) {

		if (listaAtraccion == null) {
			return true;
		}

		if (sugerible.esPromocion()) {
			((Promocion) sugerible).getArrayAtracciones();
			for (int i = 0; i < ((Promocion) sugerible).getArrayAtracciones().size(); i++) {
				if (!noExisteEnLista(((Promocion) sugerible).getArrayAtracciones().get(i), listaAtraccion))
					return false;
			}
			return true;
		}
		return noExisteEnLista(((Atraccion) sugerible), listaAtraccion);
	}


	private boolean noExisteEnLista(Atraccion atraccion, List<Atraccion> listaAtraccion) {

		if (listaAtraccion == null) {
			return true;
		}
		for (Atraccion Atr : listaAtraccion) {
			if (Atr.getId() == atraccion.getId()) {
				return false;
			}
		}
		return true;
	}

}
