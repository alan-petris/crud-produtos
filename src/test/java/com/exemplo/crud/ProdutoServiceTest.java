package com.exemplo.crud;

import com.exemplo.crud.exception.DadosInvalidosException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProdutoServiceTest {
    @Test
    public void deveCadastrarProdutoComDadosValidos() {
        ProdutoService produtoService = new ProdutoService();

        Produto geladeira = produtoService.cadastrar("Geladeira", new BigDecimal("100.00"));

        assertEquals(1L, geladeira.getId());
        assertEquals("Geladeira", geladeira.getNome());
        assertEquals(new BigDecimal("100.00"), geladeira.getPreco());
        assertEquals(1, geladeira.getQuantidade());
    }

    @Test
    public void deveGerarIdsSequenciaisAoCadastrarProdutos() {
        ProdutoService produtoService = new ProdutoService();
        Produto carro = produtoService.cadastrar("Carro", new BigDecimal("1000"));
        Produto aviao = produtoService.cadastrar("Avião", new BigDecimal("50000"));
        assertEquals(1L, carro.getId());
        assertEquals(2L, aviao.getId());
    }

    @Test
    public void deveLancarExcecaoQuandoNomeForNulo() {
        ProdutoService produtoService = new ProdutoService();

        DadosInvalidosException excecao = assertThrows(DadosInvalidosException.class, () -> produtoService.cadastrar(null, new BigDecimal("100")));
        assertEquals("Nome inválido !", excecao.getMessage());
    }

    @Test
    public void deveLancarExcecaoQuandoNomeForVazio() {
        ProdutoService produtoService = new ProdutoService();
        DadosInvalidosException excecao = assertThrows(DadosInvalidosException.class, () -> produtoService.cadastrar("", new BigDecimal("50")));
        assertEquals("Nome inválido !", excecao.getMessage());
    }

    @Test
    public void deveLancarExcecaoQuandoNomeEstiverEmBranco() {
        ProdutoService produtoService = new ProdutoService();
        DadosInvalidosException excecao = assertThrows(DadosInvalidosException.class, () -> produtoService.cadastrar("    ", new BigDecimal("500")));
        assertEquals("Nome inválido !", excecao.getMessage());
    }

    @Test
    public void deveLancarExcecaoQuandoPrecoForNulo() {
        ProdutoService produtoService = new ProdutoService();
        DadosInvalidosException excecao = assertThrows(DadosInvalidosException.class, () -> produtoService.cadastrar("mouse", null));
        assertEquals("Preço inválido !", excecao.getMessage());
    }

    @Test
    public void deveLancarExcecaoQuandoPrecoForZero() {
        ProdutoService produtoService = new ProdutoService();
        DadosInvalidosException excecao = assertThrows(DadosInvalidosException.class, () -> produtoService.cadastrar("Monitor", BigDecimal.ZERO));
        assertEquals("Preço inválido !", excecao.getMessage());
    }

    @Test
    public void deveLancarExcecaoQuandoPrecoForMenorQueZero() {
        ProdutoService produtoService = new ProdutoService();
        DadosInvalidosException excecao = assertThrows(DadosInvalidosException.class, () -> produtoService.cadastrar("Fruta", new BigDecimal("-50")));
        assertEquals("Preço inválido !", excecao.getMessage());
    }

    @Test
    public void naoDeveConsumirIdQuandoCadastroForInvalido() {
        ProdutoService produtoService = new ProdutoService();
        assertThrows(DadosInvalidosException.class, () -> produtoService.cadastrar("", new BigDecimal("0")));

        Produto computador = produtoService.cadastrar("Computador", new BigDecimal("2000"));
        assertEquals(1L, computador.getId());
    }

    // TESTES DE BUSCAR PRODUTOS

    @Test
    public void deveBuscarProdutoPeloId() {
        ProdutoService produtoService = new ProdutoService();
        Produto produto1 = produtoService.cadastrar("produto 1", new BigDecimal("50"));
        Produto produto2 = produtoService.cadastrar("produto 2", new BigDecimal("100"));
        Produto produto3 = produtoService.cadastrar("produto 3", new BigDecimal("200"));

        assertEquals(Optional.of(produto1), produtoService.buscarProdutoID(1L));
        assertEquals(Optional.of(produto2), produtoService.buscarProdutoID(2L));
        assertEquals(Optional.of(produto3), produtoService.buscarProdutoID(3L));
    }

    @Test
    public void deveRetornarEmptyOptionalAoBuscarIdInexistente() {
        ProdutoService produtoService = new ProdutoService();
        produtoService.cadastrar("produto 1", new BigDecimal("50"));
        Long idInexistente = 100L;
        Optional<Produto> result = produtoService.buscarProdutoID(idInexistente);
        assertTrue(result.isEmpty());
    }

    @Test
    public void deveBuscarEmListaVazia() {
        ProdutoService produtoServiceVazia = new ProdutoService();
        Long qualquerId = 1L;
        Optional<Produto> result = produtoServiceVazia.buscarProdutoID(qualquerId);
        assertTrue(result.isEmpty(), "O resultado deve ser vazio quando não tem produtos registrados");
    }

    // TESTE DE LISTAR TODOS
    @Test
    public void deveRetornarTodos() {
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
    public void deveTestarListaVazia() {
        ProdutoService produtoService = new ProdutoService();
        assertTrue(produtoService.listarTodos().isEmpty());
    }

    @Test
    public void deveTestarModificacaoExterna() {
        ProdutoService produtoService = new ProdutoService();
        Produto produto1 = produtoService.cadastrar("produto 1", new BigDecimal("50"));
        Produto produto2 = new Produto(2L, "produto2", new BigDecimal("30"), 1);

        List<Produto> todos = produtoService.listarTodos();

        assertThrows(UnsupportedOperationException.class, () -> todos.add(produto2));

        assertEquals(1, todos.size());
    }

    // METODO ATUALIZAR
    @Test
    public void deveAtualizarProduto() {
        ProdutoService produtoService = new ProdutoService();

        Produto produto1 = produtoService.cadastrar("produto1", new BigDecimal("50.00"));
        Long idOriginal = produto1.getId();
        BigDecimal precoOriginal = produto1.getPreco();
        int quantidadeProduto = produto1.getQuantidade();
        boolean produtoAtualizado = produtoService.atualizar(1L, "produto2", new BigDecimal("200.00"));

        assertEquals(idOriginal, produto1.getId());
        assertEquals(new BigDecimal("50.00"), precoOriginal);
        assertEquals("produto2", produto1.getNome());
        assertEquals(new BigDecimal("200.00"), produto1.getPreco());
        assertEquals(quantidadeProduto, produto1.getQuantidade());

        assertTrue(produtoAtualizado);
    }

    @Test
    public void deveRetornarFalseAoAtualizarProdutoInexistente() {
        ProdutoService produtoService = new ProdutoService();
        Produto produto1 = produtoService.cadastrar("produto1", new BigDecimal("50.00"));

        boolean produtoAtualizado1 = produtoService.atualizar(2L, "AlteradoTeste", new BigDecimal("100.00"));

        assertEquals("produto1", produto1.getNome());
        assertEquals(new BigDecimal("50.00"), produto1.getPreco());
        assertFalse(produtoAtualizado1);
    }

    @Test
    public void deveLancarExcecaoAoAtualizarComDadosInvalidos() {
        ProdutoService produtoService = new ProdutoService();
        produtoService.cadastrar("produto1", new BigDecimal("50.00"));

        DadosInvalidosException excecaoPrecoZero = assertThrows(DadosInvalidosException.class, () -> produtoService.atualizar(1L, "a", new BigDecimal("0")));
        assertEquals("Preço inválido !", excecaoPrecoZero.getMessage());

        DadosInvalidosException excecaoPrecoNegativo = assertThrows(DadosInvalidosException.class, () -> produtoService.atualizar(1L, "a", new BigDecimal("-10")));
        assertEquals("Preço inválido !", excecaoPrecoNegativo.getMessage());

        DadosInvalidosException excecaoNomeVazio = assertThrows(DadosInvalidosException.class, () -> produtoService.atualizar(1L, "", new BigDecimal("10")));
        assertEquals("Nome inválido !", excecaoNomeVazio.getMessage());

        DadosInvalidosException excecaoIdInvalido = assertThrows(DadosInvalidosException.class, () -> produtoService.atualizar(null, "teste", new BigDecimal("50")));
        assertEquals("Id inválido !", excecaoIdInvalido.getMessage());
    }

    //METODO REMOVER
    @Test
    public void deveRemoverProduto() {
        ProdutoService produtoService = new ProdutoService();
        Produto produto = produtoService.cadastrar("Novo", new BigDecimal("50"));
        assertEquals("Novo", produto.getNome());
        assertTrue(produtoService.remover(1L));
        assertFalse(produtoService.remover(2L));
        DadosInvalidosException idNulo = assertThrows(DadosInvalidosException.class, () -> produtoService.remover(null));
        assertEquals("Id inválido !", idNulo.getMessage());

    }

    @Test
    public void deveEnviarDadosInvalidosExceptionAoReceberIdNulo() {
        ProdutoService produtoService = new ProdutoService();
        produtoService.cadastrar("Novo", new BigDecimal("50"));
        DadosInvalidosException idNulo = assertThrows(DadosInvalidosException.class, () -> produtoService.remover(null));
        assertEquals("Id inválido !", idNulo.getMessage());
    }

    @Test
    public void deveRetornarFalseAoReceberIdQueNaoPossui() {
        ProdutoService produtoService = new ProdutoService();
        produtoService.cadastrar("Novo", new BigDecimal("50"));
        assertFalse(produtoService.remover(5L));
    }

    @Test
    public void devePreservarProdutosQueNaoForamRemovidos(){
        ProdutoService produtoService = new ProdutoService();
        Produto produto1 = produtoService.cadastrar("produto1", new BigDecimal("50"));
        Produto produto2 = produtoService.cadastrar("produto2", new BigDecimal("100"));
        Produto produto3 = produtoService.cadastrar("produto3", new BigDecimal("150"));
        boolean removido = produtoService.remover(1L);
        assertTrue(removido);
        assertEquals(2, produtoService.listarTodos().size());
        assertFalse(produtoService.listarTodos().contains(produto1));
        assertTrue(produtoService.listarTodos().contains(produto2));
        assertTrue(produtoService.listarTodos().contains(produto3));
    }


}
