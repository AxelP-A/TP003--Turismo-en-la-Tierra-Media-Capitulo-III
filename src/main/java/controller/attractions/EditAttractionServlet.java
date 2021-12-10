package controller.attractions;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import services.AttractionService;

@WebServlet("/atraccion/edit.do")
public class EditAttractionServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private AttractionService attractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		Atraccion atraccion = attractionService.find(id);
		req.setAttribute("atraccion", atraccion);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/atraccion/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		int cost = Integer.parseInt(req.getParameter("cost"));
		// Integer cost = req.getParameter("cost").trim() == "" ? null : Integer.parseInt(req.getParameter("cost"));
		double duration = Double.parseDouble(req.getParameter("duration"));
		int capacity = Integer.parseInt(req.getParameter("capacity"));
		
		String tipo = "TEST"; // CREAR CAMPO PARA PARSEAR.

		Atraccion attraction = attractionService.updateAtraccion(id, name, cost, duration, capacity, tipo);

		if (attraction.isValid()) {
			resp.sendRedirect("/Tp003-TurismoEnLaTierraMedia/atraccion/index.do");
		} else {
			req.setAttribute("atraccion", attraction);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/atraccion/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}