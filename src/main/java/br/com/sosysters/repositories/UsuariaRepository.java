package br.com.sosysters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sosysters.entities.Usuaria;

public interface UsuariaRepository extends JpaRepository<Usuaria, Long>{

}