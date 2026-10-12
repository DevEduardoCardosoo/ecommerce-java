package com.deveduardo.ecommerce.model.entities;

public class Produto {

    private final int id;
    private String nome;
    private double preco;
    private int estoque;

    public Produto(int id, String nome,double preco, int estoque) {
        if(preco < 0) throw new IllegalArgumentException ("Preço não pode ser negativo");
        if(estoque < 0) throw new IllegalArgumentException ("estoque não pode ser negativo");
        this.id = id;
        setNome(nome);
        setPreco(preco);
        this.estoque = estoque;
    }

    public int getId() {return id;
    }

    public String getNome() {return nome;
    }

    public void setNome(String nome) {
        if(nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome não pode ficar vazio");
        this.nome = nome;
    }

    public double getPreco() {return preco;
    }

    public void setPreco(double preco) {
        if(preco < 0) throw new IllegalArgumentException("Preço não pode ser negativo");
        this.preco = preco;
    }

    public int getEstoque() {return estoque;
    }

    public void baixarEstoque(int quantidade){
        if(quantidade <=0) throw new IllegalArgumentException("a quantidade deve ser positiva");
        if(quantidade > estoque) throw new IllegalArgumentException ("Estoque insuficiente para " + nome);
        estoque -= quantidade;
    }

    public void reporEstoque(int quantidade){
        if(quantidade<=0) throw new IllegalArgumentException("Quantidade deve ser positivo!");
        estoque += quantidade;
    }

    @Override
    public String toString(){
        return String.format("[%d] %s - R$ %.2f (estoque: %d)", id, nome, preco, estoque);
    }


}
