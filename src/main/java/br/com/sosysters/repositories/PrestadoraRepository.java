package br.com.sosysters.repositories;

import br.com.sosysters.entities.Prestadora;
import br.com.sosysters.entities.Usuaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PrestadoraRepository extends JpaRepository<Prestadora, Long> {
	Optional<Prestadora> findByUsuaria(Usuaria usuaria);
}

