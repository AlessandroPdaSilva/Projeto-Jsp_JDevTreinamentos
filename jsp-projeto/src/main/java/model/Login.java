package model;

import java.io.Serializable;
import java.util.Date;

public class Login implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String email;
	private String nome;
	private String login;
	private String senha;
	private int userAdm;
	private String perfil;
	private String extensaoFoto;
	private String fotoBase64;
	private String dataNascimento;

	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUserAdm() {
		return userAdm;
	}
	public void setUserAdm(int userAdm) {
		this.userAdm = userAdm;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getExtensaoFoto() {
		return extensaoFoto;
	}
	public void setExtensaoFoto(String extensaoFoto) {
		this.extensaoFoto = extensaoFoto;
	}
	public String getFotoBase64() {
		return fotoBase64;
	}
	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}


	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	
	
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	@Override
	public String toString() {
		return "Login [id=" + id + ", email=" + email + ", nome=" + nome + ", login=" + login + ", senha=" + senha
				+ ", userAdm=" + userAdm + ", perfil=" + perfil + ", extensaoFoto=" + extensaoFoto + ", fotoBase64="
				+ fotoBase64 + "]";
	}










}
