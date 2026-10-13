package com.exemplo.crud;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ProdutoService {
    private List<Produto> produtos = new ArrayList<>();
    private Long id = 0L;


    public Produto cadastrar(String nome, BigDecimal preco){
        if (preco == null || preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Argumento inválido !");
        }
        if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Argumento inválido !");
        }
        id++;
        Produto novo = new Produto(id, nome, preco, 1);
        produtos.add(novo);
        return  novo;
    }
    public Optional<Produto> buscarProdutoID(Long id){
        return produtos.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }
    public List<Produto> listarTodos(){
        return Collections.unmodifiableList(produtos);
    }

}
