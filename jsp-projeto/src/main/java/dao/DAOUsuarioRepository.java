package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.SingleConnection;
import model.Login;

public class DAOUsuarioRepository {
	
	private Connection conexao;
	
	// CONSTRUTOR
	public DAOUsuarioRepository(){
		conexao = SingleConnection.getConexao();
	}
	
	// SALVAR 
	public Login salvarUsuario(Login u) throws Exception {
		
		String sql = "INSERT INTO usuario (nome, email, login, senha) VALUES (?,?,?,?)";
		PreparedStatement salvar = conexao.prepareStatement(sql);
		salvar.setString(1, u.getNome());
		salvar.setString(2, u.getEmail());
		salvar.setString(3, u.getLogin());
		salvar.setString(4, u.getSenha());
		
		salvar.execute();
		
		
		conexao.commit();
		
		return this.getUsuarioByLogin(u.getLogin());
	}
	
	// UPDATE
	public Login editarUsuario(Login u) throws Exception {
		
		String sql = "UPDATE usuario SET nome=?, email=?, login=?, senha=? WHERE id= ?";
		PreparedStatement editar = conexao.prepareStatement(sql);
		editar.setString(1, u.getNome());
		editar.setString(2, u.getEmail());
		editar.setString(3, u.getLogin());
		editar.setString(4, u.getSenha());
		editar.setLong(5, u.getId());
		
		editar.execute();
		
		
		conexao.commit();
		
		return this.getUsuarioByLogin(u.getLogin());
	}
	
	// DELETAR
	public void deletarUsuario(Long id) throws Exception {
		
		String sql = "DELETE FROM usuario WHERE id = ?";
		PreparedStatement deleta = conexao.prepareStatement(sql);
		deleta.setLong(1, id);
		
		deleta.executeUpdate();
		
		conexao.commit();
		
	}
	
	
	// GET USUARIO BY LOGIN
	public Login getUsuarioByLogin(String login) throws Exception {
		
		String sql = "SELECT * FROM usuario WHERE login = ?";
		PreparedStatement prepara = conexao.prepareStatement(sql);
		prepara.setString(1, login);
		
		ResultSet result = prepara.executeQuery();
		
		Login u = new Login();
		
		while(result.next()) {
			u.setId(result.getLong("id"));
			u.setEmail(result.getString("email"));
			u.setNome(result.getString("nome"));
			u.setLogin(result.getString("login"));
			u.setSenha(result.getString("senha"));
		}
		
		conexao.commit();
		
		return u;
	}
	
	// GET BUSCA USUARIOS
		public List<Login> getUsuariosBusca(String login) throws Exception {
			
			String sql = "SELECT * FROM usuario WHERE nome LIKE ? ";
			PreparedStatement prepara = conexao.prepareStatement(sql);
			prepara.setString(1, "%"+login+"%");
			
			ResultSet result = prepara.executeQuery();
			
			List<Login> usuarios = new ArrayList<Login>();
			Login u = new Login();
			
			while(result.next()) {
				u.setId(result.getLong("id"));
				u.setEmail(result.getString("email"));
				u.setNome(result.getString("nome"));
				u.setLogin(result.getString("login"));
				u.setSenha(result.getString("senha"));
				
				usuarios.add(u);
			}
			
			conexao.commit();
			
			return usuarios;
		}
	
	
	
	
	 
	
	
	
	
	
	
	
	
	
	
	
	// VALIDA LOGIN
	/* 
	public boolean validarLogin(String login) throws Exception {
		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper('"+login+"');";
		
        PreparedStatement statement = conexao.prepareStatement(sql);
		
		ResultSet resutado = statement.executeQuery();
		
		resutado.next();
		return resutado.getBoolean("existe");
		
	}
	*/
	
	
}
