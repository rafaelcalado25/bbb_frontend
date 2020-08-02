package com.rafaelcamara.bbb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rafaelcamara.bbb.entity.Usuario;
import com.rafaelcamara.bbb.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario();
		usuario.setUsername(username);
		usuario = this.usuarioService.consultarPorUserName(usuario);
		if(usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new UserSS(usuario.getId(), usuario.getUsername(), usuario.getPassword(), usuario.getPerfis());
	}
	
	public static UserSS getUsuarioLogado() {
		try {
			return (UserSS)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch (Exception e) {
			return null;
		}
		
	}

}
