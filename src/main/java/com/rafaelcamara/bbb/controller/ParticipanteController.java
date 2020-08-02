package com.rafaelcamara.bbb.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelcamara.bbb.dto.ParticipanteDTO;
import com.rafaelcamara.bbb.entity.Participante;
import com.rafaelcamara.bbb.service.ParticipanteService;

@RestController
@RequestMapping(value="/participante")
public class ParticipanteController {
	
	@Autowired
	private ParticipanteService service;
	
	@RequestMapping(value= "/participantes",method=RequestMethod.GET)
	public ResponseEntity<List <ParticipanteDTO>> consultarAll () {
		List <Participante> participantes = service.findAllParticipantes();
		List<ParticipanteDTO> retorno = participantes.stream().map(participante -> new ParticipanteDTO(participante)).collect(Collectors.toList());
		return ResponseEntity.ok().body(retorno);				
	}
	

}
