package br.com.iteris.universidade.olamundo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AlbumNaoEncontradoException extends RuntimeException {
    public AlbumNaoEncontradoException() {
        super("Não foi encontrado Album para a entrada");
    }
}
