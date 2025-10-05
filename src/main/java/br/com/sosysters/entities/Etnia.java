package br.com.sosysters.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "Etnias")
public class Etnia {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_etnia")
	private Long idEtnia;

	@Column (name = "etnia")
	private String etnia;

	public Etnia() {

	}

	public Etnia(Long idUsuaria, String nomeUsuaria) {
		this.idEtnia = idUsuaria;
		this.etnia = nomeUsuaria;
	}

	public Long getIdEtnia() {
		return idEtnia;
	}

	public void setIdEtnia(Long idEtnia) {
		this.idEtnia = idEtnia;
	}

	public String getEtnia() {
		return etnia;
	}

	public void setEtnia(String etnia) {
		this.etnia = etnia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEtnia, etnia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etnia other = (Etnia) obj;
		return Objects.equals(idEtnia, other.idEtnia) && Objects.equals(etnia, other.etnia);
	}
}
