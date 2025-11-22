package com.cinedata;

import com.cinedata.model.*;
import com.cinedata.service.*;
import com.cinedata.util.Validacao;
import com.cinedata.util.Criptografia;
import java.time.LocalDateTime;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== CINEDATA BACKEND - ETAPA 6 ===");
        System.out.println("=== TESTES DE REFATORAÇÃO SOLID ===\n");
        
        // Teste 1: Validações
        testarValidacoes();
        
        // Teste 2: Cálculos de desconto
        testarCalculosDesconto();
        
        // Teste 3: Regras de negócio
        testarRegrasNegocio();
        
        // Teste 4: Criptografia
        testarCriptografia();
        
        System.out.println("\n=== TODOS OS TESTES CONCLUÍDOS ===");
    }
    
    private static void testarValidacoes() {
        System.out.println("--- TESTE 1: VALIDAÇÕES ---");
        
        System.out.println("Senha '12345' (inválida): " + Validacao.validarSenha("12345"));
        System.out.println("Senha '123456' (válida): " + Validacao.validarSenha("123456"));
        System.out.println("Email válido: " + Validacao.validarEmail("usuario@email.com"));
        System.out.println("Email inválido: " + Validacao.validarEmail("emailinvalido"));
        System.out.println("Faixa etária adequada (14 >= 12): " + Validacao.validarFaixaEtaria(12, 14));
        System.out.println("Faixa etária inadequada (10 >= 12): " + Validacao.validarFaixaEtaria(12, 10));
    }
    
    private static void testarCalculosDesconto() {
        System.out.println("\n--- TESTE 2: CÁLCULOS DE DESCONTO ---");

        double precoOriginal = 20.0;
        System.out.printf("Preço original: R$ %.2f%n", precoOriginal);
        System.out.printf("Com 10%% desconto: R$ %.2f%n", 
                         CalculadoraDesconto.aplicarDescontoReserva(precoOriginal));
    }

    private static void testarRegrasNegocio() {
        System.out.println("\n--- TESTE 3: REGRAS DE NEGÓCIO ---");
        
        // Criar objetos de teste
        Usuario usuarioAtivo = new Usuario(1, "João Silva", "joao@email.com", true, false);
        Usuario usuarioInativo = new Usuario(2, "Maria Santos", "maria@email.com", false, false);
        Usuario gerente = new Usuario(3, "Admin", "admin@cinedata.com", true, true);
        
        Filme filme = new Filme(1, "Divertida Mente 2", 0, 120, true);
        Sessao sessaoFutura = new Sessao(1, filme, LocalDateTime.now().plusDays(1), 20.0, true);
        Sessao sessaoPassada = new Sessao(2, filme, LocalDateTime.now().minusDays(1), 20.0, true);
        
        UsuarioService usuarioService = new UsuarioService();
        ReservaService reservaService = new ReservaService();
        
        System.out.println("Usuário ativo pode reservar: " + usuarioService.podeFazerReserva(usuarioAtivo));
        System.out.println("Usuário inativo pode reservar: " + usuarioService.podeFazerReserva(usuarioInativo));
        System.out.println("É gerente: " + usuarioService.isGerente(gerente));
        
        System.out.println("Reserva válida (usuário ativo + sessão futura): " + 
                          reservaService.podeReservar(usuarioAtivo, sessaoFutura));
        System.out.println("Reserva inválida (usuário inativo): " + 
                          reservaService.podeReservar(usuarioInativo, sessaoFutura));
        System.out.println("Reserva inválida (sessão passada): " + 
                          reservaService.podeReservar(usuarioAtivo, sessaoPassada));
    }
    
    private static void testarCriptografia() {
        System.out.println("\n--- TESTE 4: CRIPTOGRAFIA ---");
        
        String senha = "minhaSenha123";
        String hash = Criptografia.criptografarMD5(senha);
        
        System.out.println("Senha original: " + senha);
        System.out.println("Hash MD5: " + hash);
        System.out.println("Hash length: " + hash.length() + " caracteres");
    }
}