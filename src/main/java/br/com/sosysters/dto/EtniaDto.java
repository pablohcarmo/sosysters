package br.com.sosysters.dto;
import br.com.sosysters.entities.Etnia;

public class EtniaDto {
	private Long idEtnia;
	private String etnia;

	public EtniaDto() {
	}

	public EtniaDto(Etnia entity) {
		idEtnia = entity.getIdEtnia();
		etnia = entity.getEtnia();
	}

	public Long getIdEtnia() {
		return idEtnia;
	}

	public String getEtnia() {
		return etnia;
	}
}