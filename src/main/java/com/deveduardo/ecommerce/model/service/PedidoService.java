package com.deveduardo.ecommerce.model.service;

import com.deveduardo.ecommerce.model.entities.Carrinho;
import com.deveduardo.ecommerce.model.entities.ItemCarrinho;
import com.deveduardo.ecommerce.model.entities.Pedido;
import com.deveduardo.ecommerce.model.entities.Produto;
import com.deveduardo.ecommerce.model.repository.ProdutoRepository;

import java.util.List;

import static com.deveduardo.ecommerce.model.service.ProdutoService.buscar;

public class PedidoService {

    private final ProdutoRepository repository;
    private int proximoIdPedido = 1;

    public PedidoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Pedido finalizar(Carrinho carrinho) {
        if (carrinho == null || carrinho.estaVazio()) {
            throw new IllegalArgumentException("O carrinho não pode estar vazio");
        }

        List<Produto> produtos = repository.listar();

        // 1ª passada: conferir o estoque de TODOS os itens
        for (ItemCarrinho item : carrinho.getItens()) {
            Produto produto = buscar(produtos, item.getProduto().getId());
            if (item.getQuantidade() > produto.getEstoque()) {
                throw new IllegalStateException("Estoque insuficiente para " + produto.getNome()
                        + " (disponível: " + produto.getEstoque() + ")");
            }
        }

        Pedido pedido = new Pedido(proximoIdPedido++, carrinho);

        // 2ª passada: baixar o estoque
        for (ItemCarrinho item : carrinho.getItens()) {
            Produto produto = buscar(produtos, item.getProduto().getId());
            produto.baixarEstoque(item.getQuantidade());
        }

        repository.salvarTodos(produtos);
        carrinho.limpar();   // criou o pedido, limpa o carrinho

        return pedido;
    }
}
