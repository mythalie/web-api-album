package br.com.iteris.universidade.olamundo.service;

import br.com.iteris.universidade.olamundo.domain.dto.*;
import br.com.iteris.universidade.olamundo.domain.entity.Album;
import br.com.iteris.universidade.olamundo.domain.entity.Avaliacao;
import br.com.iteris.universidade.olamundo.exception.AlbumInvalidoException;
import br.com.iteris.universidade.olamundo.exception.AlbumNaoEncontradoException;
import br.com.iteris.universidade.olamundo.repository.AlbunsRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbunsService {

    private final AlbunsRepository repository;

    public AlbunsService(final AlbunsRepository repository) {
        this.repository = repository;
        }

    public AlbumResponse criarAlbum(AlbumCreateRequest albumCreateRequest) {
        // Regra
        // Somente albums lançados entre 1950 e o ano atual
        if(albumCreateRequest.getAnoLancamento() < 1950 || albumCreateRequest.getAnoLancamento() > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new AlbumInvalidoException("Album não se encaixa nas regras de anos: maior que 1950 e menor que hoje");
        }
        var novoAlbum = new Album();
        novoAlbum.setNome(albumCreateRequest.getNome());
        novoAlbum.setArtista(albumCreateRequest.getArtista());
        novoAlbum.setAnoLancamento(albumCreateRequest.getAnoLancamento());

        var albumSalvo = repository.save(novoAlbum);

        return new AlbumResponse(
                albumSalvo.getIdAlbum(),
                albumSalvo.getNome(),
                albumSalvo.getArtista(),
                albumSalvo.getAnoLancamento(),
                recuperaAvaliacaoMedia(albumSalvo.getAvaliacoes()),
                recuperaAvaliacao(albumSalvo.getAvaliacoes())
        );
    }

    public List<AlbumResponse> listar(AlbumFilterRequest filter) {
        var resultado = repository.listarComFiltroNativo(filter.getNome(), filter.getArtista());

        return resultado.stream().map(album -> new AlbumResponse(
                album.getIdAlbum(),
                album.getNome(),
                album.getArtista(),
                album.getAnoLancamento(),
                recuperaAvaliacaoMedia(album.getAvaliacoes()),
                recuperaAvaliacao(album.getAvaliacoes())
        )).collect(Collectors.toList());

    }

    public AlbumResponse buscarPorId(Integer idAlbum) {
        var albumEncontrado = repository.findById(idAlbum);

        if (albumEncontrado.isEmpty()) {
            throw new AlbumNaoEncontradoException();
        }

        var albumSalvo = albumEncontrado.get();

        return new AlbumResponse(
                albumSalvo.getIdAlbum(),
                albumSalvo.getNome(),
                albumSalvo.getArtista(),
                albumSalvo.getAnoLancamento(),
                recuperaAvaliacaoMedia(albumSalvo.getAvaliacoes()),
                recuperaAvaliacao(albumSalvo.getAvaliacoes())
        );
    }

    public AlbumResponse buscarPorNome(String nome) {
        var albumEncontrado = repository.findByNomeContaining(nome);

        if (albumEncontrado.isEmpty()) {
            throw new AlbumNaoEncontradoException();
        }
        var albumSalvo = albumEncontrado.get();

        return new AlbumResponse(
                albumSalvo.getIdAlbum(),
                albumSalvo.getNome(),
                albumSalvo.getArtista(),
                albumSalvo.getAnoLancamento(),
                recuperaAvaliacaoMedia(albumSalvo.getAvaliacoes()),
                recuperaAvaliacao(albumSalvo.getAvaliacoes())
        );
    }

    public AlbumResponse atualizarAlbum(Integer idAlbum, AlbumUpdateRequest albumUpdateRequest) {
        var albumEncontrado = repository.findById(idAlbum);

        if (albumEncontrado.isEmpty()) {
            throw new AlbumNaoEncontradoException();
        }

        var album = albumEncontrado.get();
        album.setArtista(albumUpdateRequest.getArtista());

        var albumSalvo = repository.save(album);

        return new AlbumResponse(
                albumSalvo.getIdAlbum(),
                albumSalvo.getNome(),
                albumSalvo.getArtista(),
                albumSalvo.getAnoLancamento(),
                recuperaAvaliacaoMedia(album.getAvaliacoes()),
                recuperaAvaliacao(albumSalvo.getAvaliacoes())
        );
    }

    public AlbumResponse deletarAlbum(Integer idAlbum) {
        var albumEncontrado = repository.findById(idAlbum);

        if (albumEncontrado.isEmpty()) {
            throw new AlbumNaoEncontradoException();
        }

        var album = albumEncontrado.get();
        repository.delete(album);

        return new AlbumResponse(
                album.getIdAlbum(),
                album.getNome(),
                album.getArtista(),
                album.getAnoLancamento(),
                recuperaAvaliacaoMedia(album.getAvaliacoes()),
                recuperaAvaliacao(album.getAvaliacoes())
        );
    }

    private List<AvaliacaoResponse> recuperaAvaliacao(List<Avaliacao> avaliacaos) {
        return avaliacaos.stream().map(avaliacao -> new AvaliacaoResponse(
                avaliacao.getIdAvaliacao(),
                avaliacao.getAlbum().getIdAlbum(),
                avaliacao.getNota(),
                avaliacao.getComentario()
        )).collect(Collectors.toList());
    }

    private String recuperaAvaliacaoMedia(List<Avaliacao> avaliacaos) {
        var resultado = avaliacaos.stream().mapToDouble(Avaliacao::getNota).average();

        if (resultado.isEmpty())
            return null;

        return String.format("%.2f", resultado.getAsDouble());
    }
}
