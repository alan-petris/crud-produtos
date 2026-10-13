package com.exemplo.crud;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    // TESTES DE BUSCAR PRODUTOS

    @Test
    public void deveBuscarProdutoPeloId(){
        ProdutoService produtoService = new ProdutoService();
        Produto produto1 = produtoService.cadastrar("produto 1", new BigDecimal("50"));
        Produto produto2 = produtoService.cadastrar("produto 2", new BigDecimal("100"));
        Produto produto3 = produtoService.cadastrar("produto 3", new BigDecimal("200"));

        assertEquals(Optional.of(produto1), produtoService.buscarProdutoID(1L));
    }

    @Test
    public void deveBuscarIdInexistente(){
        ProdutoService produtoService = new ProdutoService();
        Produto produto1 = produtoService.cadastrar("produto 1", new BigDecimal("50"));
        assertEquals(Optional.empty(),produtoService.buscarProdutoID(100L));
    }
    @Test
    public void deveBuscarEmListaVazia(){
        ProdutoService produtoService = new ProdutoService();
        assertEquals(Optional.empty(), produtoService.buscarProdutoID(1L));
    }

    // TESTE DE LISTAR TODOS
    @Test
    public void deveRetornarTodos(){
        ProdutoService produtoService = new ProdutoService();
        Produto produto1 = produtoService.cadastrar("produto 1", new BigDecimal("50"));
        Produto produto2 = produtoService.cadastrar("produto 2", new BigDecimal("100"));
        Produto produto3 = produtoService.cadastrar("produto 3", new BigDecimal("200"));

        List<Produto> todos = produtoService.listarTodos();
        assertEquals(3, todos.size());
        assertSame(produto1, produtoService.listarTodos().get(0));
        assertSame(produto2, produtoService.listarTodos().get(1));
        assertSame(produto3, produtoService.listarTodos().get(2));
    }
    @Test
    public void deveTestarListaVazia(){
        ProdutoService produtoService = new ProdutoService();
        assertTrue(produtoService.listarTodos().isEmpty());
    }
    @Test
    public void deveTestarModificacaoExterna(){
        ProdutoService produtoService = new ProdutoService();
        Produto produto1 = produtoService.cadastrar("produto 1", new BigDecimal("50"));
        Produto produto2 = new Produto(2L, "produto2", new BigDecimal("30"), 1);

        List<Produto> todos = produtoService.listarTodos();

        assertThrows(UnsupportedOperationException.class, () -> todos.add(produto2));

        assertEquals(1, todos.size());
    }
}
