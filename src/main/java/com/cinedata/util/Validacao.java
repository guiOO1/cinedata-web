package com.cinedata.util;


public class Validacao {
    
    private static final int TAMANHO_MINIMO_SENHA = 6;
    

    public static boolean validarSenha(String senha) {
        return senha != null && senha.length() >= TAMANHO_MINIMO_SENHA;
    }

    public static boolean validarEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    public static boolean validarFaixaEtaria(int faixaEtariaFilme, int idadeUsuario) {
        return idadeUsuario >= faixaEtariaFilme;
    }

    public static boolean validarObrigatorios(String... campos) {
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}