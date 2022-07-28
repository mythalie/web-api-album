package br.com.iteris.universidade.olamundo.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AvaliacaoCreateRequest {
    @NotNull(message = "Id do Album deve ser definido")
    private Integer idAlbum;

    @NotNull(message = "Nota deve ser definido")
    private Integer nota;

    private String comentario;
}
