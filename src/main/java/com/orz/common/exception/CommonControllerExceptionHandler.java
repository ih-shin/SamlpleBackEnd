package com.orz.common.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.orz.base.domain.error.ErrorCode;
import com.orz.base.domain.error.ErrorCodeRepository;

@Order(999)
@RestControllerAdvice
public class CommonControllerExceptionHandler {
	
	@Autowired
	ErrorCodeRepository errorRepository;

    @ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorCode> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        final ErrorCode response = errorRepository.findByCode("C003");
        
        response.setMessage(response.getMessage() + "\\r\\n" + e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<ErrorCode> handleNoHandlerFoundException() {
		
        final ErrorCode response = errorRepository.findByCode("C002");
        		
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorCode> handleExceptionn(Exception e) {
		
        final ErrorCode response = errorRepository.findByCode("C001");
        		
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
}
