package br.com.sosysters.entities;

import java.sql.Date;
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
@Table (name = "Usuarias")
public class Usuaria {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_usuaria")
	private Long idUsuaria;

	@Column (name = "nome")
	private String nomeUsuaria;

	@Column (name = "sobrenome")
	private String sobrenomeUsuaria;

	@Column (name = "nome_social")
	private String nomeSocialUsuaria;

	@Column (name = "dt_nascimento")
	private Date dtNascimentoUsuaria;

	@Column (name = "rg")
	private String rgUsuaria;

	@Column (name = "cpf")
	private String cpfUsuaria;

	@Column (name = "email")
	private String emailUsuaria;

	@Column (name = "senha")
	private String senhaUsuaria;

	@ManyToOne
	@JoinColumn (name = "id_etnia")
	private Etnia etniaUsuaria;

	@ManyToOne
	@JoinColumn (name = "id_genero")
	private Genero generoUsuaria;

	public Usuaria() {

	}

	public Usuaria(Long idUsuaria, String nomeUsuaria, String sobrenomeUsuaria, String nomeSocialUsuaria,
			Date dtNascimentoUsuaria, String rgUsuaria, String cpfUsuaria,
			String emailUsuaria, String senhaUsuaria, Etnia etniaUsuaria, Genero generoUsuaria) {
		this.idUsuaria = idUsuaria;
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

	public Long getIdUsuaria() {
		return idUsuaria;
	}

	public void setId(Long idUsuaria) {
		this.idUsuaria = idUsuaria;
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

	public String getCpfUsuaria() {
		return cpfUsuaria;
	}

	public void setCpfUsuaria(String cpfUsuaria) {
		this.cpfUsuaria = cpfUsuaria;
	}

	public String getRgUsuaria() {
		return rgUsuaria;
	}

	public void setRgUsuaria(String rgUsuaria) {
		this.rgUsuaria = rgUsuaria;
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

	public Etnia getEtniaUsuaria() {
	    return etniaUsuaria;
	}

	public void setEtniaUsuaria(Etnia etniaUsuaria) {
	    this.etniaUsuaria = etniaUsuaria;
	}

	public Genero getGeneroUsuaria() {
	    return generoUsuaria;
	}

	public void setGeneroUsuaria(Genero generoUsuaria) {
	    this.generoUsuaria = generoUsuaria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpfUsuaria, dtNascimentoUsuaria, emailUsuaria, etniaUsuaria, generoUsuaria, idUsuaria,
				nomeSocialUsuaria, nomeUsuaria, rgUsuaria, senhaUsuaria, sobrenomeUsuaria);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuaria other = (Usuaria) obj;
		return Objects.equals(cpfUsuaria, other.cpfUsuaria)
				&& Objects.equals(dtNascimentoUsuaria, other.dtNascimentoUsuaria)
				&& Objects.equals(emailUsuaria, other.emailUsuaria) && etniaUsuaria == other.etniaUsuaria
				&& generoUsuaria == other.generoUsuaria && Objects.equals(idUsuaria, other.idUsuaria)
				&& Objects.equals(nomeSocialUsuaria, other.nomeSocialUsuaria)
				&& Objects.equals(nomeUsuaria, other.nomeUsuaria) && Objects.equals(rgUsuaria, other.rgUsuaria)
				&& Objects.equals(senhaUsuaria, other.senhaUsuaria)
				&& Objects.equals(sobrenomeUsuaria, other.sobrenomeUsuaria);
	}
}
