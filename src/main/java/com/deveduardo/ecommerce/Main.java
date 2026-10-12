package com.deveduardo.ecommerce;

import com.deveduardo.ecommerce.model.repository.ProdutoRepository;
import com.deveduardo.ecommerce.model.service.PedidoService;
import com.deveduardo.ecommerce.model.service.ProdutoService;
import com.deveduardo.ecommerce.ui.Menu;

public class Main {
    public static void main(String[] args) {
        ProdutoRepository repo = new ProdutoRepository("produtos.csv");
        new Menu(new ProdutoService(repo), new PedidoService(repo)).iniciar();
    }
}