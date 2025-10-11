package com.example.rankify.excepiton;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFound.class)
    private ResponseEntity<String> userNotFoundHandler(UserNotFound userNotFound){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound.getMessage());
    }

    @ExceptionHandler(RankingNotFound.class)
    private ResponseEntity<String> rankingNotFoundHandler(RankingNotFound rankingNotFound){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rankingNotFound.getMessage());
    }
}
