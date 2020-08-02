package com.rafaelcamara.bbb.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario implements Serializable, Comparable<Usuario> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long id;
	
	private String username;
	
	@JsonIgnore
	private String password;
	
	
	@ManyToOne
	@JoinColumn(name="perfil_id")
	private Perfil perfis;	
	

	public Usuario() {
		super();
	}
	
	
	public Usuario(Long id,	String username, String password, Perfil perfis) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.perfis = perfis;
	}




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Perfil getPerfis() {
		return perfis;
	}


	public void setPerfis(Perfil perfis) {
		this.perfis = perfis;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	


	@Override
	public int compareTo(Usuario o) {
		// TODO Auto-generated method stub
		if(this.username != null) {
			return this.username.compareTo(o.getUsername());
		}
		return 1;
	}

	

}
