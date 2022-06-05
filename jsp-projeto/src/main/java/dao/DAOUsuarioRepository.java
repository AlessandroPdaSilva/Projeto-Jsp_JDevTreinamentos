package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.SingleConnection;
import model.Login;

public class DAOUsuarioRepository {
	
	private Connection conexao;
	
	// CONSTRUTOR
	public DAOUsuarioRepository(){
		conexao = SingleConnection.getConexao();
	}
	
	// SALVAR 
	public boolean salvarUsuario(Login u) throws Exception {
		
		String sql = "INSERT INTO usuario (nome, email, login, senha) VALUES (?,?,?,?)";
		PreparedStatement salvar = conexao.prepareStatement(sql);
		salvar.setString(1, u.getNome());
		salvar.setString(2, u.getEmail());
		salvar.setString(3, u.getLogin());
		salvar.setString(4, u.getSenha());
		
		boolean result = salvar.execute();
		
		
		conexao.commit();
		
		if(result) {
			return true;
		}else {
			return false;
		}
		
			
	}
	
}
