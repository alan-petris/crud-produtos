package com.exemplo.crud;

import java.math.BigDecimal;

public class Produto {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private int quantidade;

    public Produto(Long id, String nome, BigDecimal preco, int quantidade){
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Long getId(){
        return id;
    }
    public String getNome(){
        return nome;
    }
    public BigDecimal getPreco(){
        return preco;
    }
    public int getQuantidade(){
        return quantidade;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public void setPreco(BigDecimal preco){
        this.preco = preco;
    }
    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }
}
