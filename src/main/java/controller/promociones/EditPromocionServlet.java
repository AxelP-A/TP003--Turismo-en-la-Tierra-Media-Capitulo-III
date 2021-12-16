package controller.promociones;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Promocion;
import services.AttractionService;
import services.PromocionService;

@WebServlet("/promocion/edit.do")
public class EditPromocionServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
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
		Integer id = Integer.parseInt(req.getParameter("id"));

		Promocion promocion = promocionService.find(id, attractionService.list());
		req.setAttribute("promocion", promocion);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promocion/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		Integer cost = Integer.parseInt(req.getParameter("cost"));
		// Integer cost = req.getParameter("cost").trim() == "" ? null :
		// Integer.parseInt(req.getParameter("cost"));
		String atraccionesIncluidas = req.getParameter("atracciones");
		String descripcion = req.getParameter("descripcion");

		Promocion promocion = promocionService.update(id.intValue(), name, cost.intValue(), descripcion,
				atraccionesIncluidas);

		if (promocion.isValid()) {
			resp.sendRedirect("/Tp003-TurismoEnLaTierraMedia/promocion/index.do");
		} else {
			req.setAttribute("promocion", promocion);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promocion/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}