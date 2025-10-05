package br.com.sosysters.dto;

import java.sql.Date;

public class NovaUsuariaDto {
	private String nomeUsuaria;
	private String sobrenomeUsuaria;
	private String nomeSocialUsuaria;
	private Date dtNascimentoUsuaria;
	private String rgUsuaria;
	private String cpfUsuaria;
	private String emailUsuaria;
	private String senhaUsuaria;
	private Long etniaUsuaria;
	private Long generoUsuaria;

	public NovaUsuariaDto() {
	}

	public NovaUsuariaDto(String nomeUsuaria, String sobrenomeUsuaria, String nomeSocialUsuaria, Date dtNascimentoUsuaria,
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

	public Date getDtNascimentoUsuaria() {
		return dtNascimentoUsuaria;
	}

	public void setDtNascimentoUsuaria(Date dtNascimentoUsuaria) {
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
}