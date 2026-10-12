package com.deveduardo.ecommerce;

import com.deveduardo.ecommerce.repository.ProdutoRepository;

public class Main {
    public static void main(String[] args) {
        ProdutoRepository repo = new ProdutoRepository("produtos.csv");
        repo.listar().forEach(System.out::println);
    }
}
