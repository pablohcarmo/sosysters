package br.com.sosysters.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sosysters.entities.Logradouro;

public interface LogradouroRepository extends JpaRepository<Logradouro, Long> {
    Optional<Logradouro> findFirstByLogradouroIgnoreCaseOrderByIdLogradouroAsc(String logradouro);
}

