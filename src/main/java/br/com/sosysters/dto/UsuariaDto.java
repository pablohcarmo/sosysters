package br.com.sosysters.dto;

import java.sql.Date;

import br.com.sosysters.entities.Usuaria;

public class UsuariaDto {
	private Long idUsuaria;
	private String nomeUsuaria;
	private String sobrenomeUsuaria;
	private Date dtNascimentoUsuaria;
	private String emailUsuaria;

	public UsuariaDto() {
	}

	public UsuariaDto(Usuaria entity) {
		idUsuaria = entity.getIdUsuaria();
		nomeUsuaria = entity.getNomeUsuaria();
		sobrenomeUsuaria = entity.getSobrenomeUsuaria();
		dtNascimentoUsuaria = entity.getDtNasciUsuaria();
		emailUsuaria = entity.getEmailUsuaria();
	}

	public Long getIdUsuaria() {
		return idUsuaria;
	}

	public String getNomeUsuaria() {
		return nomeUsuaria;
	}

	public String getSobrenomeUsuaria() {
		return sobrenomeUsuaria;
	}

	public Date getDtNascimentoUsuaria() {
		return dtNascimentoUsuaria;
	}

	public String getEmailUsuaria() {
		return emailUsuaria;
	}
}