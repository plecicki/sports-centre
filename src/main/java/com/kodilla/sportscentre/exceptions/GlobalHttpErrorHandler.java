package com.kodilla.sportscentre.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>("User with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<Object> handleCardNotFoundException(CardNotFoundException exception) {
        return new ResponseEntity<>("Card with given id doesn't exist or is owned by someone else", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<Object> handleInvoiceNotFoundException(InvoiceNotFoundException exception) {
        return new ResponseEntity<>("Invoice with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException exception) {
        return new ResponseEntity<>("Order with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongUsernameException.class)
    public ResponseEntity<Object> handleWrongUsername(WrongUsernameException exception) {
        return new ResponseEntity<>("User with given username doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<Object> handleWrongPassword(WrongPasswordException exception) {
        return new ResponseEntity<>("Given password is incorrect", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LackOfPermissionToCreateAdminAccount.class)
    public ResponseEntity<Object> handleWrongAdminCreateKey(LackOfPermissionToCreateAdminAccount exception) {
        return new ResponseEntity<>("You do not have permission to create admin account", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ThisUsernameIsTaken.class)
    public ResponseEntity<Object> handleWrongAdminCreateKey(ThisUsernameIsTaken exception) {
        return new ResponseEntity<>("This username is already taken, please try another one", HttpStatus.BAD_REQUEST);
    }
}
