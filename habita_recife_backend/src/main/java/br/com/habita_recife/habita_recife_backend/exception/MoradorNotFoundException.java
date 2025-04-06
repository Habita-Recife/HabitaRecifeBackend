package br.com.habita_recife.habita_recife_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MoradorNotFoundException extends RuntimeException {

    public MoradorNotFoundException(String message) {
        super(message);
    }

    public MoradorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}