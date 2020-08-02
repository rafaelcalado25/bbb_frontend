package com.rafaelcamara.bbb.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rafaelcamara.bbb.service.exception.PreConditionException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(PreConditionException.class)
	public ResponseEntity<StandardError> authorization(PreConditionException e, HttpServletRequest request) {		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.PRECONDITION_FAILED.value(), "Falhou na regra", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(err);
	}
	
	

}
