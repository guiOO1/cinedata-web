package com.cinedata.model;

public class Filme {
    private final int id;
    private final String nome;
    private final int faixaEtaria;
    private final int duracaoMinutos;
    private final boolean emCartaz;
    
    public Filme(int id, String nome, int faixaEtaria, int duracaoMinutos, boolean emCartaz) {
        this.id = id;
        this.nome = nome;
        this.faixaEtaria = faixaEtaria;
        this.duracaoMinutos = duracaoMinutos;
        this.emCartaz = emCartaz;
    }
    
    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getFaixaEtaria() { return faixaEtaria; }
    public int getDuracaoMinutos() { return duracaoMinutos; }
    public boolean isEmCartaz() { return emCartaz; }
    
    @Override
    public String toString() {
        return String.format("Filme[id=%d, nome=%s, faixaEtaria=%d, emCartaz=%s]",
                id, nome, faixaEtaria, emCartaz);
    }
}