package com.rafaelcamara.bbb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rafaelcamara.bbb.dto.NewUsuarioDTO;
import com.rafaelcamara.bbb.entity.Usuario;
import com.rafaelcamara.bbb.repository.UsuarioRepository;
import com.rafaelcamara.bbb.security.UserSS;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;	
	
	@Autowired
	private BCryptPasswordEncoder pe;
		
	
	public Usuario salvar (Usuario entidade) {
		return repo.save(entidade);
	}
	
		
	public List<Usuario> consultar(){
		return repo.findAll();
	}
	
	public Usuario consultarPorUserName (Usuario entidade) {
		return repo.findByUsername(entidade.getUsername());
	}	
	
	public Usuario consultarPorId(Long pId) {
		Usuario retorno = null;
		Optional<Usuario> usuario = repo.findById(pId);
		retorno = usuario.orElse(null);
		
		return retorno;
	}
	
	public NewUsuarioDTO insert (NewUsuarioDTO entidade) {
		Usuario user = this.fromNewUsuarioDTO(entidade);		
		user = this.salvar(user);
		entidade.setId(user.getId());
		return entidade;
	}
	
	public Usuario fromNewUsuarioDTO (NewUsuarioDTO entidade) {
		Usuario retorno = new Usuario();
		retorno.setId(entidade.getId());
		retorno.setPerfis(entidade.getPerfil());
		retorno.setPassword(pe.encode(entidade.getPassword()));
		retorno.setUsername(entidade.getEmail());
		
		return retorno;
	}
	
	
	public Usuario consultarUsuarioLogado(){
		
		UserSS user = UserDetailsServiceImpl.getUsuarioLogado();
		Usuario usuario = new Usuario();
		usuario.setUsername(user.getUsername());
		usuario = this.consultarPorUserName(usuario);
		return usuario;
		
	}

}
