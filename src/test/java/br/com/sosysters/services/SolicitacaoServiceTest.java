package br.com.sosysters.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SolicitacaoServiceTest {

    @Test
    void deveAdicionarSolicitacaoNaListaDePesquisa() {
        SolicitacaoService service = new SolicitacaoService();
        int tamanhoInicial = service.listarParaPesquisa().size();

        service.criarSolicitacao("pintura", "Pintura de parede externa");

        assertEquals(tamanhoInicial + 1, service.listarParaPesquisa().size());
        assertEquals("Pintura", service.listarParaPesquisa().getFirst().titulo());
        assertEquals("pintura", service.listarParaPesquisa().getFirst().categoria());
    }

    @Test
    void deveManterCategoriasDinamicasParaPesquisa() {
        SolicitacaoService service = new SolicitacaoService();

        service.criarSolicitacao("hidraulica", "Troca de registro do banheiro");

        assertFalse(service.listarCategoriasPesquisa().isEmpty());
        assertEquals("all", service.listarCategoriasPesquisa().getFirst());
        assertTrue(service.listarCategoriasPesquisa().contains("hidráulica"));
    }
}

