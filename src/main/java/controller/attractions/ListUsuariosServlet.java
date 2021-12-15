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
import model.Usuario;
import services.UsuarioService;

@WebServlet("/usuario/index.do")
public class ListUsuariosServlet extends HttpServlet implements Servlet{

	private static final long serialVersionUID = -6193051202425224206L;
	
	private UsuarioService usuarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.usuarioService = new UsuarioService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		List<Usuario> usuario = usuarioService.list();
		req.setAttribute("listaUsuarios", usuario);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/usuario/index.jsp");
		dispatcher.forward(req, resp);
	}
}