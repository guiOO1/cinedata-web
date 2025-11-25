package com.cinedata.model;

import javax.persistence.*;

@Entity
@Table(name = "filme")
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 45)
    private String nome;
    
    @Column(name = "faixa_etaria", nullable = false)
    private int faixaEtaria;
    
    @Column(nullable = false, length = 45)
    private String produtora;
    
    @Column(nullable = false, length = 255)
    private String descricao;
    
    @Column(name = "duracao_min", nullable = false)
    private int duracaoMin;
    
    @Column(name = "em_cartaz", nullable = false)
    private boolean emCartaz;
    
    // Construtores
    public Filme() {}
    
    public Filme(String nome, int faixaEtaria, String produtora, String descricao, int duracaoMin, boolean emCartaz) {
        this.nome = nome;
        this.faixaEtaria = faixaEtaria;
        this.produtora = produtora;
        this.descricao = descricao;
        this.duracaoMin = duracaoMin;
        this.emCartaz = emCartaz;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public int getFaixaEtaria() { return faixaEtaria; }
    public void setFaixaEtaria(int faixaEtaria) { this.faixaEtaria = faixaEtaria; }
    
    public String getProdutora() { return produtora; }
    public void setProdutora(String produtora) { this.produtora = produtora; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public int getDuracaoMin() { return duracaoMin; }
    public void setDuracaoMin(int duracaoMin) { this.duracaoMin = duracaoMin; }
    
    public boolean isEmCartaz() { return emCartaz; }
    public void setEmCartaz(boolean emCartaz) { this.emCartaz = emCartaz; }
}