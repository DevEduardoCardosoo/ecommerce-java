package com.deveduardo.ecommerce.repository;

import com.deveduardo.ecommerce.model.entities.Produto;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    private final Path arquivo;

    public ProdutoRepository(String caminho) {
        this.arquivo = Path.of(caminho);
    }

    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<>();

        if (!Files.exists(arquivo)) {
            return produtos; // arquivo ainda não existe = lista vazia
        }

        try {
            for (String linha : Files.readAllLines(arquivo, StandardCharsets.UTF_8)) {
                if (linha.isBlank()) continue;

                String[] partes = linha.split(";");
                produtos.add(new Produto(
                        Integer.parseInt(partes[0].trim()),
                        partes[1].trim(),
                        Double.parseDouble(partes[2].trim()),
                        Integer.parseInt(partes[3].trim())
                ));
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo de produtos", e);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Arquivo de produtos com formato inválido", e);
        }

        return produtos;
    }

    public void salvarTodos(List<Produto> produtos) {
        List<String> linhas = new ArrayList<>();
        for (Produto p : produtos) {
            linhas.add(p.getId() + ";" + p.getNome() + ";" + p.getPreco() + ";" + p.getEstoque());
        }

        try {
            Files.write(arquivo, linhas, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar o arquivo de produtos", e);
        }
    }
}
