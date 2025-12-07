package br.dev.hygino.config;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.dev.hygino.services.exceptions.BookLoanNotFoundException;
import br.dev.hygino.services.exceptions.BorrowBookException;
import br.dev.hygino.services.exceptions.ErrorResponse;
import br.dev.hygino.services.exceptions.UserHasBookLoanException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserHasBookLoanException.class)
    public ResponseEntity<ErrorResponse> handleUserLoan(UserHasBookLoanException ex, WebRequest request) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(BorrowBookException.class)
    public ResponseEntity<ErrorResponse> handleBookLoan(BorrowBookException ex, WebRequest request) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(BookLoanNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFound(BookLoanNotFoundException ex, WebRequest request) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
