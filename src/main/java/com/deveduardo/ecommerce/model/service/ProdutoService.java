package com.deveduardo.ecommerce.model.service;

import com.deveduardo.ecommerce.model.entities.Produto;
import com.deveduardo.ecommerce.model.repository.ProdutoRepository;

import java.util.List;

public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository){
        this.repository = repository;
    }

    public List<Produto> listar(){
        return repository.listar();
    }

    public Produto buscarPorId(int id){
        return buscar(repository.listar(),id);
    }

    public Produto cadastrar(String nome, double preco, int estoque) {
        List<Produto> produtos = repository.listar();

        Produto produto = new Produto(proximoId(produtos), nome, preco, estoque);
        produtos.add(produto);

        repository.salvarTodos(produtos);
        return produto;
    }

    public void reporEstoque(int id, int quantidade) {
        List<Produto> produtos = repository.listar();

        Produto produto = buscar(produtos, id);
        produto.reporEstoque(quantidade);

        repository.salvarTodos(produtos);
    }

    public void atualizarPreco(int id, double novoPreco) {
        List<Produto> produtos = repository.listar();

        Produto produto = buscar(produtos, id);
        produto.setPreco(novoPreco);

        repository.salvarTodos(produtos);
    }

    private Produto buscar(List<Produto> produtos,int id){
        return produtos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: id " + id));
    }

    private int proximoId(List<Produto> produtos) {
        return produtos.stream()
                .mapToInt(Produto::getId)
                .max()
                .orElse(0) + 1;
    }
}
