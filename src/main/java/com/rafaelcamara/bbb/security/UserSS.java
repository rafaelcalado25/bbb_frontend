package com.rafaelcamara.bbb.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rafaelcamara.bbb.entity.Perfil;

public class UserSS implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String username;
	private String password;
	Collection<SimpleGrantedAuthority> authority = new ArrayList<SimpleGrantedAuthority>();
	
	public UserSS() {
		super();
	}

	public UserSS(Long id, String username, String password, Perfil perfis) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.authority.add(new SimpleGrantedAuthority(perfis.getNome()));
				
	}

	public Long getId() {
		return this.id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authority;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public boolean hasHole(Perfil entidade) {
		for (GrantedAuthority grantedAuthority : authority) {
			if(grantedAuthority.getAuthority().equals(entidade.getNome())) {
				return true;
			}
			
		}
		return false;
	}

}
