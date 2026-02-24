package com.felipe.centraldesk.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.felipe.centraldesk.domain.entity.Chamado;

@Entity
@Table(name = "historicos_chamado")
public class HistoricoChamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String mensagem;

    private LocalDateTime dataRegistro;

    @ManyToOne(optional = false)
    @JoinColumn(name = "chamado_id")
    private Chamado chamado;

    protected HistoricoChamado() {}

    public HistoricoChamado(String mensagem, Chamado chamado) {
        this.mensagem = mensagem;
        this.chamado = chamado;
        this.dataRegistro = LocalDateTime.now();
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }
}