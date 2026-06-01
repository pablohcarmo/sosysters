package br.com.sosysters.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Logradouros")
public class Logradouro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_logradouro")
    private Long idLogradouro;

    @Column(name = "logradouro")
    private String logradouro;

    public Logradouro() {
    }

    public Logradouro(Long idLogradouro, String logradouro) {
        this.idLogradouro = idLogradouro;
        this.logradouro = logradouro;
    }

    public Long getIdLogradouro() {
        return idLogradouro;
    }

    public void setIdLogradouro(Long idLogradouro) {
        this.idLogradouro = idLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLogradouro, logradouro);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Logradouro other = (Logradouro) obj;
        return Objects.equals(idLogradouro, other.idLogradouro)
                && Objects.equals(logradouro, other.logradouro);
    }
}

