package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.SingleConnection;
import model.Login;

public class DAOLoginRepository {
	
	private Connection conexao;
	
	// CONTRUTOR
	public DAOLoginRepository() {
		 conexao = SingleConnection.getConexao();
	}
	
	// AUTENTICAR
	public boolean autenticaLogin(Login login) throws Exception{
			
		String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?";
		PreparedStatement autenticar = conexao.prepareStatement(sql);
		autenticar.setString(1, login.getLogin());
		autenticar.setString(2, login.getSenha());
		
		ResultSet result = autenticar.executeQuery();
		
		if(result.next()) {
			return true;
		}
		
		return false;
	}
	
}
