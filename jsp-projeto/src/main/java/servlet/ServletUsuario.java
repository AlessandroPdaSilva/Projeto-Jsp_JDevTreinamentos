package servlet;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOUsuarioRepository;
import model.Login;

@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	DAOUsuarioRepository usuarioDao = new DAOUsuarioRepository();
	
    public ServletUsuario() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String id = request.getParameter("id");
			String email = request.getParameter("email");
			String nome = request.getParameter("nome");
			String login = request.getParameter("login");
			String senha= request.getParameter("senha");
			
			Login log = new Login();
			log.setId( id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			log.setNome(nome);
			log.setEmail(email);
			log.setLogin(login);
			log.setSenha(senha);
			
			usuarioDao.salvarUsuario(log);
			request.setAttribute("msgErro", "Salvo com Sucesso!!");
			 
			
			request.setAttribute("usuario", log);
			RequestDispatcher pagina = request.getRequestDispatcher("principal/usuario.jsp");
			
			pagina.forward(request, response);
			
			
			
		}catch(SQLIntegrityConstraintViolationException e1){
			e1.printStackTrace();
			request.setAttribute("msgErro", "Login ja Existe, por favor crie outro usuario ");
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
			redirecionar.forward(request, response); 
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msgErro", "Erro ao Salvar: "+e.getMessage());
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
			redirecionar.forward(request, response); 
		} 
		
		
		
	}

}
