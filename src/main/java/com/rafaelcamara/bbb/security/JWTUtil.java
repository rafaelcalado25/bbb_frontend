package com.rafaelcamara.bbb.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	@Value("${jwt.secret}")
	private String secret; 
	
	@Value("${jwt.timeout}")
	private Long timeout; 
	
	public String generateToken(String username) {
		return Jwts.builder().setSubject(username)
				.setExpiration(new Date(this.timeout + System.currentTimeMillis()))
				.signWith(SignatureAlgorithm.HS512, this.secret.getBytes())
				.compact();
			
	}
	
	public boolean tokenValido(String token) {
		String username = null;
		Date expirationDate = null;
		Date now = new Date(System.currentTimeMillis());
		Claims claims = this.getClaims(token);
		if(claims != null) {
			username = claims.getSubject();
			expirationDate = claims.getExpiration();
			if(username != null && now.before(expirationDate)) {
				return true;
			}
		}
		
		
		return false;
	}
	
	private Claims getClaims(String token) {
		// TODO Auto-generated method stub
		try {
			return Jwts.parser().setSigningKey(this.secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}

	public String getUsername(String token) {
		String username = null;		
		Claims claims = this.getClaims(token);
		if(claims != null) {
			username = claims.getSubject();
			return username;
		}
		
		return null;
	}

}
