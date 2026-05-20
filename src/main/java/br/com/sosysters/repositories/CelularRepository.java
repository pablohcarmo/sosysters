package br.com.sosysters.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sosysters.entities.Celular;

public interface CelularRepository extends JpaRepository<Celular, Long> {
	Optional<Celular> findFirstByUsuariaCelular_IdUsuaria(Long idUsuaria);
}

