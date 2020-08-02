package com.rafaelcamara.bbb.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.rafaelcamara.bbb.entity.Perfil;
import com.rafaelcamara.bbb.entity.Usuario;


public class NewUsuarioDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotEmpty
	private String email;
	private String password;
	private Perfil perfil;	
	
	
	public NewUsuarioDTO() {
		super();
	}

	public NewUsuarioDTO(Long id, String username, String password, Perfil perfil) {
		super();
		this.id = id;
		this.email = username;
		this.password = password;
		this.perfil = perfil;
	}
	
	public NewUsuarioDTO(Usuario entidade) {
		super();
		this.id = entidade.getId();
		this.email = entidade.getUsername();
		this.password = entidade.getPassword();
		this.perfil = entidade.getPerfis();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	

}
