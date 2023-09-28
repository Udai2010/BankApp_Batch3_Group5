package com.bankapp.bankapp.exception;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomException{
    
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // @Override
    @ExceptionHandler(InvalidCredentialException.class)
    protected ResponseEntity<Object> handleInvalidCredentialsException(InvalidCredentialException ex) {

		Map<String, Object> responseBody = new LinkedHashMap<>();
		responseBody.put("timestamp", new Date());
		responseBody.put("status", HttpStatus.UNAUTHORIZED);

		responseBody.put("errors", "Invalid credentials. User can not be authenticated.");

		return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
	}

    @ExceptionHandler(InsufficientBalanceException.class)
    protected ResponseEntity<Object> handleInsufficientBalanceException(InsufficientBalanceException ex
			) {

		Map<String, Object> responseBody = new LinkedHashMap<>();
		responseBody.put("timestamp", new Date());
		responseBody.put("status", HttpStatus.BAD_REQUEST);

		responseBody.put("errors", "User does not have enough balance to do this transaction.");

		return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AccountDisabledException.class)
    protected ResponseEntity<Object> handleAccountDisabledException(AccountDisabledException ex
			) {

		Map<String, Object> responseBody = new LinkedHashMap<>();
		responseBody.put("timestamp", new Date());
		responseBody.put("status", HttpStatus.BAD_REQUEST);

		responseBody.put("errors", "Account is disabled. Please contact support for clarification.");

		return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
	}
}
