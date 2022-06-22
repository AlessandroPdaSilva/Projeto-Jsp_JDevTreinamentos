package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOTelefoneRepository;
import dao.DAOUsuarioRepository;
import model.Login;
import model.Telefone;

@WebServlet("/principal/ServletTelefone")
public class ServletTelefone extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DAOUsuarioRepository usuDao = new DAOUsuarioRepository();
	DAOTelefoneRepository telDao = new DAOTelefoneRepository();

    public ServletTelefone() {
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {// LISTAGEM TELEFONE
			
			String idUsuario = request.getParameter("idUsuario");
			String acao = request.getParameter("acao");
			
			if(acao != null && acao.equals("deletarTelefone")) {
				
				String idFone = request.getParameter("idTelefone");
				telDao.deletarTelefone(Long.parseLong(idFone));
				request.setAttribute("msg", "Excluido com sucesso!!");
			}
			
			if(idUsuario != null || idUsuario.isEmpty()) {
				
				Login usuario = usuDao.getUsuarioById(Long.parseLong(idUsuario));
				request.setAttribute("usuario", usuario);
				
				
				List<Telefone> listaTel = telDao.getTelefonesUsuario(usuario.getId());
				request.setAttribute("listaTelefone", listaTel);
				
				RequestDispatcher redirecionar = request.getRequestDispatcher("/principal/telefone.jsp");
				redirecionar.forward(request, response);
				
			}else {
				//
				List<Login> listaUsu = usuDao.getUsuarios();
				request.setAttribute("listaUsuario", listaUsu);
				request.setAttribute("totalPagina", usuDao.totalPagina());

				RequestDispatcher redirecionar = request.getRequestDispatcher("/principal/usuario.jsp");
				redirecionar.forward(request, response);
			}
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// SALVAR TELEFONE
			long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
			String numero = request.getParameter("numero");
			
			Telefone tel = new Telefone();
			tel.setNumero(numero);
			tel.setUsuario(usuDao.getUsuarioById(idUsuario));
			
			telDao.salvarTelefone(tel);
			
			
			//
			Login usuario = usuDao.getUsuarioById(idUsuario);
			request.setAttribute("usuario", usuario);
			List<Telefone> listaTel = telDao.getTelefonesUsuario(usuario.getId());
			request.setAttribute("listaTelefone", listaTel);
			
			
			
			request.setAttribute("msg", "Telefone salvo com sucesso!!");
			RequestDispatcher redirecionar = request.getRequestDispatcher("/principal/telefone.jsp");
			redirecionar.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("msg", "Erro ao salvar !!");
			RequestDispatcher redirecionar = request.getRequestDispatcher("/principal/telefone.jsp");
			redirecionar.forward(request, response);
		}
		
		
		
	}

}
