package br.com.sosysters.dto;

import java.time.LocalDate;

public class NovaUsuariaDto {
	private String nomeUsuaria;
	private String sobrenomeUsuaria;
	private String nomeSocialUsuaria;
	private LocalDate dtNascimentoUsuaria;
	private String rgUsuaria;
	private String cpfUsuaria;
	private String emailUsuaria;
	private String senhaUsuaria;
	private Long etniaUsuaria;
	private Long generoUsuaria;
	// telefone/endereço
	private String dddCelular;
	private String celular;
	private String cep;
	private String logradouro;
	private String numeroResidencia;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;

	public NovaUsuariaDto() {
	}

	public NovaUsuariaDto(String nomeUsuaria, String sobrenomeUsuaria, String nomeSocialUsuaria, LocalDate dtNascimentoUsuaria,
			String rgUsuaria, String cpfUsuaria, String emailUsuaria, String senhaUsuaria, Long etniaUsuaria, Long generoUsuaria) {
		this.nomeUsuaria = nomeUsuaria;
		this.sobrenomeUsuaria = sobrenomeUsuaria;
		this.nomeSocialUsuaria = nomeSocialUsuaria;
		this.dtNascimentoUsuaria = dtNascimentoUsuaria;
		this.rgUsuaria = rgUsuaria;
		this.cpfUsuaria = cpfUsuaria;
		this.emailUsuaria = emailUsuaria;
		this.senhaUsuaria = senhaUsuaria;
		this.etniaUsuaria = etniaUsuaria;
		this.generoUsuaria = generoUsuaria;
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

	public String getSenhaUsuaria() {
		return senhaUsuaria;
	}

	public void setSenhaUsuaria(String senhaUsuaria) {
		this.senhaUsuaria = senhaUsuaria;
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

	public String getDddCelular() {
		return dddCelular;
	}

	public void setDddCelular(String dddCelular) {
		this.dddCelular = dddCelular;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
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
}