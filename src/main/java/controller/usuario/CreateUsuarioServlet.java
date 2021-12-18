package controller.usuario;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import services.UsuarioService;

@WebServlet("/usuario/create.do")
public class CreateUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = -8444977424705041386L;
	private UsuarioService usuarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.usuarioService = new UsuarioService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/usuario/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = -1; //

		String nombre = req.getParameter("nombre");
		String preferida = req.getParameter("preferida");
		int presupuesto = Integer.parseInt(req.getParameter("presupuesto"));
		double tiempoDisponible = Double.parseDouble(req.getParameter("tiempoDisponible"));

		String admin = req.getParameter("admin");

		Boolean isAdmin = convertToBoolean(admin);

		String password = req.getParameter("password");
		
		Usuario usuario = usuarioService.create(id, nombre, preferida, presupuesto, tiempoDisponible, password,
				isAdmin);
		if (usuario.isValid()) {
			resp.sendRedirect("/Tp003-TurismoEnLaTierraMedia/usuario/index.do");
		} else {
			req.setAttribute("usuario", usuario);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/usuario/create.jsp");
			dispatcher.forward(req, resp);
		}
	}

	private boolean convertToBoolean(String value) {
		boolean returnValue = false;
		if ("on".equalsIgnoreCase(value)) {
			returnValue = true;
		}
		return returnValue;
	}

}