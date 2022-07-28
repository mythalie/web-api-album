package br.com.iteris.universidade.olamundo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idAlbum;

    /**
     * Nome do album
     * @implNote Column por padrão define o length igual a 255
     */
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String artista;

    private int anoLancamento;

    /**
     * É amplamente recomendado a utilização do fetch type Lazy,
     * pois consultaremos o relacionamente somente em caso de necessidade
     */
    @OneToMany(mappedBy = "album", fetch=FetchType.LAZY)
    private List<Avaliacao> avaliacoes;
}
