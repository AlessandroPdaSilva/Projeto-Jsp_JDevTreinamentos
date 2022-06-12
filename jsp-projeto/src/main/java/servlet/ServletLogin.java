package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.Session;

import dao.DAOLoginRepository;
import dao.DAOUsuarioRepository;
import model.Login;

@WebServlet(urlPatterns = {"/principal/ServletLogin","/ServletLogin"})
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DAOLoginRepository loginDao = new DAOLoginRepository();
	DAOUsuarioRepository usuDao = new DAOUsuarioRepository();
	
	//CONTRUTOR
    public ServletLogin() {
        super();
    }
    
    //GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if(acao.equalsIgnoreCase("logout")) {
			
			request.getSession().invalidate();
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			redirecionar.forward(request, response);
			
		}else {
			doPost(request, response);
		}
		
		
	}
	
	//POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
	 
		 
		 if(login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {// validacao vazio
			
			 Login log = new Login();
			 log.setLogin(login);
			 log.setSenha(senha);
			 
				 try {
					
							 
							 
							 if(loginDao.autenticaLogin(log)) {//validacao usuario
								 
								 log = usuDao.getUsuarioByLogin(login);
								 
								 // sessao
								 request.getSession().setAttribute("login", log);
								  
							 
								 // pagina principal
								 RequestDispatcher r = request.getRequestDispatcher("/principal/principal.jsp");
								 r.forward(request, response);
								 
								 
							 }else {
								 // ERRO LOGIN OU SENHA
								 RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
								 request.setAttribute("msg", "Login ou senha esta incorreto");
								 redirecionar.forward(request, response);
							 }
					 
				 } catch (Exception e) {
					 
					 e.printStackTrace();
					 
					 // redirecionando para pagina de erro
					 RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
					 request.setAttribute("msg", e.getMessage());
					 redirecionar.forward(request, response);
					 
				 }
			 
			 
			 
		 }else {
			 // ERRO VAZIO
			 RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			 request.setAttribute("msg", "Login ou senha esta vazio");
			 redirecionar.forward(request, response);
		 }
		
		 
	}

}
