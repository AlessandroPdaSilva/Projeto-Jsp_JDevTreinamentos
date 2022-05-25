package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Login;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//CONTRUTOR
    public ServletLogin() {
        super();
    }
    
    //GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		 
		 if(login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
			
			 Login log = new Login();
			 log.setLogin(login);
			 log.setSenha(senha);
			 
			 if( log.getLogin().equalsIgnoreCase("adm") && 
					 log.getSenha().equalsIgnoreCase("adm")) {
			 
				 // sessao
				 request.getSession().setAttribute("usuario", log.getLogin());
				 
				 RequestDispatcher r = request.getRequestDispatcher("principal/principal.jsp");
				 r.forward(request, response);
				 
				 
			 }else {
				 // ERRO LOGIN OU SENHA
				 RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
				 request.setAttribute("msgErro", "Login ou senha esta incorreto");
				 redirecionar.forward(request, response);
			 }
			 
		 }else {
			 // ERRO VAZIO
			 RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			 request.setAttribute("msgErro", "Login ou senha esta vazio");
			 redirecionar.forward(request, response);
		 }
		
		 
	}
	
	//POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
