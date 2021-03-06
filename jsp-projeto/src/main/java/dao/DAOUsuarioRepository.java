package dao;

import java.sql.Connection;
import java.util.Date;
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

	// SALVAR USUARIO
	public Login salvarUsuario(Login u) throws Exception {

		String sql = "INSERT INTO usuario (nome, email, login, senha,perfil,cep,logradouro,bairro,localidade,uf,dataNascimento) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement salvar = conexao.prepareStatement(sql);
		salvar.setString(1, u.getNome());
		salvar.setString(2, u.getEmail());
		salvar.setString(3, u.getLogin());
		salvar.setString(4, u.getSenha());
		salvar.setString(5, u.getPerfil());
		salvar.setString(6, u.getCep());
		salvar.setString(7, u.getLogradouro());
		salvar.setString(8, u.getBairro());
		salvar.setString(9, u.getLocalidade());;
		salvar.setString(10, u.getUf());
		salvar.setString(11, u.getDataNascimento());


		salvar.execute();


		conexao.commit();

		atualizarFoto(u);

		return this.getUsuarioByLogin(u.getLogin());


	}

	// UPDATE USUARIO
	public Login editarUsuario(Login u) throws Exception {

		String sql = "UPDATE usuario SET nome=?, email=?, login=?, senha=?, perfil=?, "
				+ "cep=?, logradouro=?, bairro=?, localidade=?, uf=?, dataNascimento=? WHERE id= ?";
		PreparedStatement editar = conexao.prepareStatement(sql);
		editar.setString(1, u.getNome());
		editar.setString(2, u.getEmail());
		editar.setString(3, u.getLogin());
		editar.setString(4, u.getSenha());
		editar.setString(5, u.getPerfil());
		editar.setString(6, u.getCep());
		editar.setString(7, u.getLogradouro());
		editar.setString(8, u.getBairro());
		editar.setString(9, u.getLocalidade());
		editar.setString(10, u.getUf());
		editar.setString(11, u.getDataNascimento());
		editar.setLong(12,u.getId());

		editar.execute();

		conexao.commit();

		atualizarFoto(u);

		return this.getUsuarioByLogin(u.getLogin());
	}

	// DELETAR USUARIO
	public void deletarUsuario(Long id) throws Exception {
		DAOTelefoneRepository telDao = new DAOTelefoneRepository();
		telDao.deletarTelefoneUsuario(id);

		String sql = "DELETE FROM usuario WHERE id = ?";
		PreparedStatement deleta = conexao.prepareStatement(sql);
		deleta.setLong(1, id);

		deleta.executeUpdate();

		conexao.commit();

	}




	// ATUALIZAR FOTO
	public void atualizarFoto(Login u) throws SQLException {

		if(u.getFotoBase64() != null) {
			String sql = "UPDATE usuario SET fotoBase64 = ?, extensaoFoto = ? WHERE login = ?";
			PreparedStatement salvarFoto = conexao.prepareStatement(sql);
			salvarFoto.setString(1, u.getFotoBase64());
			salvarFoto.setString(2, u.getExtensaoFoto());
			salvarFoto.setString(3, u.getLogin());

			salvarFoto.execute();

			conexao.commit();
		}
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
			u.setUserAdm(result.getInt("useradm"));
			u.setPerfil(result.getString("perfil"));
			u.setExtensaoFoto(result.getString("extensaoFoto"));
			u.setFotoBase64(result.getString("fotoBase64"));

			u.setCep(result.getString("cep"));
			u.setLogradouro(result.getString("logradouro"));
			u.setBairro(result.getString("bairro"));
			u.setLocalidade(result.getString("localidade"));
			u.setUf(result.getString("uf"));
			u.setDataNascimento(result.getString("dataNascimento"));

		}

		conexao.commit();

		return u;
	}

	// USUARIO EXISTE
		public int usuarioExiste(String login) throws Exception {
			
			String sql = "SELECT COUNT(id) AS t FROM usuario WHERE login = ?";
			PreparedStatement prepara = conexao.prepareStatement(sql);
			prepara.setString(1, login);
			
			ResultSet result = prepara.executeQuery();

			result.next();

			int resultado = result.getInt("t");
			 
			 
			return resultado;
		}


	// GET BUSCA USUARIOS
	public List<Login> getUsuariosBusca(String login, int offset) throws Exception {

		String sql = "SELECT * FROM usuario WHERE nome LIKE \"%"+login+"%\" AND useradm = 0 ORDER BY nome LIMIT 5 OFFSET "+offset;
		//String sql = "SELECT * FROM usuario WHERE nome LIKE \"%"+login+"%\" AND useradm = 0";
		PreparedStatement prepara = conexao.prepareStatement(sql);

		ResultSet result = prepara.executeQuery();


		List<Login> usuarios = new ArrayList<>();


		while(result.next()) {

			Login u = new Login();
			u.setId(result.getLong("id"));
			u.setEmail(result.getString("email"));
			u.setNome(result.getString("nome"));
			u.setLogin(result.getString("login"));
			u.setSenha(result.getString("senha"));
			u.setPerfil(result.getString("perfil"));
			u.setExtensaoFoto(result.getString("extensaoFoto"));
			u.setFotoBase64(result.getString("fotoBase64"));

			u.setCep(result.getString("cep"));
			u.setLogradouro(result.getString("logradouro"));
			u.setBairro(result.getString("bairro"));
			u.setLocalidade(result.getString("localidade"));
			u.setUf(result.getString("uf"));
			u.setDataNascimento(result.getString("dataNascimento"));

			usuarios.add(u);
		}


		return usuarios;
	}

	// GET USUARIO BY ID
	public Login getUsuarioById(Long id) throws Exception {

		String sql = "SELECT * FROM usuario WHERE id = ? AND useradm = 0";
		PreparedStatement prepara = conexao.prepareStatement(sql);
		prepara.setLong(1, id);

		ResultSet result = prepara.executeQuery();

		Login u = new Login();

		while(result.next()) {
			u.setId(result.getLong("id"));
			u.setEmail(result.getString("email"));
			u.setNome(result.getString("nome"));
			u.setLogin(result.getString("login"));
			u.setSenha(result.getString("senha"));
			u.setPerfil(result.getString("perfil"));
			u.setExtensaoFoto(result.getString("extensaoFoto"));
			u.setFotoBase64(result.getString("fotoBase64"));

			u.setCep(result.getString("cep"));
			u.setLogradouro(result.getString("logradouro"));
			u.setBairro(result.getString("bairro"));
			u.setLocalidade(result.getString("localidade"));
			u.setUf(result.getString("uf"));
			u.setDataNascimento(result.getString("dataNascimento"));

		}

		conexao.commit();

		return u;
	}




	// GET BUSCA USUARIOS
	public List<Login> getUsuarios() throws Exception {

			String sql = "SELECT * FROM usuario WHERE useradm = 0 ";
			PreparedStatement prepara = conexao.prepareStatement(sql);

			ResultSet result = prepara.executeQuery();


			List<Login> usuarios = new ArrayList<>();


			while(result.next()) {

				Login u = new Login();
				u.setId(result.getLong("id"));
				u.setEmail(result.getString("email"));
				u.setNome(result.getString("nome"));
				u.setLogin(result.getString("login"));
				u.setSenha(result.getString("senha"));
				u.setPerfil(result.getString("perfil"));
				u.setExtensaoFoto(result.getString("extensaoFoto"));
				u.setFotoBase64(result.getString("fotoBase64"));

				u.setCep(result.getString("cep"));
				u.setLogradouro(result.getString("logradouro"));
				u.setBairro(result.getString("bairro"));
				u.setLocalidade(result.getString("localidade"));
				u.setUf(result.getString("uf"));
				u.setDataNascimento(result.getString("dataNascimento"));

				usuarios.add(u);
			}


			return usuarios;
		}

	// GET BUSCA USUARIOS PAGINACAO
	public List<Login> getUsuariosPaginacao(int offset) throws Exception {

			String sql = "SELECT * FROM usuario WHERE useradm = 0 ORDER BY nome LIMIT 5 OFFSET "+offset;
			PreparedStatement prepara = conexao.prepareStatement(sql);

			ResultSet result = prepara.executeQuery();


			List<Login> usuarios = new ArrayList<>();


			while(result.next()) {

				Login u = new Login();
				u.setId(result.getLong("id"));
				u.setEmail(result.getString("email"));
				u.setNome(result.getString("nome"));
				u.setLogin(result.getString("login"));
				u.setSenha(result.getString("senha"));
				u.setPerfil(result.getString("perfil"));
				u.setExtensaoFoto(result.getString("extensaoFoto"));
				u.setFotoBase64(result.getString("fotoBase64"));

				u.setCep(result.getString("cep"));
				u.setLogradouro(result.getString("logradouro"));
				u.setBairro(result.getString("bairro"));
				u.setLocalidade(result.getString("localidade"));
				u.setUf(result.getString("uf"));
				u.setDataNascimento(result.getString("dataNascimento"));

				usuarios.add(u);
			}


			return usuarios;
		}

	
	// GET BUSCA USUARIOS
		public List<Login> getUsuarios(String dataInicial, String dataFinal) throws Exception {

				String sql = "SELECT * FROM usuario WHERE useradm = 0 and dataNascimento >= ? and dataNascimento <= ? ";
				PreparedStatement prepara = conexao.prepareStatement(sql);
				prepara.setString(1, dataInicial);
				prepara.setString(2, dataFinal);
				
				ResultSet result = prepara.executeQuery();


				List<Login> usuarios = new ArrayList<>();


				while(result.next()) {

					Login u = new Login();
					u.setId(result.getLong("id"));
					u.setEmail(result.getString("email"));
					u.setNome(result.getString("nome"));
					u.setLogin(result.getString("login"));
					u.setSenha(result.getString("senha"));
					u.setPerfil(result.getString("perfil"));
					u.setExtensaoFoto(result.getString("extensaoFoto"));
					u.setFotoBase64(result.getString("fotoBase64"));

					u.setCep(result.getString("cep"));
					u.setLogradouro(result.getString("logradouro"));
					u.setBairro(result.getString("bairro"));
					u.setLocalidade(result.getString("localidade"));
					u.setUf(result.getString("uf"));
					u.setDataNascimento(result.getString("dataNascimento"));

					usuarios.add(u);
				}


				return usuarios;
			}
	

	// TOTAL DE PAGINA
	public int totalPagina() throws SQLException {

		String sql = "SELECT COUNT(id) AS total FROM usuario WHERE useradm = 0";
		PreparedStatement prepara = conexao.prepareStatement(sql);
		ResultSet result = prepara.executeQuery();

		result.next();

		Double resultado = result.getDouble("total");
		double limit = 5.0;

		Double pagina = resultado / limit;
		double resto = pagina % 2;

		if(resto > 0) {
			pagina++;
		}

		return pagina.intValue();
	}

	// TOTAL DE PAGINA BUSCA
	public int totalPaginaBusca(String login) throws SQLException {

		String sql = "SELECT COUNT(id) AS total FROM usuario WHERE nome LIKE \"%"+login+"%\" AND useradm = 0";
		PreparedStatement prepara = conexao.prepareStatement(sql); 
		ResultSet result = prepara.executeQuery();
		
		result.next();

		Double resultado = result.getDouble("total");
		double limit = 5.0;

		Double pagina = resultado / limit;
		double resto = pagina % 2;

		if(resto > 0) {
			pagina++;
		}

		return pagina.intValue();
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
