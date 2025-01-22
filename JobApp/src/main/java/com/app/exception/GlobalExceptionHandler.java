package com.app.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);

	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<String> handleExpiredTokenException(SignatureException exception) {
		LOGGER.info("GlobalExceptionHandler.handleExpiredTokenException : " + exception.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT token is Invalid");
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<Map<String, String>> handleAuthenticationException(AuthenticationException ex) {
		LOGGER.info("GlobalExceptionHandler.handleAuthenticationException : " + ex.getMessage());
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", "Invalid username or password");
		errorResponse.put("timestamp", LocalDateTime.now().toString());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
	}
}
