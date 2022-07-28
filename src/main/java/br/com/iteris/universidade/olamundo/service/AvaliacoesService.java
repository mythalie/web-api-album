package br.com.iteris.universidade.olamundo.service;

import br.com.iteris.universidade.olamundo.domain.dto.AvaliacaoCreateRequest;
import br.com.iteris.universidade.olamundo.domain.dto.AvaliacaoResponse;
import br.com.iteris.universidade.olamundo.domain.entity.Avaliacao;
import br.com.iteris.universidade.olamundo.exception.AlbumNaoEncontradoException;
import br.com.iteris.universidade.olamundo.exception.AvaliacaoInvalidaException;
import br.com.iteris.universidade.olamundo.repository.AlbunsRepository;
import br.com.iteris.universidade.olamundo.repository.AvaliacoesRepository;
import org.springframework.stereotype.Service;

@Service
public class AvaliacoesService {

    private final AvaliacoesRepository avaliacoesRepository;

    private final AlbunsRepository albunsRepository;

    public AvaliacoesService(final AvaliacoesRepository avaliacoesRepository, final AlbunsRepository albunsRepository) {
        this.avaliacoesRepository = avaliacoesRepository;
        this.albunsRepository = albunsRepository;
    }

    public AvaliacaoResponse avaliar(AvaliacaoCreateRequest createRequest) {
        var albumEncontrado = albunsRepository.findById(createRequest.getIdAlbum());

        if (albumEncontrado.isEmpty()) {
            throw new AlbumNaoEncontradoException();
        }

        if (createRequest.getNota() > 5 || createRequest.getNota() < 1) {
            throw new AvaliacaoInvalidaException();
        }

        var avaliacao = new Avaliacao();
        avaliacao.setAlbum(albumEncontrado.get());
        avaliacao.setNota(createRequest.getNota());
        avaliacao.setComentario(createRequest.getComentario());

        var avaliacaoSalva = avaliacoesRepository.save(avaliacao);

        return new AvaliacaoResponse(
                avaliacaoSalva.getIdAvaliacao(),
                avaliacaoSalva.getAlbum().getIdAlbum(),
                avaliacaoSalva.getNota(),
                avaliacaoSalva.getComentario()
        );
    }
}