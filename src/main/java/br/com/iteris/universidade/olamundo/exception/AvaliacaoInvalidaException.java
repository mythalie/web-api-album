package br.com.iteris.universidade.olamundo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AvaliacaoInvalidaException extends RuntimeException {
    public AvaliacaoInvalidaException() {
        super("Nota deve ser entre 1 e 5");
    }
}
