package com.deveduardo.ecommerce.ui;

import com.deveduardo.ecommerce.model.entities.Carrinho;
import com.deveduardo.ecommerce.model.entities.ItemCarrinho;
import com.deveduardo.ecommerce.model.entities.Pedido;
import com.deveduardo.ecommerce.model.service.PedidoService;
import com.deveduardo.ecommerce.model.service.ProdutoService;

import java.util.Scanner;

public class Menu {

    private final ProdutoService produtoService;
    private final PedidoService pedidoService;
    private final Carrinho carrinho = new Carrinho();
    private final Scanner scanner = new Scanner(System.in);

    public Menu(ProdutoService produtoService, PedidoService pedidoService) {
        this.produtoService = produtoService;
        this.pedidoService = pedidoService;
    }

    public void iniciar() {
        int opcao;
        do {
            exibirOpcoes();
            opcao = lerInt("Escolha: ");

            try {
                switch (opcao) {
                    case 1 -> listarProdutos();
                    case 2 -> adicionarAoCarrinho();
                    case 3 -> verCarrinho();
                    case 4 -> removerDoCarrinho();
                    case 5 -> finalizarCompra();
                    case 0 -> System.out.println("Até mais!");
                    default -> System.out.println("Opção inválida");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private void exibirOpcoes() {
        System.out.println("\n=== LOJA ===");
        System.out.println("1 - Listar produtos");
        System.out.println("2 - Adicionar ao carrinho");
        System.out.println("3 - Ver carrinho");
        System.out.println("4 - Remover do carrinho");
        System.out.println("5 - Finalizar compra");
        System.out.println("0 - Sair");
    }

    private void listarProdutos() {
        produtoService.listar().forEach(System.out::println);
    }

    private void adicionarAoCarrinho() {
        int id = lerInt("Id do produto: ");
        int quantidade = lerInt("Quantidade: ");

        carrinho.adicionar(produtoService.buscarPorId(id), quantidade);
        System.out.println("Produto adicionado ao carrinho.");
    }

    private void verCarrinho() {
        if (carrinho.estaVazio()) {
            System.out.println("Carrinho vazio.");
            return;
        }
        for (ItemCarrinho item : carrinho.getItens()) {
            System.out.printf("%s x%d = R$ %.2f%n",
                    item.getProduto().getNome(), item.getQuantidade(), item.getSubtotal());
        }
        System.out.printf("Total: R$ %.2f%n", carrinho.getTotal());
    }

    private void removerDoCarrinho() {
        int id = lerInt("Id do produto a remover: ");
        int quantidade = lerInt("Quantidade a remover: ");

        carrinho.diminuirQuantidade(id, quantidade);
        System.out.println("Carrinho atualizado.");
    }

    private void finalizarCompra() {
        Pedido pedido = pedidoService.finalizar(carrinho);
        System.out.println("Compra finalizada!");
        System.out.println(pedido);
    }

    private int lerInt(String mensagem) {
        System.out.print(mensagem);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // cai em "opção inválida" ou em erro de validação
        }
    }
}