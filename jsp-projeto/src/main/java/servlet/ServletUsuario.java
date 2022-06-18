package servlet;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import model.Login;

@MultipartConfig
@WebServlet(urlPatterns =  {"/ServletUsuario"})
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
				//
				List<Login> listaUsu = usuarioDao.getUsuarios();
				request.setAttribute("listaUsuario", listaUsu);
				
				usuarioDao.deletarUsuario(Long.parseLong(id));
				response.getWriter().write("Deletado com sucesso!!");
				
			} catch (Exception e) {// erro
				
				response.getWriter().write("Erro ao deletar!!");
				e.printStackTrace();
				
			}
		
		
		// BUSCAR USUARIO
		}else if(!acao.isEmpty() && acao != null && acao.equalsIgnoreCase("buscarUsuario")) {
			
			
			String buscarNome = request.getParameter("buscarNome");
			
			try {
				//
				List<Login> listaUsu = usuarioDao.getUsuarios();
				request.setAttribute("listaUsuario", listaUsu);
				
				List<Login> usuarios = usuarioDao.getUsuariosBusca(buscarNome);
				
				ObjectMapper mapper = new ObjectMapper();
				
 				String json = mapper.writeValueAsString(usuarios);
				
				response.getWriter().write(json);
				
			} catch (Exception e) {//erro
				e.printStackTrace();
			}
		 
			
		// VER EDITAR
		}else if(!acao.isEmpty() && acao != null && acao.equalsIgnoreCase("verEditar")){
			
			String id = request.getParameter("id");
			 
			try {
				Login log = usuarioDao.getUsuarioById(id);
				//
				List<Login> listaUsu = usuarioDao.getUsuarios();
				request.setAttribute("listaUsuario", listaUsu);
				
				request.setAttribute("usuario", log);
				request.setAttribute("msg", "Usuario em edição !! ");
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
				redirecionar.forward(request, response); 
				
			} catch (Exception e) {//erro
				e.printStackTrace();
			}
			
		// LISTAR USUARIO
		}else if(!acao.isEmpty() && acao != null && acao.equalsIgnoreCase("listarUsuario")){
			
			try {
				//
				List<Login> listaUsu = usuarioDao.getUsuarios();
				
				request.setAttribute("listaUsuario", listaUsu);
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
				redirecionar.forward(request, response);
				
			} catch (Exception e) {//erro
				e.printStackTrace();
			}
			
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
		String perfil = request.getParameter("perfil");
		
		String cep = request.getParameter("cep");
		String logradouro = request.getParameter("logradouro");
		String bairro = request.getParameter("bairro");
		String localidade = request.getParameter("localidade");
		String uf = request.getParameter("uf");
		
		Login log = new Login();
		log.setId( id != null && !id.isEmpty() ? Long.parseLong(id) : null);
		log.setNome(nome);
		log.setEmail(email);
		log.setLogin(login);
		log.setSenha(senha);
		log.setPerfil(perfil != null && !perfil.isEmpty() ? perfil : null);
		log.setCep(cep);
		log.setLocalidade(localidade);
		log.setBairro(bairro);
		log.setLogradouro(logradouro);
		log.setUf(uf);
		
		// imagem de perfil
		if(ServletFileUpload.isMultipartContent(request)) {
			
			Part arquivoEnviado = request.getPart("filefoto");// pega foto da tela
			
			if(arquivoEnviado.getSize() > 0) {
				byte[] foto = IOUtils.toByteArray(arquivoEnviado.getInputStream());// converte arquivo para byte
				String extensao = arquivoEnviado.getContentType().split("\\/")[1];
				String fotoEmBase64 = "data:image/" + extensao + ";base64," + new Base64().encodeBase64String(foto);
				 
				 
				log.setFotoBase64(fotoEmBase64);
				log.setExtensaoFoto(extensao);
			}
			
			 
			
			 
		}
		
		try {
			
			// SALVAR
			log = usuarioDao.salvarUsuario(log);
			
			request.setAttribute("msg", "Salvo com Sucesso!!");
			request.setAttribute("usuario", log);
			
			//
			List<Login> listaUsu = usuarioDao.getUsuarios();
			request.setAttribute("listaUsuario", listaUsu);
			
			RequestDispatcher pagina = request.getRequestDispatcher("principal/usuario.jsp");
			
			pagina.forward(request, response);
			
			
			
		}catch(SQLIntegrityConstraintViolationException e1){// --usuario ja existe (salvar)
			
				// EDITAR
				try {
					
					log = usuarioDao.editarUsuario(log);
					
					request.setAttribute("usuario", log);
					request.setAttribute("msg", "Editado com sucesso !! ");
					
					//
					List<Login> listaUsu = usuarioDao.getUsuarios();
					request.setAttribute("listaUsuario", listaUsu);
					
					RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
					redirecionar.forward(request, response); 
					
				}catch (SQLIntegrityConstraintViolationException e3) {// --usuario ja existe (editar)
					
					e3.printStackTrace();
					request.setAttribute("msg", "Usuario ja existe, tente outro!!");
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
