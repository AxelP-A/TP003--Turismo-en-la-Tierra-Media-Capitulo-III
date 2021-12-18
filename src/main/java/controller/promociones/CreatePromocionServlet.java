package controller.promociones;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.Promocion;
import services.AttractionService;
import services.PromocionService;

@WebServlet("/promocion/create.do")
public class CreatePromocionServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private PromocionService promocionService;
	private AttractionService attractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promocionService = new PromocionService();
		this.attractionService = new AttractionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Atraccion> atracciones =  attractionService.list();
		req.setAttribute("atracciones", atracciones);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promocion/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = -1;
		
		String nombre = req.getParameter("name");
		int costo = Integer.parseInt(req.getParameter("cost"));
		String tipo = req.getParameter("sellist1");
		String descripcion = req.getParameter("descripcion");
		String [] atraccionesIncluidas = req.getParameterValues("sellist2");
		String fechaBaja = null;

		Promocion promocion = promocionService.create(id, nombre, costo, tipo, atraccionesIncluidas, descripcion, fechaBaja);
		if (promocion.isValid()) {
			resp.sendRedirect("/Tp003-TurismoEnLaTierraMedia/atraccion/index.do");
		} else {
			req.setAttribute("promocion", promocion);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/promocion/create.jsp");
			dispatcher.forward(req, resp);
		}
	}
}