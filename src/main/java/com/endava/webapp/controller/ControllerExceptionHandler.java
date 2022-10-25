package com.endava.webapp.controller;

import com.endava.webapp.exception.ErrorResponse;
import com.endava.webapp.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundHandler(
            ResourceNotFoundException e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), request.getDescription(false),
                HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> internalError(Exception e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // TODO - finish exception handling
//    // mb use HttpStatus = CONFLICT ?
//    @ResponseBody
//    @ExceptionHandler(UniqueConstraintViolationException.class)
//    public ResponseEntity<ErrorResponse> uniqueConstraintViolationHandler(
//            UniqueConstraintViolationException e, WebRequest request) {
//        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), request.getDescription(false),
//                HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
}
