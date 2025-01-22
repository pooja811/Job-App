package com.app.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);

	@ExceptionHandler(ExpiredTokenException.class)
	public ResponseEntity<String> handleExpiredTokenException(ExpiredTokenException exception) {

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT token has expired. Please log in again.");
	}
}
