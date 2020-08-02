package com.rafaelcamara.bbb.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.rafaelcamara.bbb.config.Constantes;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
	
	private JWTUtil jwtUtil = null;
	private UserDetailsService userDetailsService = null;

	public JWTAuthorizationFilter(JWTUtil jwtUtil, AuthenticationManager authenticationManager,
			UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException { 
		String json =request.getHeader(Constantes.NM_SECURITY_AUTHENTICATION_NAME.constante);
		String autorizacao = "Bearer " + json.substring(json.indexOf(":")+2, json.lastIndexOf("}")-1);
		
		UsernamePasswordAuthenticationToken auth = null;		
		
		
		if(autorizacao != null && autorizacao.startsWith(Constantes.NM_SECURITY_BEARER_TOKEN_NAME.constante)) {
			 auth = this.getAuthentication(autorizacao.substring(
					 Constantes.NM_SECURITY_BEARER_TOKEN_NAME.constante.length()));
			 
			 if(auth !=null) {
				 SecurityContextHolder.getContext().setAuthentication(auth);
			 }
		}
		chain.doFilter(request, response);
		
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		String username = null;
		UserDetails user = null; 
		
		if(this.jwtUtil.tokenValido(token)) {
			username = this.jwtUtil.getUsername(token);
			user = this.userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		
		
		return null;
	}
	
	
}
