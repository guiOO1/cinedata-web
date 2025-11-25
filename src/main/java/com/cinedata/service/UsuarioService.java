package com.cinedata.service;

import com.cinedata.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    public boolean podeFazerReserva(Usuario usuario) {
        if (usuario == null) {
            return false;
        }
        
        // Regra de negócio: usuário deve estar ativo
        return usuario.isAtivo();
    }
    
    public boolean isGerente(Usuario usuario) {
        return usuario != null && usuario.isGerente();
    }

    public boolean temAcessoAdmin(Usuario usuario) {
        return isGerente(usuario) && usuario.isAtivo();
    }
}