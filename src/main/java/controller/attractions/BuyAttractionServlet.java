package controller.attractions;

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
import services.BuyAttractionService;

@WebServlet("/atraccion/buy.do")
public class BuyAttractionServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private BuyAttractionService buyAttractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.buyAttractionService = new BuyAttractionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer attractionId = Integer.parseInt(req.getParameter("id"));
	
		String esPromo = req.getParameter("ep");
		
		
		
		
		
			
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		Map<String, String> errors = buyAttractionService.buy(user.getId(), attractionId);
		
		Usuario usuario = DAOFactory.getUsuarioDAO().findByUserId(user.getId());
		req.getSession().setAttribute("user", usuario);
		
		if (errors.isEmpty()) {
			req.setAttribute("flash", "¡Gracias por comprar!");
		} else {
			req.setAttribute("errors", errors);
			req.setAttribute("flash", "No ha podido realizarse la compra");
		}
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/atraccion/index.do");
		dispatcher.forward(req, resp);
	}
}