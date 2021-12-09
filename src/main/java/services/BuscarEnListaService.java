package services;

import java.util.List;

import model.Atraccion;

public class BuscarEnListaService {

	public boolean noCompro(Atraccion atraccion, List<Atraccion> listaAtraccion) {

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
