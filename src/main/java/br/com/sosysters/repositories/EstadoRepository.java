package br.com.sosysters.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sosysters.entities.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Optional<Estado> findFirstByUfEstadoIgnoreCaseOrderByIdEstadoAsc(String ufEstado);

    Optional<Estado> findFirstByEstadoIgnoreCaseOrderByIdEstadoAsc(String estado);
}

