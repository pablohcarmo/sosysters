package br.com.sosysters.pojo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarias")
public class Usuaria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuaria")
	private Long id;

	@Column(name = "nome", nullable = false)
	@JsonProperty("nomeUsuaria")
	private String nomeUsuaria;

	@Column(name = "sobrenome", nullable = false)
	@JsonProperty("sobrenomeUsuaria")
	private String sobrenomeUsuaria;

	@Column(name = "dt_nascimento", nullable = false)
	@JsonProperty("dataNascimento")
	private Date dataNascimento;

	@Column(name = "rg", nullable = false)
	@JsonProperty("rg")
	private String rg;

	@Column(name = "cpf", nullable = false)
	@JsonProperty("cpf")
	private String cpf;

	@Column(name = "email", nullable = false)
	@JsonProperty("email")
	private String email;

	@Column(name = "senha", nullable = false)
	@JsonProperty("senha")
	private String senha;

	@Column(name = "id_etnia", nullable = false)
	@JsonProperty("id_etnia")
	private int etnia;

	@Column(name = "id_genero", nullable = false)
	@JsonProperty("id_genero")
	private int genero;

	//private String reporte;

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
