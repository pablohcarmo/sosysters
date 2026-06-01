package br.com.sosysters.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cidades")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cidade")
    private Long idCidade;

    @Column(name = "cidade")
    private String cidade;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado estadoCidade;

    public Cidade() {
    }

    public Cidade(Long idCidade, String cidade, Estado estadoCidade) {
        this.idCidade = idCidade;
        this.cidade = cidade;
        this.estadoCidade = estadoCidade;
    }

    public Long getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Long idCidade) {
        this.idCidade = idCidade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Estado getEstadoCidade() {
        return estadoCidade;
    }

    public void setEstadoCidade(Estado estadoCidade) {
        this.estadoCidade = estadoCidade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cidade, estadoCidade, idCidade);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cidade other = (Cidade) obj;
        return Objects.equals(cidade, other.cidade)
                && Objects.equals(estadoCidade, other.estadoCidade)
                && Objects.equals(idCidade, other.idCidade);
    }
}

