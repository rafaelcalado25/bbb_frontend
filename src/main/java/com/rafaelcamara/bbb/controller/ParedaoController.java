package com.rafaelcamara.bbb.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelcamara.bbb.dto.ParedaoDTO;
import com.rafaelcamara.bbb.entity.Paredao;
import com.rafaelcamara.bbb.service.ParedaoService;

@RestController
@RequestMapping(value="/paredao")
public class ParedaoController {
	
	@Autowired
	private ParedaoService service;
	
	@RequestMapping(value= "/paredoes",method=RequestMethod.GET)
	public ResponseEntity<List<ParedaoDTO>> consultarAll () {
		List <Paredao> paredoes = service.findAllParedoes();
		List<ParedaoDTO> retorno = paredoes.stream().map(paredao -> new ParedaoDTO(paredao)).collect(Collectors.toList());
		return ResponseEntity.ok().body(retorno);				
	}
	
	@RequestMapping(value= "/paredao/ativo",method=RequestMethod.GET)
	public ResponseEntity<ParedaoDTO> consultarPardaoAtivo () {
		Paredao paredao = service.findParedaoAtivo();
		ParedaoDTO retorno = null;
		if(paredao != null) {
			retorno = new ParedaoDTO(paredao); 
		}
				
		return ResponseEntity.ok().body(retorno);				
	}
	
	@RequestMapping(value= "/formarparedao", method=RequestMethod.POST)	
	public ResponseEntity <ParedaoDTO> formarParedao (@RequestBody ParedaoDTO entidade) {
		ParedaoDTO retorno = this.service.formarParedao(entidade);
		return ResponseEntity.ok().body(retorno);
		
	}
	
	@RequestMapping(value= "/liberarparedao", method=RequestMethod.PUT)	
	public ResponseEntity <ParedaoDTO> liberarParedao (@RequestBody ParedaoDTO entidade) {
		ParedaoDTO retorno = this.service.liberarParedaoCriado(entidade);
		return ResponseEntity.ok().body(retorno);
		
	}
	
	@RequestMapping(value= "/fecharparedao", method=RequestMethod.PUT)	
	public ResponseEntity <ParedaoDTO> fecharParedao (@RequestBody ParedaoDTO entidade) {
		ParedaoDTO retorno = this.service.fecharParedaoCriado(entidade);
		return ResponseEntity.ok().body(retorno);
		
	}
	
	@RequestMapping(value= "/proximoparedao",method=RequestMethod.GET)
	public ResponseEntity <ParedaoDTO> consultarProximoParedao () {
		ParedaoDTO retorno =this.service.proximoParedao();
		return ResponseEntity.ok().body(retorno);				
	}

}
