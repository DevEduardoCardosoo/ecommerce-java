package com.deveduardo.ecommerce.model.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrinho {

    private final List<ItemCarrinho> itens = new ArrayList<>();

    public void adicionar(Produto produto,int quantidade){
        if(quantidade <= 0) throw new IllegalArgumentException("A quantidade deve ser positiva!");

        for(ItemCarrinho item: itens){
            if(item.getProduto().getId() == produto.getId()){
                item.aumentar(quantidade);
                return;
            }
        }
        itens.add(new ItemCarrinho(produto,quantidade));
    }

    public void remover(int IdProduto){
        itens.removeIf(item -> item.getProduto().getId() == IdProduto);
    }

    public double getTotal(){
        double total = 0;
        for(ItemCarrinho item: itens){
            total += item.getSubtotal();
        }
        return total;
    }

    public List<ItemCarrinho> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public boolean estaVazio() { return itens.isEmpty(); }

    public void limpar() { itens.clear(); }

}
