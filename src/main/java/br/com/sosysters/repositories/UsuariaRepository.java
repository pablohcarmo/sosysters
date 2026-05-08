package br.com.sosysters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sosysters.entities.Usuaria;

import java.util.Optional;

public interface UsuariaRepository extends JpaRepository<Usuaria, Long>{

    Optional<Usuaria> findByEmailUsuariaIgnoreCase(String emailUsuaria);
}