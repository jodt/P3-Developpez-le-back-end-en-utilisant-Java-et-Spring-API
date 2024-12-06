package com.openclassrooms.chatop.exception;

import com.openclassrooms.chatop.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseDto> handleBadCredentialsException(BadCredentialsException ex){
        ResponseDto response = ResponseDto.builder().message("error").build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<ResponseDto> handleBadCredentialsException(UserAlreadyRegisteredException ex){
        ResponseDto response = ResponseDto.builder().message("user already registered").build();
        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}