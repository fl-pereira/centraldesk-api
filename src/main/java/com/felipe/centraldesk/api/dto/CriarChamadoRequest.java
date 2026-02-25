package com.felipe.centraldesk.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para criação de um chamado")
public class CriarChamadoRequest {

    @Schema(example = "Erro no sistema")
    private String titulo;

    @Schema(example = "Sistema não carrega")
    private String descricao;

    @Schema(example = "1")
    private Long usuarioId;

    @Schema(example = "1")
    private Long grupoId;

    public CriarChamadoRequest(){}

    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public Long getUsuarioId() { return usuarioId; }
    public Long getGrupoId() { return grupoId; }
}