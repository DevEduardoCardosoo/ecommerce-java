package com.deveduardo.ecommerce.model.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {

    private final int id;
    private final LocalDateTime data;
    private final List<ItemCarrinho> itens;
    private final double total;

    public Pedido(int id, Carrinho carrinho) {
        if (carrinho == null || carrinho.estaVazio()) {
            throw new IllegalArgumentException("O carrinho não pode estar vazio");
        }
        this.id = id;
        this.data = LocalDateTime.now();
        this.itens = new ArrayList<>(carrinho.getItens());
        this.total = carrinho.getTotal();
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public List<ItemCarrinho> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("Pedido #%d - %s - %d item(ns) - Total: R$ %.2f",
                id, data.format(formato), itens.size(), total);
    }
}
