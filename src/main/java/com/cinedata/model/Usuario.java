package com.cinedata.model;

public class Usuario {
    private final int id;
    private final String nome;
    private final String email;
    private final boolean ativo;
    private final boolean gerente;
    
    public Usuario(int id, String nome, String email, boolean ativo, boolean gerente) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.ativo = ativo;
        this.gerente = gerente;
    }
    
    // Getters - imutabilidade
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public boolean isAtivo() { return ativo; }
    public boolean isGerente() { return gerente; }
    
    @Override
    public String toString() {
        return String.format("Usuario[id=%d, nome=%s, email=%s, ativo=%s, gerente=%s]",
                id, nome, email, ativo, gerente);
    }
}