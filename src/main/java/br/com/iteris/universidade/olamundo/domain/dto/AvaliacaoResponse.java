package br.com.iteris.universidade.olamundo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvaliacaoResponse {
    private int idAvaliacao;
    private int idAlbum;
    private int nota;
    private String comentario;
}
