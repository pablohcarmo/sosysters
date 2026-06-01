package br.com.sosysters.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sosysters.entities.Bairro;
import br.com.sosysters.entities.Cidade;

public interface BairroRepository extends JpaRepository<Bairro, Long> {
    Optional<Bairro> findFirstByBairroIgnoreCaseAndCidadeBairroOrderByIdBairroAsc(String bairro, Cidade cidadeBairro);
}

