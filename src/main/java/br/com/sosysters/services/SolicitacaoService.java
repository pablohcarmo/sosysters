package br.com.sosysters.services;

import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SolicitacaoService {

    private final AtomicLong sequence = new AtomicLong(1);
    private final CopyOnWriteArrayList<SolicitacaoPesquisaItem> solicitacoes = new CopyOnWriteArrayList<>();

    public SolicitacaoService() {
        // Itens iniciais para manter a pesquisa funcional antes de integrações adicionais.
        adicionarInicial("Pedreira", "Serviço de construção para uma casa.", "construção", "/images/pedreira.webp");
        adicionarInicial("Pintora", "Pintura profissional para residência de 250m².", "pintura", "/images/pintura.avif");
        adicionarInicial("Serviços Gerais", "Limpeza e manutenção em geral para um ambiente de 100m².", "limpeza", "/images/serviços gerais.jpg");
        adicionarInicial("Empreiteira", "Especialistas em grandes obras e projetos de engenharia.", "construção", "/images/empreiteira.jpg");
    }

    public List<SolicitacaoPesquisaItem> listarParaPesquisa() {
        List<SolicitacaoPesquisaItem> resultado = new ArrayList<>(solicitacoes);
        resultado.sort(Comparator.comparingLong(SolicitacaoPesquisaItem::id).reversed());
        return resultado;
    }

    public List<String> listarCategoriasPesquisa() {
        Set<String> categorias = new LinkedHashSet<>();
        categorias.add("all");
        listarParaPesquisa().forEach(item -> categorias.add(item.categoria()));
        return new ArrayList<>(categorias);
    }

    public void criarSolicitacao(String email, String servico, String descricao) {
        String servicoNormalizado = normalizarServico(servico);
        String titulo = montarTitulo(servicoNormalizado);
        String categoria = mapearCategoria(servicoNormalizado);
        String imagem = mapearImagem(servicoNormalizado);

        solicitacoes.add(new SolicitacaoPesquisaItem(
                sequence.getAndIncrement(),
                titulo,
                descricao,
                categoria,
                imagem,
                email,
                "pendente"
        ));
    }

    public List<SolicitacaoPesquisaItem> listarPorUsuario(String email) {
        return solicitacoes.stream()
                .filter(s -> Objects.equals(s.emailCriador(), email))
                .sorted(Comparator.comparingLong(SolicitacaoPesquisaItem::id).reversed())
                .toList();
    }

    private void adicionarInicial(String titulo, String descricao, String categoria, String imagem) {
        solicitacoes.add(new SolicitacaoPesquisaItem(
                sequence.getAndIncrement(),
                titulo,
                descricao,
                categoria,
                imagem,
                null,
                "disponível"
        ));
    }

    private String montarTitulo(String servico) {
        if (servico == null || servico.isBlank()) {
            return "Serviço";
        }

        String titulo = servico.trim().toLowerCase(Locale.ROOT);
        return Character.toUpperCase(titulo.charAt(0)) + titulo.substring(1);
    }

    private String normalizarServico(String servico) {
        if (servico == null) {
            return "outros";
        }

        String semAcento = Normalizer.normalize(servico, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        String normalizado = semAcento.trim().toLowerCase(Locale.ROOT);

        return normalizado.isBlank() ? "outros" : normalizado;
    }

    private String mapearCategoria(String servicoNormalizado) {
        return switch (servicoNormalizado) {
            case "pedreira", "empreiteira", "construcao" -> "construção";
            case "pintura" -> "pintura";
            case "limpeza", "servicos gerais" -> "limpeza";
            case "hidraulica" -> "hidráulica";
            case "mudanca" -> "mudança";
            default -> "outros";
        };
    }

    private String mapearImagem(String servicoNormalizado) {
        return switch (servicoNormalizado) {
            case "pedreira" -> "/images/pedreira.webp";
            case "pintura" -> "/images/pintura.avif";
            case "limpeza", "servicos gerais" -> "/images/serviços gerais.jpg";
            case "empreiteira" -> "/images/empreiteira.jpg";
            default -> "/images/icone-minha-soli.svg";
        };
    }

    public record SolicitacaoPesquisaItem(Long id, String titulo, String descricao, String categoria, String imagem, String emailCriador, String status) {
    }
}

