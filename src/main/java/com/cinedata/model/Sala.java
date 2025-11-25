package com.cinedata.model;

import javax.persistence.*;

@Entity
@Table(name = "sala")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "capacidade_max", nullable = false)
    private int capacidadeMax;
    
    @Column(nullable = false)
    private int numero;
    
    // Construtores
    public Sala() {}
    
    public Sala(int capacidadeMax, int numero) {
        this.capacidadeMax = capacidadeMax;
        this.numero = numero;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public int getCapacidadeMax() { return capacidadeMax; }
    public void setCapacidadeMax(int capacidadeMax) { this.capacidadeMax = capacidadeMax; }
    
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
}