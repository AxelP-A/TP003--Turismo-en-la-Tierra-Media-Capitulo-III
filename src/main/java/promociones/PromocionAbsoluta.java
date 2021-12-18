package promociones;

import java.util.List;

import model.Atraccion;
import model.Promocion;

public class PromocionAbsoluta extends Promocion {

	private int monedas;

	public PromocionAbsoluta(int id, String nombre, List<Atraccion> listaAtracciones, int monedas, String descripcion, String fechaBaja) {
		super(id ,nombre, listaAtracciones, descripcion, fechaBaja);
		this.monedas = monedas;
	}
	
	public PromocionAbsoluta(int id) {
		super(id, null, null, null, null);
	}
	
	/** En este metodo, tenemos 2 ideas distintas sobre como deberia funcionar, por lo tanto, dejamos ambos escritos, uno funcionando actualmente, y el otro comentado.
	 *  Por un lado, entendemos que las promociones absolutas obtienen un descuento a partir de una cantidad de monedas que ingresa desde la db(caso funcionando actualmente).
	 *  Por otro lado, interpretamos que tambien puede significar que el costo final va a ser el de las monedas que ingresen desde el archivo (seria el caso del return MONEDAS).
	 */
	
	@Override
	public int getCostoDeVisita() {
		
		int costoTotal = 0;
		
		for (int i = 0; i < super.atraccionesIncluidas.size(); i++) {
			costoTotal += atraccionesIncluidas.get(i).getCostoDeVisita();
		}
		return costoTotal - monedas;
		// return MONEDAS;
	}
	
	@Override
	public void setCalculoDeCosto(double nuevoCalculo) {
		this.monedas = (int) nuevoCalculo;
	}
	
	@Override
	public int getCalculoDeCosto() {
		return monedas;
	}
	
	@Override
	public String getTipoProm() {
		return "ABSOLUTA";
	}
	
	
	
	
	
	
	
}