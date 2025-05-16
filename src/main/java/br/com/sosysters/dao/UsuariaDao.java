package br.com.sosysters.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sosysters.pojo.Usuaria;

public class UsuariaDao {
	private Connection connection;
	private String url = "jdbc:postgresql://switchyard.proxy.rlwy.net:20155/railway";
	private String user = "postgres";
	private String password = "SLxURYRUCXLsKjAnWnBOdWgyEnhDyYFc";

	public UsuariaDao() {
		try {
			connection = DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			if (connection == null) {
			    System.out.println("Erro: Conexão com o banco de dados não estabelecida!");
			    return;
			}
		}
	}

	public void insert (Usuaria usuaria) {
		String sql = "INSERT INTO \"usuarias\" (nome, sobrenome, dt_nascimento, rg, cpf, email, senha, id_etnia, id_genero) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try(PreparedStatement pstmt = connection.prepareStatement(sql)){
			pstmt.setString(1, usuaria.getNomeUsuaria());
			pstmt.setString(2,  usuaria.getSobrenomeUsuaria());
			pstmt.setDate(3, new java.sql.Date(usuaria.getDataNascimento().getTime()));
			pstmt.setString(4, usuaria.getRg());
			pstmt.setString(5, usuaria.getCpf());
			pstmt.setString(6, usuaria.getEmail());
			pstmt.setString(7, usuaria.getSenha());
			pstmt.setInt(8, usuaria.getEtnia());
			pstmt.setInt(9, usuaria.getGenero());
			System.out.println("Inserindo usuária no banco...");
			pstmt.executeUpdate();
			System.out.println("Usuária inserida com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		    System.err.println("Erro ao inserir usuária:");
		    System.out.println("Erro ao inserir: " + e.getMessage());
		}
	}

	public List<Usuaria> getLista () {
		List<Usuaria> lista = new ArrayList<Usuaria>();
		String sql = "SELECT * FROM Usuarias";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String nome = rs.getString("nome");
				String sobrenome = rs.getString("sobrenome");
				Date dataNascimento = rs.getDate("dt_nascimento");
				String cpf = rs.getString("cpf");
				String rg = rs.getString("rg");
				String email = rs.getString("email");
				String senha = rs.getString("senha");
				int etnia = rs.getInt("id_etnia");
				int genero = rs.getInt("id_genero");
				lista.add(new Usuaria(nome, sobrenome, dataNascimento, rg, cpf, email, senha, etnia, genero));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}