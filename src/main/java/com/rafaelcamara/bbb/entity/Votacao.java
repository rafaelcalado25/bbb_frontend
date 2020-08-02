package com.rafaelcamara.bbb.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Votacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long id;
	
	@ManyToOne
	private Paredao paredao;
	
	@ManyToOne
	private Participante participante;
	
	@ManyToOne
	private Usuario usuario;
	
	private Timestamp dhVoto;

	public Votacao() {
		super();
	}

	public Votacao(Long id, Paredao paredao, Participante participante, Usuario usuario, Timestamp dhVoto) {
		super();
		this.id = id;
		this.paredao = paredao;
		this.participante = participante;
		this.usuario = usuario;
		this.dhVoto = dhVoto;
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
		Votacao other = (Votacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
