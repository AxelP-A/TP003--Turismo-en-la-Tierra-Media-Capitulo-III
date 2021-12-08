package controller.attractions;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.Usuario;
import services.AttractionService;
import services.ItinerarioService;

@WebServlet("/atraccion/index.do")
public class ListAttractionsServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8346640902238722429L;
	private AttractionService attractionService;
	
	private ItinerarioService itinerarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
		this.itinerarioService = new ItinerarioService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Atraccion> atraccion = attractionService.list();
		List<Atraccion> elegidas = itinerarioService.crearListaAtraccionesAceptadas((Usuario) req.getSession().getAttribute("user"));
		
		req.setAttribute("atraccion", atraccion);
		
		req.setAttribute("atraccionesAceptadas", elegidas);
		
		
		System.out.println((Usuario) req.getSession().getAttribute("user"));
		System.out.println("---------------------------");
		System.out.println("---------------------------");
		System.out.println(atraccion);
		System.out.println("---------------------------");
		System.out.println(elegidas);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/atraccion/index.jsp");
		dispatcher.forward(req, resp);
	}
	
	
}