package br.com.sosysters.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Prestadoras")
public class Prestadora {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_prestadora")
	private Long idPrestadora;

	@ManyToOne
	@JoinColumn(name = "id_usuaria", nullable = false)
	private Usuaria usuaria;

	@ManyToMany
	@JoinTable(
		name = "Prestadoras_Profissoes",
		joinColumns = @JoinColumn(name = "id_prestadora"),
		inverseJoinColumns = @JoinColumn(name = "id_profissao")
	)
	private Set<Profissao> profissoes = new HashSet<>();

	public Prestadora() {
	}

	public Prestadora(Usuaria usuaria) {
		this.usuaria = usuaria;
	}

	public Long getIdPrestadora() {
		return idPrestadora;
	}

	public void setIdPrestadora(Long idPrestadora) {
		this.idPrestadora = idPrestadora;
	}

	public Usuaria getUsuaria() {
		return usuaria;
	}

	public void setUsuaria(Usuaria usuaria) {
		this.usuaria = usuaria;
	}

	public Set<Profissao> getProfissoes() {
		return profissoes;
	}

	public void setProfissoes(Set<Profissao> profissoes) {
		this.profissoes = profissoes;
	}

	public void adicionarProfissao(Profissao profissao) {
		this.profissoes.add(profissao);
	}

	public void removerProfissao(Profissao profissao) {
		this.profissoes.remove(profissao);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPrestadora);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prestadora other = (Prestadora) obj;
		return Objects.equals(idPrestadora, other.idPrestadora);
	}

	@Override
	public String toString() {
		return "Prestadora [idPrestadora=" + idPrestadora + "]";
	}
}

