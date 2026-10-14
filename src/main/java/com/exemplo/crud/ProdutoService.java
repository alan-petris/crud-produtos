package com.exemplo.crud;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ProdutoService {
    private List<Produto> produtos = new ArrayList<>();
    private Long id = 0L;


    public Produto cadastrar(String nome, BigDecimal preco) {
        if (preco == null || preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Argumento inválido !");
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Argumento inválido !");
        }
        id++;
        Produto novo = new Produto(id, nome, preco, 1);
        produtos.add(novo);
        return novo;
    }

    public Optional<Produto> buscarProdutoID(Long id) {
        return produtos.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public List<Produto> listarTodos() {
        return Collections.unmodifiableList(produtos);
    }

    public boolean atualizar(Long id, String nome, BigDecimal preco) {
        if(id == null){
            throw new IllegalArgumentException("Id inválido !");
        }
        if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome inválido !");
        }
        if (preco == null || preco.compareTo(BigDecimal.ZERO) <=0){
            throw new IllegalArgumentException("Preço inválido !");
        }
        Optional<Produto> produtoOptional = produtos.stream()
                .filter(produto -> produto.getId().equals(id))
                .findFirst();
        produtoOptional.ifPresent(produto -> {
            produto.setNome(nome);
            produto.setPreco(preco);
        });
        return produtoOptional.isPresent();
    }
    public boolean remover(Long id){
        if (id == null){
            throw new IllegalArgumentException("Id inválido !");
        }
        return produtos.removeIf((p) -> p.getId().equals(id));
    }
}
