package com.cinedata.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessao")
public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "filmeId", nullable = false)
    private Filme filme;
    
    @ManyToOne
    @JoinColumn(name = "salaId", nullable = false)
    private Sala sala;
    
    @Column(nullable = false)
    private double preco;
    
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;
    
    @Column(nullable = false)
    private boolean ativa;
    
    // Construtores
    public Sessao() {}
    
    public Sessao(Filme filme, Sala sala, double preco, LocalDateTime dataHora, boolean ativa) {
        this.filme = filme;
        this.sala = sala;
        this.preco = preco;
        this.dataHora = dataHora;
        this.ativa = ativa;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Filme getFilme() { return filme; }
    public void setFilme(Filme filme) { this.filme = filme; }
    
    public Sala getSala() { return sala; }
    public void setSala(Sala sala) { this.sala = sala; }
    
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    
    public boolean isAtiva() { return ativa; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }
}