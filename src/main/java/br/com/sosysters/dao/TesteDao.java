package br.com.sosysters.dao;

import java.sql.Date;
import java.util.List;

import br.com.sosysters.pojo.Usuaria;

public class TesteDao {
	public static void main(String[] args) {
        UsuariaDao dao = new UsuariaDao();

        // Inserção de teste
        Usuaria nova = new Usuaria("Nome Teste3", "Sobrenome Teste3", Date.valueOf("2025-05-13"),"1234567", "12345678900", "teste3@email.com", "senha321", 3, 1);
        nova.setDataNascimento(Date.valueOf("2020-05-13"));
        dao.insert(nova);

        // Listagem de objetos/registros do banco
        List<Usuaria> usuarias = dao.getLista();
        for (Usuaria u : usuarias) {
            System.out.println("Nome: " + u.getNomeUsuaria());
            System.out.println("Sobrenome: " + u.getSobrenomeUsuaria());
            System.out.println("Email: " + u.getEmail());
            System.out.println("Data Nascimento: " + u.getDataNascimento());
            System.out.println("CPF: " + u.getCpf());
            System.out.println("RG: " + u.getRg());
            System.out.println("Senha: " + u.getSenha());
            System.out.println("Etnia: " + u.getEtnia());
            System.out.println("Genero: " + u.getGenero());
            System.out.println("---------------------------------");
        }
    }
}