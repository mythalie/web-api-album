package br.com.iteris.universidade.olamundo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AlbumResponse {
    private int idAlbum;
    private String nome;
    private String artista;
    private int anoLancamento;
    private String avaliacaoMedia;
    private List<AvaliacaoResponse> avaliacoes;
}
