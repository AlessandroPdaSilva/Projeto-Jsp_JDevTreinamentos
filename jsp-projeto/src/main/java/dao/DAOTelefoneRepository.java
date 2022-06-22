package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.SingleConnection;
import model.Telefone;

public class DAOTelefoneRepository {

	private Connection conexao;
	DAOUsuarioRepository usuDao = new DAOUsuarioRepository();
	
	// CONSTRUTOR
	public DAOTelefoneRepository() {
		 conexao = SingleConnection.getConexao();
	}
	
	// SALVAR TELEFONE
	public Telefone salvarTelefone(Telefone tel) throws Exception{
		
		String sql = "INSERT INTO telefone (usuario_id,numero) VALUES (?,?)";
		PreparedStatement salvar = conexao.prepareStatement(sql);
		salvar.setLong(1, tel.getUsuario().getId());
		salvar.setString(2, tel.getNumero());
		salvar.execute();
		
		conexao.commit();
		
		return tel;
	}
	
	// UPDATE TELEFONE
	public Telefone editarTelefone(Telefone tel) throws Exception {
		
		String sql = "UPDATE telefone SET numero WHERE id= ?";
		PreparedStatement editar = conexao.prepareStatement(sql);
		editar.setString(1, tel.getNumero());
		editar.execute();
		
		conexao.commit();
		
		return tel;
	}
	
	// DELETAR TELEFONE
	public void deletarTelefone(Long id) throws Exception {
		String sql = "DELETE FROM telefone WHERE id = ?";
		PreparedStatement deletar = conexao.prepareStatement(sql);
		deletar.setLong(1, id);
		deletar.executeUpdate();
		
		conexao.commit();
	}
	
	// DELETAR TELEFONE USUARIO
	public void deletarTelefoneUsuario(Long idUsuario) throws Exception {
		String sql = "DELETE FROM telefone WHERE usuario_id = ?";
		PreparedStatement deletar = conexao.prepareStatement(sql);
		deletar.setLong(1, idUsuario);
		deletar.executeUpdate();
		
		conexao.commit();
	}
			
	// GET TELEFONES USUARIO
	public List<Telefone> getTelefonesUsuario(Long idUsuario) throws Exception{
		
		String sql = "SELECT * FROM telefone WHERE usuario_id = ?";
		PreparedStatement prepara = conexao.prepareStatement(sql);
		prepara.setLong(1, idUsuario);
		
		ResultSet result = prepara.executeQuery();
		
		
		List<Telefone> listaTel = new ArrayList<Telefone>();
		
		while(result.next()) {
			
			Telefone tel = new Telefone();
			tel.setId(result.getLong("id"));
			tel.setUsuario(usuDao.getUsuarioById(idUsuario));
			tel.setNumero(result.getString("numero"));
			
			listaTel.add(tel);
		}
		
		conexao.commit();
		
		return listaTel;
	}
	
	
}
