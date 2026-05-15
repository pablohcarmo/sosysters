package br.com.sosysters.repositories;

import br.com.sosysters.entities.UsuariaConfirmacaoToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuariaConfirmacaoTokenRepository extends JpaRepository<UsuariaConfirmacaoToken, Long> {

    public Optional<UsuariaConfirmacaoToken> findByUuid(UUID uuid);
}