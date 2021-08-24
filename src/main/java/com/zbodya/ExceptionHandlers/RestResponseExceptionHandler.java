package com.zbodya.ExceptionHandlers;

import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler 
{

	@ExceptionHandler({NoSuchElementException.class})
	public ResponseEntity<Object> nahdleNoSuchElementException(Exception ex, WebRequest request)
	{
		HttpHeaders header = new HttpHeaders();
		header.set("SUCCESFULL", "FALSE");		
		ResponseEntity<Object> response = new ResponseEntity<Object>("Szukana wartość nie istnieje! Porszę podać poprawne dane!",header,HttpStatus.OK);		
		return response;
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> nahdleEmptyResultDataAccessException(Exception ex, WebRequest request)
	{
		HttpHeaders header = new HttpHeaders();
		header.set("SUCCESFULL", "FALSE");		
		ResponseEntity<Object> response = new ResponseEntity<Object>("Starasz się odwoływać do wartości która nie istnieje! Proszę podać poprawne dane!",header,HttpStatus.OK);		
		return response;
	}
	
	
	
	
	
}
