package controller.attractions;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.ItinerarioService;
import services.UsuarioService;
import sugeribles.Sugerible;

@WebServlet("/usuario/listItinerario.do")
public class ListItinerarioServlet extends HttpServlet {

	private static final long serialVersionUID = -8346640902238722429L;
	private ItinerarioService itinerarioService;
	private UsuarioService usuarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.itinerarioService = new ItinerarioService();
		this.usuarioService = new UsuarioService();
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Integer id = Integer.parseInt(req.getParameter("id"));
		
		
		itinerarioService.cargarItinerario(usuarioService.find(id));
			
		List<Sugerible> itinerario = itinerarioService.getItinerario();
		
		req.setAttribute("usuario",usuarioService.find(id));
		req.setAttribute("itinerario", itinerario);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/usuario/listItinerario.jsp");
		dispatcher.forward(req, resp);
	}
}