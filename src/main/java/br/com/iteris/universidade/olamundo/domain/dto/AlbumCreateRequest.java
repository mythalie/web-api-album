package br.com.iteris.universidade.olamundo.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AlbumCreateRequest {
    @NotEmpty(message = "Nome deve ser definido")
    private String nome;

    @NotEmpty(message = "Artista deve ser definido")
    private String artista;

    @NotNull(message = "Ano de lançamento deve ser definido")
    private Integer anoLancamento;
}
