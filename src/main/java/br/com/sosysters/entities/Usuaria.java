package br.com.sosysters.entities;

import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	@Column (name = "sobrenome_social")
	private String sobrenomeSocialUsuaria;

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

	@Column (name = "id_etnia")
	private int etniaUsuaria;

	@Column (name = "id_genero")
	private int generoUsuaria;

	public Usuaria() {

	}

	public Usuaria(Long idUsuaria, String nomeUsuaria, String sobrenomeUsuaria, String nomeSocialUsuaria,
			String sobrenomeSocialUsuaria, Date dtNascimentoUsuaria, String rgUsuaria, String cpfUsuaria,
			String emailUsuaria, String senhaUsuaria, int etniaUsuaria, int generoUsuaria) {
		super();
		this.idUsuaria = idUsuaria;
		this.nomeUsuaria = nomeUsuaria;
		this.sobrenomeUsuaria = sobrenomeUsuaria;
		this.nomeSocialUsuaria = nomeSocialUsuaria;
		this.sobrenomeSocialUsuaria = sobrenomeSocialUsuaria;
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

	public String getSobrenomeSocialUsuaria() {
		return sobrenomeSocialUsuaria;
	}

	public void setSobrenomeSocialUsuaria(String sobrenomeSocialUsuaria) {
		this.sobrenomeSocialUsuaria = sobrenomeSocialUsuaria;
	}

	public Date getDtNasciUsuaria() {
		return dtNascimentoUsuaria;
	}

	public void setDtNasciUsuaria(Date dtNasciUsuaria) {
		this.dtNascimentoUsuaria = dtNasciUsuaria;
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

	public int getEtniaUsuaria() {
		return etniaUsuaria;
	}

	public void setEtniaUsuaria(int etniaUsuaria) {
		this.etniaUsuaria = etniaUsuaria;
	}

	public int getGeneroUsuaria() {
		return generoUsuaria;
	}

	public void setGeneroUsuaria(int generoUsuaria) {
		this.generoUsuaria = generoUsuaria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpfUsuaria, dtNascimentoUsuaria, emailUsuaria, etniaUsuaria, generoUsuaria, idUsuaria,
				nomeSocialUsuaria, nomeUsuaria, rgUsuaria, senhaUsuaria, sobrenomeSocialUsuaria, sobrenomeUsuaria);
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
				&& Objects.equals(sobrenomeSocialUsuaria, other.sobrenomeSocialUsuaria)
				&& Objects.equals(sobrenomeUsuaria, other.sobrenomeUsuaria);
	}
}
