# E-commerce Java

Projeto de e-commerce em Java com Maven, feito para praticar orientação a objetos
e arquitetura em camadas. Roda no terminal e salva os dados em arquivo CSV.

## Funcionalidades
- Listar produtos
- Adicionar e remover itens do carrinho
- Finalizar compra (confere o estoque, baixa e gera o pedido)
- Dados persistidos em `produtos.csv`

## Tecnologias
- Java 17
- Maven

## Estrutura
- `model.entities`: Produto, ItemCarrinho, Carrinho, Pedido
- `model.repository`: leitura e gravação do CSV
- `model.service`: regras de negócio
- `ui`: menu do terminal

## Como rodar
1. Clone o repositório
2. Abra no IntelliJ e rode a classe `Main`

## Próximos passos
- Salvar pedidos em arquivo
- Testes automatizados com JUnit
- Transformar em API REST com Spring Boot