package com.cinedata.model;

import java.time.LocalDateTime;

public class Sessao {
    private final int id;
    private final Filme filme;
    private final LocalDateTime dataHora;
    private final double preco;
    private final boolean ativa;
    
    public Sessao(int id, Filme filme, LocalDateTime dataHora, double preco, boolean ativa) {
        this.id = id;
        this.filme = filme;
        this.dataHora = dataHora;
        this.preco = preco;
        this.ativa = ativa;
    }
    
    public int getId() { return id; }
    public Filme getFilme() { return filme; }
    public LocalDateTime getDataHora() { return dataHora; }
    public double getPreco() { return preco; }
    public boolean isAtiva() { return ativa; }
    
    @Override
    public String toString() {
        return String.format("Sessao[id=%d, filme=%s, dataHora=%s, preco=%.2f, ativa=%s]",
                id, filme.getNome(), dataHora, preco, ativa);
    }
}