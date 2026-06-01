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
@Table(name = "Celulares")
public class Celular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_celular")
    private Long idCelular;

    @Column(name = "celular")
    private Integer celular;

    @Column(name = "ddd")
    private Integer ddd;

    @ManyToOne
    @JoinColumn(name = "id_usuaria")
    private Usuaria usuariaCelular;

    public Celular() {
    }

    public Celular(Long idCelular, Integer celular, Integer ddd, Usuaria usuariaCelular) {
        this.idCelular = idCelular;
        this.celular = celular;
        this.ddd = ddd;
        this.usuariaCelular = usuariaCelular;
    }

    public Long getIdCelular() {
        return idCelular;
    }

    public void setIdCelular(Long idCelular) {
        this.idCelular = idCelular;
    }

    public Integer getCelular() {
        return celular;
    }

    public void setCelular(Integer celular) {
        this.celular = celular;
    }

    public Integer getDdd() {
        return ddd;
    }

    public void setDdd(Integer ddd) {
        this.ddd = ddd;
    }

    public Usuaria getUsuariaCelular() {
        return usuariaCelular;
    }

    public void setUsuariaCelular(Usuaria usuariaCelular) {
        this.usuariaCelular = usuariaCelular;
    }

    @Override
    public int hashCode() {
        return Objects.hash(celular, ddd, idCelular, usuariaCelular);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Celular other = (Celular) obj;
        return Objects.equals(celular, other.celular)
                && Objects.equals(ddd, other.ddd)
                && Objects.equals(idCelular, other.idCelular)
                && Objects.equals(usuariaCelular, other.usuariaCelular);
    }
}

