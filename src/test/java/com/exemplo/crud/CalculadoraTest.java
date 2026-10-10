package com.exemplo.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
    @Test
    void deveSomarDoisNumeros(){
        Calculadora calculadora = new Calculadora();

        int resultado = calculadora.somar(2,3);

        assertEquals(5, resultado);
    }
}
