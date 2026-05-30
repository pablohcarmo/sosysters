package br.com.sosysters.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Profissoes")
public class Profissao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_profissao")
	private Long idProfissao;

	@Column(name = "profissao", nullable = false)
	private String nomeProfissao;

	public Profissao() {
	}

	public Profissao(String nomeProfissao) {
		this.nomeProfissao = nomeProfissao;
	}

	public Long getIdProfissao() {
		return idProfissao;
	}

	public void setIdProfissao(Long idProfissao) {
		this.idProfissao = idProfissao;
	}

	public String getNomeProfissao() {
		return nomeProfissao;
	}

	public void setNomeProfissao(String nomeProfissao) {
		this.nomeProfissao = nomeProfissao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProfissao, nomeProfissao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profissao other = (Profissao) obj;
		return Objects.equals(idProfissao, other.idProfissao)
				&& Objects.equals(nomeProfissao, other.nomeProfissao);
	}

	@Override
	public String toString() {
		return "Profissao [idProfissao=" + idProfissao + ", nomeProfissao=" + nomeProfissao + "]";
	}
}

