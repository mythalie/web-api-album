package br.com.iteris.universidade.olamundo.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AlbumUpdateRequest {
    @NotEmpty(message = "Artista é obrigatório")
    private String artista;
}
