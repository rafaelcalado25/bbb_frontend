package com.rafaelcamara.bbb.dto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import com.rafaelcamara.bbb.entity.Paredao;

public class ParedaoDTO {
	
	private Long id;
	
	private Date realizacao;
	
	private Boolean fechado;
	
	private Timestamp dhFechamento;
	
	private Set<ParticipanteDTO> participantes;

	public ParedaoDTO() {
		super();
	}

	public ParedaoDTO(Long id, Date realizacao, Boolean fechado, Timestamp dhFechamento,
			Set<ParticipanteDTO> participantes) {
		super();
		this.id = id;
		this.realizacao = realizacao;
		this.fechado = fechado;
		this.dhFechamento = dhFechamento;
		this.participantes = participantes;
	}
	
	public ParedaoDTO(Paredao entidade) {
		super();
		this.id = entidade.getId();
		this.realizacao = entidade.getRealizacao();
		this.fechado = entidade.getFechado();
		this.dhFechamento = entidade.getDhFechamento();
		if(entidade.getParticipantes() != null && !entidade.getParticipantes().isEmpty()) {
			this.participantes = entidade.getParticipantes().stream().map(p ->  
				new ParticipanteDTO(p)).collect(Collectors.toSet());
		}
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRealizacao() {
		return realizacao;
	}

	public void setRealizacao(Date realizacao) {
		this.realizacao = realizacao;
	}

	public Boolean getFechado() {
		return fechado;
	}

	public void setFechado(Boolean fechado) {
		this.fechado = fechado;
	}

	public Timestamp getDhFechamento() {
		return dhFechamento;
	}

	public void setDhFechamento(Timestamp dhFechamento) {
		this.dhFechamento = dhFechamento;
	}

	public Set<ParticipanteDTO> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Set<ParticipanteDTO> participantes) {
		this.participantes = participantes;
	}	
	
	
}
