package controller.promociones;

import java.io.IOException;
import java.util.Map;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import persistence.commons.DAOFactory;
import services.BuyPromocionService;

@WebServlet("/promocion/buy.do")
public class BuyPromocionServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private BuyPromocionService buyPromocionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.buyPromocionService = new BuyPromocionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer promocionId = Integer.parseInt(req.getParameter("id"));	
			
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		Map<String, String> errors = buyPromocionService.buy(user.getId(), promocionId);
		
		Usuario usuario = DAOFactory.getUsuarioDAO().findByUserId(user.getId());
		req.getSession().setAttribute("user", usuario);
		
		if (errors.isEmpty()) {
			req.setAttribute("flash", "¡Gracias por su compra!");
			
		} else {
			req.setAttribute("errors", errors);
			req.setAttribute("flash", "No ha podido realizarse la compra");
			
		}
		
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/atraccion/index.do");
		dispatcher.forward(req, resp);
	}
}