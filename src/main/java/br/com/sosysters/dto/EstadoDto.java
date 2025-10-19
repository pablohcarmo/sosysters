package br.com.sosysters.dto;

import br.com.sosysters.entities.Estado;

public class EstadoDto {
	private Long idEstado;
	private String ufEstado;
	private String estado;

	public EstadoDto() {
	}

	public EstadoDto(Estado entity) {
		idEstado = entity.getIdEstado();
		ufEstado = entity.getUfEstado();
		estado = entity.getEstado();
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public String getUfEstado() {
		return ufEstado;
	}

	public String getEstado() {
		return estado;
	}
}