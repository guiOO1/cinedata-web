package com.cinedata.service;

public class CalculadoraDesconto {
    
    private static final double DESCONTO_RESERVA_ONLINE = 0.10; // 10%
    
    public static double aplicarDescontoReserva(double preco) {
        validarPreco(preco);
        return preco * (1 - DESCONTO_RESERVA_ONLINE);
    }

    public static double aplicarDesconto(double preco, double percentualDesconto) {
        validarPreco(preco);
        if (percentualDesconto < 0 || percentualDesconto > 1) {
            throw new IllegalArgumentException("Percentual deve estar entre 0 e 1");
        }
        return preco * (1 - percentualDesconto);
    }
    
    private static void validarPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo");
        }
    }
}