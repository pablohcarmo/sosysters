package br.com.sosysters.pojo;

import java.sql.Date;

public class Usuaria {
	private String nomeUsuaria;
	private String sobrenomeUsuaria;
	private Date dataNascimento;
	private String rg, cpf;
	private String email, senha;
	private int etnia, genero;
	private String reporte;

	public Usuaria() {

	}

	public Usuaria(String nomeUsuaria, String sobrenomeUsuaria, Date dataNascimento, String rg, String cpf, String email, String senha, int etnia, int genero) {
		super();
		this.nomeUsuaria = nomeUsuaria;
		this.sobrenomeUsuaria = sobrenomeUsuaria;
		this.dataNascimento = dataNascimento;
		this.rg = rg;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.etnia = etnia;
		this.genero = genero;
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getEtnia() {
		return etnia;
	}

	public void setEtnia(int etnia) {
		this.etnia = etnia;
	}

	public int getGenero() {
		return genero;
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}
}
