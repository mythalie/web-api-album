package br.com.iteris.universidade.olamundo.repository;

import br.com.iteris.universidade.olamundo.domain.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacoesRepository extends JpaRepository<Avaliacao, Integer> {
}
