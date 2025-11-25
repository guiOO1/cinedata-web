package com.cinedata.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "assentoId", nullable = false)
    private Assento assento;
    
    @ManyToOne
    @JoinColumn(name = "sessaoId", nullable = false)
    private Sessao sessao;
    
    @ManyToOne
    @JoinColumn(name = "usuarioId", nullable = false)
    private Usuario usuario;
    
    @Column(name = "hora_reserva", nullable = false)
    private LocalDateTime horaReserva;
    
    @Column(nullable = false)
    private boolean valida;
    
    // Construtores
    public Reserva() {}
    
    public Reserva(Assento assento, Sessao sessao, Usuario usuario, LocalDateTime horaReserva, boolean valida) {
        this.assento = assento;
        this.sessao = sessao;
        this.usuario = usuario;
        this.horaReserva = horaReserva;
        this.valida = valida;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Assento getAssento() { return assento; }
    public void setAssento(Assento assento) { this.assento = assento; }
    
    public Sessao getSessao() { return sessao; }
    public void setSessao(Sessao sessao) { this.sessao = sessao; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    
    public LocalDateTime getHoraReserva() { return horaReserva; }
    public void setHoraReserva(LocalDateTime horaReserva) { this.horaReserva = horaReserva; }
    
    public boolean isValida() { return valida; }
    public void setValida(boolean valida) { this.valida = valida; }
}