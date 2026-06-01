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
@Table(name = "Enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long idEndereco;

    @Column(name = "num_residencia")
    private String numResidencia;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "cep")
    private String cep;

    @ManyToOne
    @JoinColumn(name = "id_bairro")
    private Bairro bairroEndereco;

    @ManyToOne
    @JoinColumn(name = "id_logradouro")
    private Logradouro logradouroEndereco;

    public Endereco() {
    }

    public Endereco(Long idEndereco, String numResidencia, String complemento, String cep, Bairro bairroEndereco, Logradouro logradouroEndereco) {
        this.idEndereco = idEndereco;
        this.numResidencia = numResidencia;
        this.complemento = complemento;
        this.cep = cep;
        this.bairroEndereco = bairroEndereco;
        this.logradouroEndereco = logradouroEndereco;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getNumResidencia() {
        return numResidencia;
    }

    public void setNumResidencia(String numResidencia) {
        this.numResidencia = numResidencia;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Bairro getBairroEndereco() {
        return bairroEndereco;
    }

    public void setBairroEndereco(Bairro bairroEndereco) {
        this.bairroEndereco = bairroEndereco;
    }

    public Logradouro getLogradouroEndereco() {
        return logradouroEndereco;
    }

    public void setLogradouroEndereco(Logradouro logradouroEndereco) {
        this.logradouroEndereco = logradouroEndereco;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bairroEndereco, cep, complemento, idEndereco, logradouroEndereco, numResidencia);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Endereco other = (Endereco) obj;
        return Objects.equals(bairroEndereco, other.bairroEndereco)
                && Objects.equals(cep, other.cep)
                && Objects.equals(complemento, other.complemento)
                && Objects.equals(idEndereco, other.idEndereco)
                && Objects.equals(logradouroEndereco, other.logradouroEndereco)
                && Objects.equals(numResidencia, other.numResidencia);
    }
}

