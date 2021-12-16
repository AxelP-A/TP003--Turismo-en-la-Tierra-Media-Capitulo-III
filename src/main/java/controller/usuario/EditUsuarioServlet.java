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

@WebServlet("/usuario/edit.do")
public class EditUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = -9196254029172194009L;
	private UsuarioService usuarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.usuarioService = new UsuarioService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		Usuario usuario = usuarioService.find(id);
		req.setAttribute("usuario", usuario);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/usuario/edit.jsp");
		dispatcher.forward(req, resp);
	}        

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Integer id = Integer.parseInt(req.getParameter("id"));
		String nombre = req.getParameter("nombre");
		String preferida = req.getParameter("preferencia");
		int presupuesto = Integer.parseInt(req.getParameter("presupuesto"));
		double tiempoDisponible = Double.parseDouble(req.getParameter("tiempoDisponible"));
		String password = req.getParameter("password");
		
		Usuario usuario = usuarioService.updateUsuario(id, nombre, preferida, presupuesto, tiempoDisponible, password);
		
		if (usuario.isValid()) {
			resp.sendRedirect("/Tp003-TurismoEnLaTierraMedia/usuario/index.do");
		} else {
			req.setAttribute("usuario", usuario);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/usuario/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}