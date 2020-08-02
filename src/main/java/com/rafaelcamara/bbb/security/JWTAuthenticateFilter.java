package com.rafaelcamara.bbb.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafaelcamara.bbb.config.Constantes;
import com.rafaelcamara.bbb.dto.CredencialDTO;

public class JWTAuthenticateFilter extends UsernamePasswordAuthenticationFilter {

	private JWTUtil jwtUtil;
	
	private AuthenticationManager authenticationManager;	
	
	
	public JWTAuthenticateFilter(JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
		this.setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
		this.jwtUtil = jwtUtil;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			CredencialDTO cred = new ObjectMapper().readValue(request.getInputStream(), CredencialDTO.class);
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken
					(cred.getEmail(), cred.getPassword(), new ArrayList<>());
			Authentication auth = this.authenticationManager.authenticate(token);
			return auth;
		} catch(IOException ex) {
			throw new RuntimeException(ex);
		}		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
		Authentication authResult) throws IOException, ServletException {
			String usename = ((UserSS)authResult.getPrincipal()).getUsername();
			String token = jwtUtil.generateToken(usename);
			response.addHeader(Constantes.NM_SECURITY_AUTHENTICATION_NAME.constante, 
					Constantes.NM_SECURITY_BEARER_TOKEN_NAME.constante+token);
			response.addHeader("access-control-expose-headers", Constantes.NM_SECURITY_AUTHENTICATION_NAME.constante);
			response.getWriter().append("[{\"Authorization\":\""+ token + "\"}]");
			
	}
	
	
	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {
			response.setStatus(401);
            response.setContentType("application/json"); 
            response.getWriter().append(json());
			
		}
		
		private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Não autorizado\", "
                + "\"message\": \"Username ou senha inválidos\", "
                + "\"path\": \"/login\"}";
        }
		
	}
	
	

}
