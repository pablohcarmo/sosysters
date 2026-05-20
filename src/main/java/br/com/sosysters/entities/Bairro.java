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
@Table(name = "Bairros")
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bairro")
    private Long idBairro;

    @Column(name = "bairro")
    private String bairro;

    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private Cidade cidadeBairro;

    public Bairro() {
    }

    public Bairro(Long idBairro, String bairro, Cidade cidadeBairro) {
        this.idBairro = idBairro;
        this.bairro = bairro;
        this.cidadeBairro = cidadeBairro;
    }

    public Long getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(Long idBairro) {
        this.idBairro = idBairro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Cidade getCidadeBairro() {
        return cidadeBairro;
    }

    public void setCidadeBairro(Cidade cidadeBairro) {
        this.cidadeBairro = cidadeBairro;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bairro, cidadeBairro, idBairro);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bairro other = (Bairro) obj;
        return Objects.equals(bairro, other.bairro)
                && Objects.equals(cidadeBairro, other.cidadeBairro)
                && Objects.equals(idBairro, other.idBairro);
    }
}

