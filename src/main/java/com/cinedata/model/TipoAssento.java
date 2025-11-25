package com.cinedata.model;

import javax.persistence.*;

@Entity
@Table(name = "tipo_assento")
public class TipoAssento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 255)
    private String descricao;
    
    @Column(name = "nome_assento", nullable = false, length = 45)
    private String nomeAssento;
    
    // Construtores
    public TipoAssento() {}
    
    public TipoAssento(String descricao, String nomeAssento) {
        this.descricao = descricao;
        this.nomeAssento = nomeAssento;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public String getNomeAssento() { return nomeAssento; }
    public void setNomeAssento(String nomeAssento) { this.nomeAssento = nomeAssento; }
}