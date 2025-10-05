package br.com.sosysters.dto;

import java.sql.Date;

import br.com.sosysters.entities.Etnia;
import br.com.sosysters.entities.Genero;
import br.com.sosysters.entities.Usuaria;

public class UsuariaDto {
	private Long idUsuaria;
	private String nomeUsuaria;
	private String sobrenomeUsuaria;
	private String nomeSocialUsuaria;
	private Date dtNascimentoUsuaria;
	private String rgUsuaria;
	private String cpfUsuaria;
	private String emailUsuaria;
	private String senhaUsuaria;
	private Etnia etniaUsuaria;
	private Genero generoUsuaria;

	public UsuariaDto() {
	}

	public UsuariaDto(Usuaria entity) {
		idUsuaria = entity.getIdUsuaria();
		nomeUsuaria = entity.getNomeUsuaria();
		sobrenomeUsuaria = entity.getSobrenomeUsuaria();
		nomeSocialUsuaria = entity.getNomeSocialUsuaria();
		dtNascimentoUsuaria = entity.getDtNascimentoUsuaria();
		rgUsuaria = entity.getRgUsuaria();
		cpfUsuaria = entity.getCpfUsuaria();
		emailUsuaria = entity.getEmailUsuaria();
		senhaUsuaria = entity.getSenhaUsuaria();
		etniaUsuaria = entity.getEtniaUsuaria();
		generoUsuaria = entity.getGeneroUsuaria();
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

	public String getNomeSocialUsuaria() {
		return nomeSocialUsuaria;
	}

	public Date getDtNascimentoUsuaria() {
		return dtNascimentoUsuaria;
	}

	public String getRgUsuaria() {
		return rgUsuaria;
	}

	public String getCpfUsuaria() {
		return cpfUsuaria;
	}

	public String getEmailUsuaria() {
		return emailUsuaria;
	}

	public String getSenhaUsuaria() {
		return senhaUsuaria;
	}

	public Etnia getEtniaUsuaria() {
		return etniaUsuaria;
	}

	public Genero getGeneroUsuaria() {
		return generoUsuaria;
	}
}