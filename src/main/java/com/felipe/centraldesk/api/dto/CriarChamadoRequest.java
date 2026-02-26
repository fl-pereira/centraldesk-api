package com.felipe.centraldesk.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados para criação de um chamado")
public class CriarChamadoRequest {

    @NotBlank(message = "Título é obrigatório")
    @Size(min = 5, message = "Título deve ter no mínimo 5 caractéres")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatório")
    @Size(min = 10, message = "Descrição deve ter no mínimo 10 caractéres")
    private String descricao;

    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "Grupo é obrigatório")
    private Long grupoId;

    public CriarChamadoRequest(){}

    public String getTitulo() {
        return titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public Long getUsuarioId() {
        return usuarioId;
    }
    public Long getGrupoId() {
        return grupoId;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setGrupoId(Long grupoId) {
        this.grupoId = grupoId;
    }
}