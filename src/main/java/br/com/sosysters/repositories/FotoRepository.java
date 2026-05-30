package br.com.sosysters.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sosysters.entities.Foto;
import br.com.sosysters.entities.Usuaria;

public interface FotoRepository extends JpaRepository<Foto, Long> {

    Optional<Foto> findFirstByUsuariaOrderByIdFotoDesc(Usuaria usuaria);
}
