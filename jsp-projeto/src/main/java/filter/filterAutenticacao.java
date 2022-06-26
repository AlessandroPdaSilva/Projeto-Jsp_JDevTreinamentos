package filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

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
import dao.DAOVersionadorBanco;
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
		// CONEXAO
		conexao = SingleConnection.getConexao();
		 
		 // VERSIONADOR DO BANCO
		 DAOVersionadorBanco versionadorDao = new DAOVersionadorBanco();
		 
		 String caminhoPastaSql = fConfig.getServletContext().getRealPath("versionadobancosql") + File.separator;
		 
		 File[] arquivosSql = new File(caminhoPastaSql).listFiles(); 
		 
		 try {
			
			 for(File f: arquivosSql) {
				 
				 boolean arquivoRodado = versionadorDao.arquivoSqlRodado(f.getName());
				 
				 if(!arquivoRodado) {
					 versionadorDao.gravaArquivo(f.getName());
					 FileInputStream entrada = new FileInputStream(f);
					 
					 Scanner scan = new Scanner(entrada,"UTF-8");
					 String sql = "";
					 
					 while(scan.hasNext()) {
						 sql += scan.nextLine();
						 sql += "\n";
					 }
					 
					 conexao.prepareStatement(sql).execute();
					 
					 conexao.commit();
					 scan.close();
				 }
				 
				 
			 }
		 
			 
		 } catch (Exception e) {
			 try {
				conexao.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			 
			 e.printStackTrace();
		 }
		 
	}

}
