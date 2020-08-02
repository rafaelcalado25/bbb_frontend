package com.rafaelcamara.bbb.config;

public enum Constantes {
	NM_SECURITY_AUTHENTICATION_NAME(1, "Authorization"),
	NM_SECURITY_BEARER_TOKEN_NAME(2, "Bearer ");
	
	public int codigo;
	public String constante;
	
	private Constantes(int codigo, String constante) {
		// TODO Auto-generated constructor stub
		this.codigo = codigo;
		this.constante = constante;
	}

}
