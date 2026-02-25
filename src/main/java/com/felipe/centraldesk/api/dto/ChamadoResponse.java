package com.felipe.centraldesk.api.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ChamadoResponse {

    private Long id;
    private String titulo;
    private String descricao;
    private String status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataFinalizacao;

    private UsuarioResumo usuario;
    private GrupoResumo grupo;

    private List<HistoricoResumo> historicos;

    public record UsuarioResumo(Long id, String nome) {}
    public record GrupoResumo(Long id, String nome) {}
    public record HistoricoResumo(String mensagem, LocalDateTime dataRegistro) {}

    public ChamadoResponse(){

    }

    public ChamadoResponse(
            Long id,
            String titulo,
            String descricao,
            String status,
            LocalDateTime dataCriacao,
            LocalDateTime dataFinalizacao,
            UsuarioResumo usuario,
            GrupoResumo grupo,
            List<HistoricoResumo> historicos
    ) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.dataFinalizacao = dataFinalizacao;
        this.usuario = usuario;
        this.grupo = grupo;
        this.historicos = historicos;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getStatus() { return status; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public LocalDateTime getDataFinalizacao() { return dataFinalizacao; }
    public UsuarioResumo getUsuario() { return usuario; }
    public GrupoResumo getGrupo() { return grupo; }
    public List<HistoricoResumo> getHistoricos() { return historicos; }
}