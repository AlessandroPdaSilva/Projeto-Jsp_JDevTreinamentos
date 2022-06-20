package model;

import java.io.Serializable;

public class Telefone implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	private long id;
	private Login usuario;
	private String numero;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Login getUsuario() {
		return usuario;
	}
	public void setUsuario(Login usuario) {
		this.usuario = usuario;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	@Override
	public String toString() {
		return "Telefone [id=" + id + ", usuario=" + usuario + ", numero=" + numero + "]";
	}
	
	
	
}
