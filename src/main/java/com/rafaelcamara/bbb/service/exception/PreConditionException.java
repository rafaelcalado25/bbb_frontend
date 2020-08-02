package com.rafaelcamara.bbb.service.exception;

public class PreConditionException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public PreConditionException (String msg) {
		super(msg);
	}
	
	public PreConditionException (String msg, Throwable cause) {
		super(msg, cause);
	}

}
