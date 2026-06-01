package br.com.sosysters.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sosysters.entities.Cidade;
import br.com.sosysters.entities.Estado;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    Optional<Cidade> findFirstByCidadeIgnoreCaseAndEstadoCidadeOrderByIdCidadeAsc(String cidade, Estado estadoCidade);
}

