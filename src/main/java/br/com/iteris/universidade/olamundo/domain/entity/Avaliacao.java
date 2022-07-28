package br.com.iteris.universidade.olamundo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAvaliacao;

    private int nota;

    private String comentario;

    /**
     * É amplamente recomendado a utilização do fetch type Lazy,
     * pois consultaremos o relacionamente somente em caso de necessidade
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_album")
    private Album album;
}
