package br.com.sosysters.repositories;

import br.com.sosysters.entities.UsuariaConfirmacaoToken;
import br.com.sosysters.entities.Usuaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuariaConfirmacaoTokenRepository extends JpaRepository<UsuariaConfirmacaoToken, Long> {

    Optional<UsuariaConfirmacaoToken> findByUuid(UUID uuid);

    Optional<UsuariaConfirmacaoToken> findByUsuaria(Usuaria usuaria);
}