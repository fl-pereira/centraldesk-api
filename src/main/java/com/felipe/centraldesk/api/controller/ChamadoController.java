package com.felipe.centraldesk.api.controller;

import com.felipe.centraldesk.api.dto.CriarChamadoRequest;
import com.felipe.centraldesk.domain.service.ChamadoService;
import com.felipe.centraldesk.domain.entity.Chamado;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    private final ChamadoService service;

    public ChamadoController(ChamadoService service) {
        this.service = service;
    }

    @Operation(
            summary = "Cria um novo chamado",
            description = "Cria um chamado vinculando a um usuário e grupo existente"
    )
    @ApiResponse(responseCode = "200", description = "Chamado criado com sucesso")
    @PostMapping
    public Chamado criar(@RequestBody CriarChamadoRequest request) {

        return service.criar(
                request.getTitulo(),
                request.getDescricao(),
                request.getUsuarioId(),
                request.getGrupoId()
        );
    }
}