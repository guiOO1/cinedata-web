package com.cinedata.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraDescontoTest {

    @Test
    void testDesconto10Porcento() {
        double resultado = CalculadoraDesconto.aplicarDescontoReserva(20.0);
        assertEquals(18.0, resultado, 0.001);
    }

    @Test
    void testPrecoZero() {
        assertEquals(0.0, CalculadoraDesconto.aplicarDescontoReserva(0.0));
    }

    @Test
    void testPrecoNegativo() {
        assertThrows(IllegalArgumentException.class,
            () -> CalculadoraDesconto.aplicarDescontoReserva(-10.0));
    }
}