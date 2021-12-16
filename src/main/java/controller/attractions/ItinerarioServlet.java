package controller.attractions;

	import java.io.IOException;
	import java.util.List;
	import jakarta.servlet.RequestDispatcher;
	import jakarta.servlet.ServletException;
	import jakarta.servlet.annotation.WebServlet;
	import jakarta.servlet.http.HttpServlet;
	import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;
	import model.Usuario;
	import services.ItinerarioService;
	import sugeribles.Sugerible;

	@WebServlet("/itinerario/index.do")
	public class ItinerarioServlet extends HttpServlet {
	
		private static final long serialVersionUID = -8346640902238722429L;
		private ItinerarioService itinerarioService;

		@Override
		public void init() throws ServletException {
			super.init();
			this.itinerarioService = new ItinerarioService();
			
		}

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			itinerarioService.cargarItinerario((Usuario) req.getSession().getAttribute("user"));
			
			//itinerarioService.cargarItinerario(usuario del id que seleccionamos);
			
			List<Sugerible> itinerario = itinerarioService.getItinerario();
			
			
			
			
			//Necesitamos una segunda carga del itinerario con el id del usuario que estemos seleccionando).
			
			
			
			req.setAttribute("itinerario", itinerario);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/itinerario/index.jsp");
			dispatcher.forward(req, resp);
		}
	}