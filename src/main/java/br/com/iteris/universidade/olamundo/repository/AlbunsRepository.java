package br.com.iteris.universidade.olamundo.repository;

import br.com.iteris.universidade.olamundo.domain.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbunsRepository extends JpaRepository<Album, Integer> {

    /**
     * Consulta album que contenha o texto passado como parametro no nome:
     * @implNote  select * from album where nome like '%nome%'
     * @param nome parâmetro do filtro
     * @return album que contenha o nome em seu conteúdo
     */
    Optional<Album> findByNomeContaining(String nome);

    /**
     * SQL PURO
     * Filtra por nome e artista
     * @param nome parâmetro opcional(:nome IS NULL OR nome = :nome) nome do filtro
     * @param artista parâmetro opcional(:artista IS NULL OR artista = :artista) nome do filtro
     * @return albuns que correspondam ao filtro
     */
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM album WHERE (:nome IS NULL OR nome = :nome) AND (:artista IS NULL OR artista = :artista)"
    )
    List<Album> listarComFiltroNativo(@Param("nome") String nome, @Param("artista") String artista);

    /**
     * SQL PURO
     * Filtra por nome e artista
     * @param nome parâmetro opcional(:nome IS NULL OR nome = :nome) nome do filtro
     * @param artista parâmetro opcional(:artista IS NULL OR artista = :artista) nome do filtro
     * @return albuns que correspondam ao filtro
     */
    @Query("SELECT a FROM Album a WHERE (:nome IS NULL OR a.nome = :nome) AND (:artista IS NULL OR a.artista = :artista)")
    List<Album> listarComFiltro(@Param("nome") String nome, @Param("artista") String artista);

}
