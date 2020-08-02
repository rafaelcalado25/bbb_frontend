package com.rafaelcamara.bbb.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.rafaelcamara.bbb.dto.ParedaoDTO;
import com.rafaelcamara.bbb.entity.Paredao;
import com.rafaelcamara.bbb.entity.Participante;
import com.rafaelcamara.bbb.repository.ParedaoRepository;

@Service
public class ParedaoService {
	
	@Autowired
	private ParedaoRepository repo;
	
	@Autowired
	private ParticipanteService participanteService;	
	
	@Autowired
	private VotacaoService votacaoService;
	
	
	public List<Paredao> findAllParedoes() {
		return repo.findAll();
	}
	
	public Paredao findParedaoAtivo() {
		Paredao retorno = null;
		List<Paredao> paredoes = repo.findByFechadoOrderByDhFechamento(false);
		if(paredoes != null && !paredoes.isEmpty()) {
			retorno = paredoes.get(0);
		}
		return  retorno;
	}
	
	public Paredao fromParedaoDTO (ParedaoDTO entidade) {
		Paredao retorno = null;
		if(entidade != null) {
			retorno = new Paredao();
			retorno.setDhFechamento(entidade.getDhFechamento());
			retorno.setId(entidade.getId());
			retorno.setFechado(entidade.getFechado());
			
			if(entidade.getParticipantes() != null &&
					!entidade.getParticipantes().isEmpty()) {
				retorno.setParticipantes(entidade.getParticipantes().stream()
						.map(p -> this.participanteService.fromParticipanteDTO(p)).collect(Collectors.toSet()));
			}
			
			retorno.setRealizacao(entidade.getRealizacao());
		}
		return retorno;
	}
	
	public ParedaoDTO formarParedao(ParedaoDTO entidade) {
		Paredao consulta = this.findParedaoAtivo();
		ParedaoDTO retorno = entidade;
		if(consulta != null) {
			throw new DuplicateKeyException("Já existe um paredão ativo");
		}
		consulta = this.fromParedaoDTO(entidade);
		consulta = this.repo.saveAndFlush(consulta);
		retorno.setId(consulta.getId());
		return retorno;
	}
	
	public ParedaoDTO liberarParedaoCriado(ParedaoDTO entidade) {
		Paredao paredao = this.repo.findByFechadoTrueAndDhFechamentoIsNull();
		ParedaoDTO retorno = null;
		if (paredao != null && paredao.getId() == entidade.getId()) {
			paredao.setFechado(false);
			paredao = this.repo.saveAndFlush(paredao);
			retorno = new ParedaoDTO(paredao);
		}
		return retorno;
	}
	
	public ParedaoDTO fecharParedaoCriado(ParedaoDTO entidade) {
		Paredao paredao = this.findParedaoAtivo();
		Set<Participante> participantes = null;
		Participante participanteEliminado = null;
		Integer maiorVotacao = 0;
		Integer votacao = 0;
		ParedaoDTO retorno = null;
		if (paredao != null && paredao.getId() == entidade.getId()) {
			paredao.setFechado(true);
			paredao.setDhFechamento(new Timestamp(System.currentTimeMillis()));
			participantes = paredao.getParticipantes();
			for (Participante p : participantes) {
				if(participanteEliminado == null) {
					participanteEliminado = p;
					maiorVotacao = this.votacaoService.consultarVotacaoesParticipanteParedao(p);
				}
				votacao = this.votacaoService.consultarVotacaoesParticipanteParedao(p);
				if(maiorVotacao< votacao) {
					participanteEliminado = p;
					maiorVotacao = votacao;
				}
				
			}
			participanteEliminado.setEliminado(true);
			this.participanteService.save(participanteEliminado);
			paredao = this.repo.saveAndFlush(paredao);
			retorno = new ParedaoDTO(paredao);
		}
		return retorno;
	}
	
	public ParedaoDTO proximoParedao() {
		Paredao paredao = this.repo.findByFechadoTrueAndDhFechamentoIsNull();
		ParedaoDTO retorno = null;
		if (paredao != null) {
			retorno = new ParedaoDTO(paredao);
		}
		return retorno;
	}
	
	public Paredao consultarPorID(Long entidade) {
		return this.repo.findById(entidade).orElse(null);
	}

}
