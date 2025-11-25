package com.cinedata.model;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "dadosId", nullable = false)
    private Dados dados;
    
    @Column(nullable = false, length = 45)
    private String nickname;
    
    @Column(nullable = false, length = 32)
    private String senha;
    
    @Column(nullable = false)
    private boolean ativo;
    
    @Column(nullable = false)
    private boolean gerente;
    
    // Construtores
    public Usuario() {}
    
    public Usuario(Dados dados, String nickname, String senha, boolean ativo, boolean gerente) {
        this.dados = dados;
        this.nickname = nickname;
        this.senha = senha;
        this.ativo = ativo;
        this.gerente = gerente;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Dados getDados() { return dados; }
    public void setDados(Dados dados) { this.dados = dados; }
    
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    
    public boolean isGerente() { return gerente; }
    public void setGerente(boolean gerente) { this.gerente = gerente; }
}