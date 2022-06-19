package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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

import connect.SingleConnection;
import model.Login;

@WebFilter(urlPatterns = {"/principal/*"})
public class filterAutenticacao implements Filter {

    private static Connection conexao;

    public filterAutenticacao() {

    }

    // DESTROI quando servidor e fechado
	@Override
	public void destroy() {
		 try {
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// EXECUTA durante todo periodo
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException{

		try {

			HttpServletRequest req = (HttpServletRequest) request;// request servlet
			HttpSession sessao = req.getSession();// sessao

			Login usuario = (Login) sessao.getAttribute("login");


			if(usuario == null) {// usuario nao existe

				RequestDispatcher redireciona = req.getRequestDispatcher("/index.jsp");
				req.setAttribute("msg", "Por favor fassa o login (filter te pegou)");
				redireciona.forward(request, response);
				return;
			}else {
				chain.doFilter(request, response);
			}


			conexao.commit();


		} catch (Exception e) {


			try {
				conexao.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();

			 // redirecionando para pagina de erro
			 request.setAttribute("msg", e.getMessage());
			 RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			 redirecionar.forward(request, response);

		}



	}

	// INICIA quando servidor e iniciado
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		 conexao = SingleConnection.getConexao();
	}

}
