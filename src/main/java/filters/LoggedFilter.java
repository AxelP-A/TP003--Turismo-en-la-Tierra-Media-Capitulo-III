package filters;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import model.Usuario;

@WebFilter(urlPatterns = "*.do")
public class LoggedFilter  implements Filter  {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        Usuario usuario = (Usuario) ((HttpServletRequest) request).getSession().getAttribute("user");
        if (usuario != null) {
            chain.doFilter(request, response);
        } else {
            request.setAttribute("flash", "Por favor, ingresa al sistema");
            //((HttpServletRequest) response).sendRedirect("/Tp003-TurismoEnLaTierraMedia/"); 

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }
	
	
	
	/*
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain); {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession s = req.getSession();

        Usuario user = (Usuario) s.getAttribute("user");

        if(user != null && user.isAdmin()) {
            //login
            chain.doFilter(request, response);
        } else {
            System.out.println("NOT logged in. Please log in to see this page");
            response.setContentType("text/html");
            response.getWriter().println("<h1>NOT logged...</h1>");
        }
    }

    @Override
    public void destroy() {
    }
    */

}
