package com.cinedata.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dados")
public class Dados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 14)
    private String cpf;
    
    @Column(nullable = false, length = 45)
    private String email;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(nullable = false)
    private LocalDate nascimento;
    
    // Construtores
    public Dados() {}
    
    public Dados(String cpf, String email, String nome, LocalDate nascimento) {
        this.cpf = cpf;
        this.email = email;
        this.nome = nome;
        this.nascimento = nascimento;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public LocalDate getNascimento() { return nascimento; }
    public void setNascimento(LocalDate nascimento) { this.nascimento = nascimento; }
}