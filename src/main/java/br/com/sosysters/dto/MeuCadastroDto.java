package br.com.sosysters.dto;

import java.time.LocalDate;

public class MeuCadastroDto {

    private String nomeUsuaria;
    private String sobrenomeUsuaria;
    private String nomeSocialUsuaria;
    private LocalDate dtNascimentoUsuaria;
    private String rgUsuaria;
    private String cpfUsuaria;
    private String emailUsuaria;
    private String telefoneCompleto;
    private String cep;
    private String logradouro;
    private String numeroResidencia;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private Long etniaUsuaria;
    private Long generoUsuaria;

    public MeuCadastroDto() {
    }

    public String getNomeUsuaria() {
        return nomeUsuaria;
    }

    public void setNomeUsuaria(String nomeUsuaria) {
        this.nomeUsuaria = nomeUsuaria;
    }

    public String getSobrenomeUsuaria() {
        return sobrenomeUsuaria;
    }

    public void setSobrenomeUsuaria(String sobrenomeUsuaria) {
        this.sobrenomeUsuaria = sobrenomeUsuaria;
    }

    public String getNomeSocialUsuaria() {
        return nomeSocialUsuaria;
    }

    public void setNomeSocialUsuaria(String nomeSocialUsuaria) {
        this.nomeSocialUsuaria = nomeSocialUsuaria;
    }

    public LocalDate getDtNascimentoUsuaria() {
        return dtNascimentoUsuaria;
    }

    public void setDtNascimentoUsuaria(LocalDate dtNascimentoUsuaria) {
        this.dtNascimentoUsuaria = dtNascimentoUsuaria;
    }

    public String getRgUsuaria() {
        return rgUsuaria;
    }

    public void setRgUsuaria(String rgUsuaria) {
        this.rgUsuaria = rgUsuaria;
    }

    public String getCpfUsuaria() {
        return cpfUsuaria;
    }

    public void setCpfUsuaria(String cpfUsuaria) {
        this.cpfUsuaria = cpfUsuaria;
    }

    public String getEmailUsuaria() {
        return emailUsuaria;
    }

    public void setEmailUsuaria(String emailUsuaria) {
        this.emailUsuaria = emailUsuaria;
    }

    public String getTelefoneCompleto() {
        return telefoneCompleto;
    }

    public void setTelefoneCompleto(String telefoneCompleto) {
        this.telefoneCompleto = telefoneCompleto;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumeroResidencia() {
        return numeroResidencia;
    }

    public void setNumeroResidencia(String numeroResidencia) {
        this.numeroResidencia = numeroResidencia;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getEtniaUsuaria() {
        return etniaUsuaria;
    }

    public void setEtniaUsuaria(Long etniaUsuaria) {
        this.etniaUsuaria = etniaUsuaria;
    }

    public Long getGeneroUsuaria() {
        return generoUsuaria;
    }

    public void setGeneroUsuaria(Long generoUsuaria) {
        this.generoUsuaria = generoUsuaria;
    }
}

