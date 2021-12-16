package controller.promociones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.Promocion;
import services.AttractionService;
import services.PromocionService;

@WebServlet("/promocion/index.do")
public class ListPromocionServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8346640902238722429L;
	private AttractionService attractionService;
	private PromocionService promocionService;
	//private ItinerarioService itinerarioService;
	//private CargadorDeSugeriblesService cargadorDeSugeribles;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
		this.promocionService = new PromocionService();
		//this.itinerarioService = new ItinerarioService();
		//this.cargadorDeSugeribles = new CargadorDeSugeriblesService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Atraccion> atracciones = attractionService.list();
		List<Promocion> promociones = promocionService.list(atracciones);
		
		//List<Atraccion> elegidas = itinerarioService.crearListaAtraccionesAceptadas((Usuario) req.getSession().getAttribute("user"));	
		
		//List<Sugerible> sugeribles = new ArrayList<Sugerible>(atraccion);
		//List<Sugerible> convertirPromos = new ArrayList<Sugerible>(promocion);
		//sugeribles.addAll(convertirPromos);
		
		//cargadorDeSugeribles.listaSugeribles((Usuario) req.getSession().getAttribute("user"), sugeribles);
		
		req.setAttribute("promociones", promociones);
				
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promocion/index.jsp");
		dispatcher.forward(req, resp);
	}
}