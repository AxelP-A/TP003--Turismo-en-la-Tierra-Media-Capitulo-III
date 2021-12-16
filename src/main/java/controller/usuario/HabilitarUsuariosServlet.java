package controller.usuario;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.UsuarioService;

@WebServlet("/usuario/habilitar.do")
public class HabilitarUsuariosServlet extends HttpServlet {
	
	private static final long serialVersionUID = -5948538887801392265L;
	private UsuarioService usuarioService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.usuarioService= new UsuarioService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		usuarioService.habilitar(id);
		resp.sendRedirect("/Tp003-TurismoEnLaTierraMedia/usuario/index.do");
	}
}