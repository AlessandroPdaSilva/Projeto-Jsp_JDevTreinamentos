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
		
		
		try {
			
			// SALVAR
			log = usuarioDao.salvarUsuario(log);
			
			request.setAttribute("msg", "Salvo com Sucesso!!");
			request.setAttribute("usuario", log);
			
			RequestDispatcher pagina = request.getRequestDispatcher("principal/usuario.jsp");
			
			pagina.forward(request, response);
			
			
			
		}catch(SQLIntegrityConstraintViolationException e1){// --usuario ja existe
			
				// EDITAR
				try {
					
					log = usuarioDao.editarUsuario(log);
					
					request.setAttribute("usuario", log);
					request.setAttribute("msg", "Editado com sucesso !! ");
					RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
					redirecionar.forward(request, response); 
					
				} catch (Exception e2) {// erro editar
					
					e2.printStackTrace();
					request.setAttribute("msg", "Erro ao editar !!");
					RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
					redirecionar.forward(request, response); 
				}
			
			//e1.printStackTrace();
			
			
			
		}catch (Exception e) {// erro salvar
			e.printStackTrace();
			request.setAttribute("msg", "Erro ao Salvar: "+e.getMessage());
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
			redirecionar.forward(request, response); 
		} 
		
		
		
	}

}
