package br.com.sosysters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sosysters.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}

