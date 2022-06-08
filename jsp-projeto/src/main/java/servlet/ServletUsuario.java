package servlet;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import model.Login;

@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	DAOUsuarioRepository usuarioDao = new DAOUsuarioRepository();
	
	// CONTRUTOR
    public ServletUsuario() {
    }
    
    // GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String acao = request.getParameter("acao");
		
		// DELETAR
		if(!acao.isEmpty() && acao != null && acao.equalsIgnoreCase("deletar")) {
			String id = request.getParameter("id");
			
			try {
				// DELETAR
				usuarioDao.deletarUsuario(Long.parseLong(id));
				response.getWriter().write("Deletado com sucesso!!");
				
			} catch (Exception e) {// erro deletar
				
				response.getWriter().write("Erro ao deletar!!");
				e.printStackTrace();
				
			}
		
		
		// BUSCAR USUARIO
		}else if(!acao.isEmpty() && acao != null && acao.equalsIgnoreCase("buscarUsuario")) {
			
			
			String buscarNome = request.getParameter("buscarNome");
			
			try {
				
				List<Login> usuarios = usuarioDao.getUsuariosBusca(buscarNome);
				
				ObjectMapper mapper = new ObjectMapper();
				
 				String json = mapper.writeValueAsString(usuarios);
				
				response.getWriter().write(json);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		 
			
			
		}else if(!acao.isEmpty() && acao != null && acao.equalsIgnoreCase("verEditar")){
			
			 
			
		}else{
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		}
		
		
		
	}
	
	// POST
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
