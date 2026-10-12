package com.deveduardo.ecommerce.model.entities;

public class ItemCarrinho {

    private final Produto produto;
    private int quantidade;

    public ItemCarrinho(Produto produto, int quantidade) {
        if (produto == null) throw new IllegalArgumentException("Produto não pode ser vazio!");
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser positiva!");
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void aumentar(int qtd) {
        if (qtd <= 0) throw new IllegalArgumentException("Quantidade deve ser positiva");
        this.quantidade += qtd;
    }

    public void diminuir(int qtd) {
        if (qtd <= 0) throw new IllegalArgumentException("Quantidade deve ser posiitiva");
        if (qtd > quantidade) throw new IllegalArgumentException("Não há essa quantidade no carrinho!");
        this.quantidade -= qtd;
    }

    public double getSubtotal() {
        return produto.getPreco() * quantidade;
    }
}
