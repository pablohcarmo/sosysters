package br.com.sosysters.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "UsuariaConfirmacaoToken")
public class UsuariaConfirmacaoToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid", nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "data_expiracao", nullable = false)
    private Instant dataExpiracao;

    @ManyToOne
    @JoinColumn(name = "id_usuaria", referencedColumnName = "id_usuaria", nullable = false, unique = true)
    private Usuaria usuaria;

    public void setUsuaria(Usuaria usuaria) {
        this.usuaria = usuaria;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Instant getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Instant dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public Usuaria getUsuaria() {
        return usuaria;
    }
}
