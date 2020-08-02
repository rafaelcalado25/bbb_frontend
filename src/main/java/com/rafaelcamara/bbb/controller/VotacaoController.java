package com.rafaelcamara.bbb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelcamara.bbb.dto.RegistroVotacaoAgrupamentoPorDataDTO;
import com.rafaelcamara.bbb.dto.VotacaoDTO;
import com.rafaelcamara.bbb.entity.Participante;
import com.rafaelcamara.bbb.service.ParticipanteService;
import com.rafaelcamara.bbb.service.VotacaoService;

@RestController
@RequestMapping(value="/votacao")
public class VotacaoController {
	
	@Autowired
	private VotacaoService service;	
	
	
	@Autowired
	private ParticipanteService participanteService;	
	
	
	@RequestMapping(value= "/efetuarvotacao", method=RequestMethod.POST)
	@PreAuthorize("hasAnyRole('AUDIENCIA')")
	public ResponseEntity <VotacaoDTO> formarParedao (@RequestBody VotacaoDTO entidade) {
		VotacaoDTO retorno = null;
		if(entidade != null && entidade.getParticipante()!=null) {
			retorno = this.service.efetuarVotacao(entidade.getParticipante());
		}
		return ResponseEntity.ok().body(retorno);
		
	}	
	
	@RequestMapping(value= "/consultarvotacaoparticipanteparedao/{entidade}", method=RequestMethod.GET)	
	public ResponseEntity <Integer> ConsultarVotacaoParticipanteParedao (@PathVariable Long entidade ) {
		Integer retorno = 0;
		Participante participante = this.participanteService.consultarPorChavePrimaria(entidade);
		if(participante != null ) {
			retorno = this.service.consultarVotacaoesParticipanteParedao(participante);
		}
		return ResponseEntity.ok().body(retorno);
		
	}	
	
	@RequestMapping(value= "/consultarvotacaoagrupadoporhora/{entidade}", method=RequestMethod.GET)	
	public ResponseEntity <List<RegistroVotacaoAgrupamentoPorDataDTO> >
		ConsultarVotacaoAgrupadoporHora (@PathVariable Long entidade ) {
		List<RegistroVotacaoAgrupamentoPorDataDTO> retorno = null;
		if(entidade != null ) {
			retorno = this.service.consultarVotacaoesAgrupadoPorTempo(entidade);
		}
		return ResponseEntity.ok().body(retorno);
		
	}	

}
