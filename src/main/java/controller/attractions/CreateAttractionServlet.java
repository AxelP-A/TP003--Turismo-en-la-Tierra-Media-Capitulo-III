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

@WebServlet("/atraccion/create.do")
public class CreateAttractionServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private AttractionService attractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/atraccion/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = -1; // crear otro constructor sin id?
		
		String nombre = req.getParameter("name");
		int costo = Integer.parseInt(req.getParameter("cost"));
		Double duracion = Double.parseDouble(req.getParameter("duration"));
		int cupo = Integer.parseInt(req.getParameter("capacity"));
		String tipo = req.getParameter("tipo");

		Atraccion atraccion = attractionService.create(id, nombre, costo, duracion, cupo, tipo);
		if (atraccion.isValid()) {
			resp.sendRedirect("/Tp003-TurismoEnLaTierraMedia/atraccion/index.do");
		} else {
			req.setAttribute("atraccion", atraccion);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/atraccion/create.jsp");
			dispatcher.forward(req, resp);
		}
	}
}