package br.com.sosysters.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Fotos")
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_foto")
    private Long idFoto;

    @Column(name = "img_reconhecimento_facial", columnDefinition = "BYTEA")
    private byte[] imgReconhecimentoFacial;

    @Column(name = "img_perfil", columnDefinition = "BYTEA")
    private byte[] imgPerfil;

    @Column(name = "selfie_verificacao", columnDefinition = "BYTEA")
    private byte[] selfieVerificacao;

    @Column(name = "documento_verificacao", columnDefinition = "BYTEA")
    private byte[] documentoVerificacao;

    @Column(name = "identidade_verificada", nullable = false)
    private boolean identidadeVerificada;

    @Column(name = "data_verificacao")
    private LocalDateTime dataVerificacao;

    @ManyToOne
    @JoinColumn(name = "id_usuaria")
    private Usuaria usuaria;

    public Long getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Long idFoto) {
        this.idFoto = idFoto;
    }

    public byte[] getImgReconhecimentoFacial() {
        return imgReconhecimentoFacial;
    }

    public void setImgReconhecimentoFacial(byte[] imgReconhecimentoFacial) {
        this.imgReconhecimentoFacial = imgReconhecimentoFacial;
    }

    public byte[] getImgPerfil() {
        return imgPerfil;
    }

    public void setImgPerfil(byte[] imgPerfil) {
        this.imgPerfil = imgPerfil;
    }

    public Usuaria getUsuaria() {
        return usuaria;
    }

    public void setUsuaria(Usuaria usuaria) {
        this.usuaria = usuaria;
    }

    public byte[] getSelfieVerificacao() {
        return selfieVerificacao;
    }

    public void setSelfieVerificacao(byte[] selfieVerificacao) {
        this.selfieVerificacao = selfieVerificacao;
    }

    public byte[] getDocumentoVerificacao() {
        return documentoVerificacao;
    }

    public void setDocumentoVerificacao(byte[] documentoVerificacao) {
        this.documentoVerificacao = documentoVerificacao;
    }

    public boolean isIdentidadeVerificada() {
        return identidadeVerificada;
    }

    public void setIdentidadeVerificada(boolean identidadeVerificada) {
        this.identidadeVerificada = identidadeVerificada;
    }

    public LocalDateTime getDataVerificacao() {
        return dataVerificacao;
    }

    public void setDataVerificacao(LocalDateTime dataVerificacao) {
        this.dataVerificacao = dataVerificacao;
    }
}
