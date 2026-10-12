package com.deveduardo.ecommerce;

import com.deveduardo.ecommerce.model.repository.ProdutoRepository;
import com.deveduardo.ecommerce.model.service.ProdutoService;

public class Main {
    public static void main(String[] args) {
        ProdutoRepository repo = new ProdutoRepository("produtos.csv");
        ProdutoService service = new ProdutoService(repo);

        System.out.println("--- antes ---");
        service.listar().forEach(System.out::println);

        service.reporEstoque(1, 5);
        service.cadastrar("Webcam", 150.0, 8);

        System.out.println("--- depois ---");
        service.listar().forEach(System.out::println);
    }
}