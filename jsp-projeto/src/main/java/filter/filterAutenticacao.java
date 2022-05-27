package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
@WebFilter(urlPatterns = {"/principal/*"})
public class filterAutenticacao implements Filter {

    
    public filterAutenticacao() {
        
    }
    
    // DESTROI quando servidor e fechado
	public void destroy() {
		 
	}
 
	// EXECUTA durante todo periodo 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 
		HttpServletRequest req = (HttpServletRequest) request;// request servlet
		HttpSession sessao = req.getSession();// sessao
		
		String usuario = (String) sessao.getAttribute("usuario");
		
		
		if(usuario == null || usuario.isEmpty()) {// usuario nao existe
			
			RequestDispatcher redireciona = req.getRequestDispatcher("/index.jsp");
			req.setAttribute("msgErro", "Por favor fassa o login (filter te pegou)");
			redireciona.forward(request, response);
			return;
		}else {
			chain.doFilter(request, response);
		}
		
		
		
	}
 
	// INICIA quando servidor e iniciado
	public void init(FilterConfig fConfig) throws ServletException {
		 
	}

}
