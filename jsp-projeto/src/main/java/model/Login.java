package model;

import java.io.Serializable;

public class Login implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String email;
	private String nome;
	private String login;
	private String senha;
	
	
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
	@Override
	public String toString() {
		return "Login [id=" + id + ", email=" + email + ", nome=" + nome + ", login=" + login + ", senha=" + senha
				+ "]";
	}
	
	
	
	
	
}
