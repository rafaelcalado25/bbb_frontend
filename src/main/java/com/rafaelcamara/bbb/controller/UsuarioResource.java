package com.rafaelcamara.bbb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelcamara.bbb.dto.NewUsuarioDTO;
import com.rafaelcamara.bbb.dto.UsuarioDTO;
import com.rafaelcamara.bbb.entity.Usuario;
import com.rafaelcamara.bbb.service.UsuarioService;


@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;	
	
	
	@RequestMapping(method=RequestMethod.POST)	
	public ResponseEntity <NewUsuarioDTO> insert (@RequestBody NewUsuarioDTO entidade) {
		//Passar o usuario logado
		NewUsuarioDTO retorno = service.insert(entidade);
		return ResponseEntity.ok().body(retorno);		
	}	
	
	@RequestMapping(value= "/usuarioLogado",method=RequestMethod.GET)
	public ResponseEntity<UsuarioDTO> consultarUsuarioLogado () {
		Usuario usuario = service.consultarUsuarioLogado();
		UsuarioDTO retorno = new UsuarioDTO(usuario);
		return ResponseEntity.ok().body(retorno);				
	}

}
