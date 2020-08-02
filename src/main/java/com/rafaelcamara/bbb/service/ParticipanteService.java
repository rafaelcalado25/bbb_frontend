package com.rafaelcamara.bbb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelcamara.bbb.dto.ParticipanteDTO;
import com.rafaelcamara.bbb.entity.Participante;
import com.rafaelcamara.bbb.repository.ParticipanteRepository;

@Service
public class ParticipanteService {
	
	@Autowired
	private ParticipanteRepository repo;
	
	
	public List<Participante> findAllParticipantes() {
		return repo.findByEliminadoFalse();
	}
	
	public Participante fromParticipanteDTO(ParticipanteDTO entidade) {
		Participante retorno = null;
		if(entidade != null) {
			retorno = new Participante();
			retorno.setEliminado(entidade.isEliminado());
			retorno.setId(entidade.getId());
			retorno.setNome(entidade.getNome());
			retorno.setPicture(entidade.getPicture());
		}
		return retorno;
	}
	
	public Participante consultarPorChavePrimaria(Long id) {
		return repo.findById(id).orElse(null);
	}
	
	public void save(Participante entidade) {
		this.repo.save(entidade);
	}
}
