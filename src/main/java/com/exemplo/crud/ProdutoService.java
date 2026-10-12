package com.exemplo.crud;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

}
