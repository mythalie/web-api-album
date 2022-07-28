package br.com.iteris.universidade.olamundo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AlbumInvalidoException extends RuntimeException {
    public AlbumInvalidoException(String message) {
        super(message);
    }
}
