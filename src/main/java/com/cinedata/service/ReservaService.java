package com.cinedata.service;

import com.cinedata.model.Usuario;
import com.cinedata.model.Sessao;
import java.time.LocalDateTime;

public class ReservaService {
    
    public boolean podeReservar(Usuario usuario, Sessao sessao) {
        if (usuario == null || sessao == null) {
            return false;
        }
        
        // Usuário deve estar ativo
        if (!usuario.isAtivo()) {
            return false;
        }
        
        // Sessão deve estar ativa
        if (!sessao.isAtiva()) {
            return false;
        }
        
        // Sessão deve ser no futuro
        if (sessao.getDataHora().isBefore(LocalDateTime.now())) {
            return false;
        }
        
        return true;
    }
    
    public double calcularPrecoComDesconto(double precoOriginal) {
        if (precoOriginal < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo");
        }
        return precoOriginal * 0.9; // 10% de desconto
    }
    

    public boolean isFaixaEtariaAdequada(Sessao sessao, int idadeUsuario) {
        return idadeUsuario >= sessao.getFilme().getFaixaEtaria();
    }
}