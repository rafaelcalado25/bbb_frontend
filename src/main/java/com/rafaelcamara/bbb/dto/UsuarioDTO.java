package com.rafaelcamara.bbb.dto;

import com.rafaelcamara.bbb.entity.Perfil;
import com.rafaelcamara.bbb.entity.Usuario;

public class UsuarioDTO {
	
	private Long id;
	
	private String username;	
	
	private Perfil perfis;

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(Long id, String username, Perfil perfis) {
		super();
		this.id = id;
		this.username = username;
		this.perfis = perfis;
	}	
	
	public UsuarioDTO(Usuario entidade) {
		super();
		this.id = entidade.getId();
		this.username = entidade.getUsername();
		this.perfis = entidade.getPerfis();
		
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

	public Perfil getPerfis() {
		return perfis;
	}

	public void setPerfis(Perfil perfis) {
		this.perfis = perfis;
	}
	

}
