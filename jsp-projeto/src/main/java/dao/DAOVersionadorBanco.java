package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.SingleConnection;

public class DAOVersionadorBanco implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private Connection conexao;
	
	public DAOVersionadorBanco() {
		conexao = SingleConnection.getConexao();
	}
	
	// ARQUIVO SQL RODADO
	public boolean arquivoSqlRodado(String nomeArquivo) throws Exception{
		
		String sql = "SELECT COUNT(id) AS rodado FROM versionadorbanco WHERE arquivo_sql = ?";
		PreparedStatement prepara = conexao.prepareStatement(sql);
		prepara.setString(1, nomeArquivo);
		
		ResultSet result = prepara.executeQuery();
		result.next();
		 
		
		return result.getBoolean("rodado");
	}
	
	// GRAVA ARQUIVO
	public void gravaArquivo(String nomeArquivo) throws Exception{
		
		String sql = "INSERT INTO versionadorbanco (arquivo_sql) VALUES (?)";
		PreparedStatement salvar = conexao.prepareStatement(sql);
		salvar.setString(1, nomeArquivo);
		salvar.execute();
		
		conexao.commit();
		
	}
	
}
