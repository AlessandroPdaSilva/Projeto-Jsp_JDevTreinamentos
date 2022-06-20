package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOUsuarioRepository;
import model.Login;

@WebServlet("/principal/ServletTelefone")
public class ServletTelefone extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOUsuarioRepository usuDao = new DAOUsuarioRepository();

    public ServletTelefone() {
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String idUsuario = request.getParameter("idUsuario");
			
			if(idUsuario != null || idUsuario.isEmpty()) {
				
				Login usuario = usuDao.getUsuarioById(Long.parseLong(idUsuario));
				request.setAttribute("usuario", usuario);
				
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

	}

}
