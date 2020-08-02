package com.rafaelcamara.bbb.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Participante implements Comparable<Participante>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long id;
	
	private String nome;
	
	private String picture;
	
	@Column(columnDefinition = "boolean default false")
	private boolean eliminado;

	
	@ManyToMany
	@JoinTable(name="paredaoxparticipantes", joinColumns= { @JoinColumn(name= "participante_id", nullable = false) },
	inverseJoinColumns= { @JoinColumn(name= "paredao_id", nullable = false) }) 
	private Set<Paredao> paredoes= new HashSet<Paredao>();
	
	public Participante() {
		super();
	}


	public Participante(Long id, String nome, String picture, boolean eliminado) {
		super();
		this.id = id;
		this.nome = nome;
		this.picture = picture;
		this.eliminado = eliminado;
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


	public Set<Paredao> getParedoes() {
		return paredoes;
	}


	public void setParedoes(Set<Paredao> paredoes) {
		this.paredoes = paredoes;
	}

	
	public boolean isEliminado() {
		return eliminado;
	}


	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
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
		Participante other = (Participante) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
	@Override
    public int compareTo(Participante p) 
    {
        return this.getId().compareTo( p.getId() );
    }
	
}
