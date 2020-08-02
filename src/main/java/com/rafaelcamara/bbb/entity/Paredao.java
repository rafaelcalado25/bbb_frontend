package com.rafaelcamara.bbb.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Paredao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long id;
	
	private Date realizacao;
	
	private Boolean fechado;
	
	private Timestamp dhFechamento;
	
	@ManyToMany( cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	@JoinTable(name="paredaoxparticipantes", joinColumns= { @JoinColumn(name= "paredao_id", nullable = false) },
	inverseJoinColumns= { @JoinColumn(name= "participante_id", nullable = false) }) 
	private Set<Participante> participantes= new HashSet<Participante>();

	public Paredao(Long id, Date realizacao, Boolean fechado, Timestamp dhFechamento, Set<Participante> participantes) {
		super();
		this.id = id;
		this.realizacao = realizacao;
		this.fechado = fechado;
		this.dhFechamento = dhFechamento;
		this.participantes = participantes;
	}

	public Paredao() {
		super();
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

	public Set<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Set<Participante> participantes) {
		this.participantes = participantes;
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
		Paredao other = (Paredao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
	
}
