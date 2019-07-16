package com.orz.common.exception.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.orz.base.domain.error.ErrorCode;
import com.orz.base.domain.error.ErrorCodeRepository;

@Order(10)
@RestControllerAdvice
public class UserControllerExceptionHandler {
	
	@Autowired
	ErrorCodeRepository errorRepository;

	@ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<ErrorCode> handleUserNotFoundException(UserNotFoundException e) {
		
        final ErrorCode response = errorRepository.findByCode("U001");
        		
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }	
	
	@ExceptionHandler(UserDuplicateEmailException.class)
    protected ResponseEntity<ErrorCode> handleUserDuplicateEmailException(UserDuplicateEmailException e) {
		
        final ErrorCode response = errorRepository.findByCode("U002");
        		
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }	
	
	@ExceptionHandler(UserInvalidPasswordException.class)
    protected ResponseEntity<ErrorCode> handleUserInvalidPasswordExceptionException(UserInvalidPasswordException e) {
		
        final ErrorCode response = errorRepository.findByCode("U003");
        		
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }	
	
	@ExceptionHandler(UserUnauthenticatedException.class)
    protected ResponseEntity<ErrorCode> handleUserUnauthenticatedExceptionException(UserUnauthenticatedException e) {
		
        final ErrorCode response = errorRepository.findByCode("U004");
        		
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }	
}
