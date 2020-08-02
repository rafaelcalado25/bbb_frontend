package com.rafaelcamara.bbb.service;

import java.sql.Timestamp;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelcamara.bbb.dto.RegistroVotacaoAgrupamentoPorDataDTO;
import com.rafaelcamara.bbb.dto.VotacaoDTO;
import com.rafaelcamara.bbb.entity.Paredao;
import com.rafaelcamara.bbb.entity.Participante;
import com.rafaelcamara.bbb.entity.Usuario;
import com.rafaelcamara.bbb.entity.Votacao;
import com.rafaelcamara.bbb.repository.VotacaoRepository;
import com.rafaelcamara.bbb.service.exception.PreConditionException;

@Service
public class VotacaoService {
	
	@Autowired
	private VotacaoRepository repo;	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ParedaoService paredaoService;
	
	private final Integer QTD_MAX_POR_MIN = 10;
		
	
	public List<Votacao> findAllVotacoes() {
		return repo.findAll();
	}
	
	public Votacao fromVotacaoDTO(VotacaoDTO entidade) {
		Votacao retorno = null;
		if(entidade != null) {
			retorno = new Votacao();
			retorno.setDhVoto(entidade.getDhVoto());
			retorno.setId(entidade.getId());
			retorno.setParedao(entidade.getParedao());
			retorno.setParticipante(entidade.getParticipante());
			retorno.setUsuario(entidade.getUsuario());
		}
		return retorno;
	}
	
	
	public VotacaoDTO efetuarVotacao(Participante entidade) {
		VotacaoDTO retorno = null;
		DateTime dateTime = new DateTime();
		Timestamp time = new Timestamp(dateTime.getMillis());
		dateTime = dateTime.minusSeconds(60);
		Integer qtd = null;
		Usuario usuario = this.usuarioService.consultarUsuarioLogado();
		Paredao paredao = this.paredaoService.findParedaoAtivo();
		Votacao votacao = null;
		if(entidade != null && usuario != null && paredao != null) {
			votacao = new Votacao(null, paredao, entidade, usuario, time);
			qtd = this.repo.consultarVotacaoesUsuarioParedaoUltimoMinuto(paredao, 
					usuario, new Timestamp(dateTime.getMillis()), time);
			if(qtd < this.QTD_MAX_POR_MIN) {
				votacao = this.repo.save(votacao);
				retorno = new VotacaoDTO(votacao);
				System.out.println(retorno.getDhVoto());
			} else {
				throw new PreConditionException("Usuario já passou da cota de votações por minuto. Favor aguardar alguns segundos!");
			}
		}
		
		return retorno;
	}
	
	public Integer consultarVotacaoesParticipanteParedao(Participante entidade) {
		Paredao paredao = this.paredaoService.findParedaoAtivo();
		return this.repo.consultarVotacaoesParticipanteParedao(paredao, entidade);
	}
	
	public List<RegistroVotacaoAgrupamentoPorDataDTO> consultarVotacaoesAgrupadoPorTempo(Long entidade) {
		Paredao paredao = this.paredaoService.consultarPorID(entidade);
		
		return paredao != null? this.repo.consultarVotacaoesPorHora(paredao):null;
	}
}
