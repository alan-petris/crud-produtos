package com.exemplo.crud;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProdutoServiceTest {
    @Test
    public void deveCadastrarProdutoComDadosValidos(){
        ProdutoService produtoService = new ProdutoService();

        Produto geladeira = produtoService.cadastrar("Geladeira", new BigDecimal("100.00"));

        assertEquals(1L, geladeira.getId());
        assertEquals("Geladeira", geladeira.getNome());
        assertEquals(new BigDecimal("100.00"), geladeira.getPreco());
        assertEquals(1, geladeira.getQuantidade());
    }

    @Test
    public void deveGerarIdsSequenciaisAoCadastrarProdutos(){
        ProdutoService produtoService = new ProdutoService();
        Produto carro = produtoService.cadastrar("Carro", new BigDecimal("1000"));
        Produto aviao = produtoService.cadastrar("Avião", new BigDecimal("50000"));
        assertEquals(1L, carro.getId());
        assertEquals(2L, aviao.getId());
    }

    @Test
    public void deveLancarExcecaoQuandoNomeForNulo(){
        ProdutoService produtoService = new ProdutoService();

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,() -> produtoService.cadastrar(null, new BigDecimal("100")));
        assertEquals("Argumento inválido !", excecao.getMessage());
    }

    @Test
    public void deveLancarExcecaoQuandoNomeForVazio(){
        ProdutoService produtoService = new ProdutoService();
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> produtoService.cadastrar("", new BigDecimal("50")));
        assertEquals("Argumento inválido !", excecao.getMessage());
    }

    @Test
    public void deveLancarExcecaoQuandoNomeEstiverEmBranco(){
        ProdutoService produtoService = new ProdutoService();
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> produtoService.cadastrar("    ", new BigDecimal("500")));
        assertEquals("Argumento inválido !", excecao.getMessage());
    }
    @Test
    public void deveLancarExcecaoQuandoPrecoForNulo(){
        ProdutoService produtoService = new ProdutoService();
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> produtoService.cadastrar("mouse", null));
        assertEquals("Argumento inválido !", excecao.getMessage());
    }
    @Test
    public void deveLancarExcecaoQuandoPrecoForZero(){
        ProdutoService produtoService = new ProdutoService();
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> produtoService.cadastrar("Monitor", BigDecimal.ZERO));
        assertEquals("Argumento inválido !", excecao.getMessage());
    }
    @Test
    public void deveLancarExcecaoQuandoPrecoForMenorQueZero(){
        ProdutoService produtoService = new ProdutoService();
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> produtoService.cadastrar("Fruta", new BigDecimal("-50")));
        assertEquals("Argumento inválido !", excecao.getMessage());
    }
    @Test
    public void naoDeveConsumirIdQuandoCadastroForInvalido(){
        ProdutoService produtoService = new ProdutoService();
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> produtoService.cadastrar("", new BigDecimal("0")));
        assertEquals("Argumento inválido !", excecao.getMessage());

        Produto computador = produtoService.cadastrar("Computador", new BigDecimal("2000"));
        assertEquals(1L, computador.getId());
    }
}
