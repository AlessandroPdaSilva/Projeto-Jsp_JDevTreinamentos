package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOLoginRepository;
import model.Login;

@WebServlet(urlPatterns = {"/principal/ServletLogin","/ServletLogin"})
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//CONTRUTOR
    public ServletLogin() {
        super();
    }
    
    //GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doPost(request, response);
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
					
							 DAOLoginRepository loginDao = new DAOLoginRepository();
							 
							 if(loginDao.autenticaLogin(log)) {//validacao usuario
							 
								 // sessao
								 request.getSession().setAttribute("usuario", log.getLogin());
							 
								 // pagina principal
								 RequestDispatcher r = request.getRequestDispatcher("/principal/principal.jsp");
								 r.forward(request, response);
								 
								 
							 }else {
								 // ERRO LOGIN OU SENHA
								 RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
								 request.setAttribute("msgErro", "Login ou senha esta incorreto");
								 redirecionar.forward(request, response);
							 }
					 
				 } catch (Exception e) {
					 
					 e.printStackTrace();
					 
					 // redirecionando para pagina de erro
					 RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
					 request.setAttribute("msgErro", e.getMessage());
					 redirecionar.forward(request, response);
					 
				 }
			 
			 
			 
		 }else {
			 // ERRO VAZIO
			 RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			 request.setAttribute("msgErro", "Login ou senha esta vazio");
			 redirecionar.forward(request, response);
		 }
		
		 
	}

}
