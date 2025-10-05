package br.com.sosysters.dto;

import br.com.sosysters.entities.Genero;

public class GeneroDto {
	private Long idGenero;
	private String genero;

	public GeneroDto() {
	}

	public GeneroDto(Genero entity) {
		this.idGenero = entity.getIdGenero();
		this.genero = entity.getGenero();
	}

	public Long getIdGenero() {
		return idGenero;
	}

	public String getGenero() {
		return genero;
	}
}