package com.meli.mutant.mutantapp.exception;

import com.meli.mutant.mutantapp.model.error.Error;
import com.meli.mutant.mutantapp.util.Constants;
import com.meli.mutant.mutantapp.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class MutantExceptionHandler {

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity generalError(Exception e) {
        Error error = new Error(Constants.GENERAL_ERROR_CODE,e.getMessage());
        return ResponseUtil.error(error);
    }
}
