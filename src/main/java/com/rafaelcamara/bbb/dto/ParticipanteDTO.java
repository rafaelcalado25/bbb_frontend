package com.rafaelcamara.bbb.dto;

import com.rafaelcamara.bbb.entity.Participante;

public class ParticipanteDTO {
	
	private Long id;
	
	private String nome;
	
	private String picture;
	
	private boolean eliminado;

	public ParticipanteDTO() {
		super();
	}

	public ParticipanteDTO(Long id, String nome, String picture, boolean eliminado) {
		super();
		this.id = id;
		this.nome = nome;
		this.picture = picture;
		this.eliminado = eliminado;
	}
	
	public ParticipanteDTO(Participante entidade) {
		super();
		this.id = entidade.getId();
		this.nome = entidade.getNome();
		this.picture = entidade.getPicture();
		this.eliminado = entidade.isEliminado();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	
	
}
