package com.cinedata.model;

import javax.persistence.*;

@Entity
@Table(name = "assento")
public class Assento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "tipo_assento_id", nullable = false)
    private TipoAssento tipoAssento;
    
    @Column(nullable = false, length = 1)
    private String coluna;
    
    @Column(nullable = false)
    private int numero;
    
    // Construtores
    public Assento() {}
    
    public Assento(TipoAssento tipoAssento, String coluna, int numero) {
        this.tipoAssento = tipoAssento;
        this.coluna = coluna;
        this.numero = numero;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public TipoAssento getTipoAssento() { return tipoAssento; }
    public void setTipoAssento(TipoAssento tipoAssento) { this.tipoAssento = tipoAssento; }
    
    public String getColuna() { return coluna; }
    public void setColuna(String coluna) { this.coluna = coluna; }
    
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    
    // Método auxiliar para identificar o assento
    public String getIdentificacao() {
        return coluna + numero;
    }
}