package com.rafaelcamara.bbb.dto;

import java.sql.Timestamp;

import com.rafaelcamara.bbb.entity.Paredao;
import com.rafaelcamara.bbb.entity.Participante;
import com.rafaelcamara.bbb.entity.Usuario;
import com.rafaelcamara.bbb.entity.Votacao;

public class VotacaoDTO {
	
	private Long id;
	
	private Paredao paredao;
	
	private Participante participante;
	
	private Usuario usuario;
	
	private Timestamp dhVoto;

	public VotacaoDTO() {
		super();
	}

	public VotacaoDTO(Long id, Paredao paredao, Participante participante, Usuario usuario, Timestamp dhVoto) {
		super();
		this.id = id;
		this.paredao = paredao;
		this.participante = participante;
		this.usuario = usuario;
		this.dhVoto = dhVoto;
	}
	
	public VotacaoDTO(Votacao entidade) {
		super();
		this.id = entidade.getId();
		//this.paredao = entidade.getParedao();
		this.participante = entidade.getParticipante();
		this.usuario = entidade.getUsuario();
		this.dhVoto = entidade.getDhVoto();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Paredao getParedao() {
		return paredao;
	}

	public void setParedao(Paredao paredao) {
		this.paredao = paredao;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Timestamp getDhVoto() {
		return dhVoto;
	}

	public void setDhVoto(Timestamp dhVoto) {
		this.dhVoto = dhVoto;
	}
		

}
