package br.com.sosysters.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Estados")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Long idEstado;

    @Column(name = "uf_estado")
    private String ufEstado;

    @Column(name = "estado")
    private String estado;

    public Estado() {
    }

    public Estado(Long idEstado, String ufEstado, String estado) {
        this.idEstado = idEstado;
        this.ufEstado = ufEstado;
        this.estado = estado;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public String getUfEstado() {
        return ufEstado;
    }

    public void setUfEstado(String ufEstado) {
        this.ufEstado = ufEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEstado, ufEstado, estado);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Estado other = (Estado) obj;
        return Objects.equals(idEstado, other.idEstado)
                && Objects.equals(ufEstado, other.ufEstado)
                && Objects.equals(estado, other.estado);
    }
}

