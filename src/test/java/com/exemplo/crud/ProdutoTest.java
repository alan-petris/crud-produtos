package com.exemplo.crud;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutoTest {
    @Test
    public void deveVerificarAtributosInicializados(){
        Produto teclado = new Produto(1L, "Teclado", new BigDecimal("150.00"), 10);
        Long idTeclado = teclado.getId();
        String nomeTeclado = teclado.getNome();
        BigDecimal precoTeclado = teclado.getPreco();
        int quantidadeTeclado = teclado.getQuantidade();
        assertEquals(1L, idTeclado);
        assertEquals("Teclado", nomeTeclado);
        assertEquals(new BigDecimal("150.00"), precoTeclado);
        assertEquals(10, quantidadeTeclado);
    }

    @Test
    public void deveAtualizarNomeDoProduto(){
        Produto monitor = new Produto(2L, "Monitor", new BigDecimal("500.00"),3);
        monitor.setNome("Alterado");
        assertEquals("Alterado", monitor.getNome());
    }

    @Test
    public void deveAtualizarPrecoDoProduto(){
        Produto mouse = new Produto (3L, "Mouse", new BigDecimal("50.90"), 5);
        mouse.setPreco(new BigDecimal("30.00"));
        BigDecimal precoMouse = mouse.getPreco();
        assertEquals(new BigDecimal("30.00"), precoMouse);
    }

    @Test
    public void deveAtualizarQuantidadeDoProduto(){
        Produto gabinete = new Produto(4L, "Gabinete", new BigDecimal("99.90"), 1);

        gabinete.setQuantidade(5);

        int quantidadeGabinete = gabinete.getQuantidade();

        assertEquals(5, quantidadeGabinete);
    }
}
