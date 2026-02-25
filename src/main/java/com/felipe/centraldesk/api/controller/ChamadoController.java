package com.felipe.centraldesk.api.controller;

import com.felipe.centraldesk.api.dto.ChamadoResponse;
import com.felipe.centraldesk.api.dto.CriarChamadoRequest;
import com.felipe.centraldesk.domain.service.ChamadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ChamadoResponse criar(@RequestBody CriarChamadoRequest request) {

        return service.criar(
                request.getTitulo(),
                request.getDescricao(),
                request.getUsuarioId(),
                request.getGrupoId()
        );
    }

    @GetMapping
    public ResponseEntity<List<ChamadoResponse>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PatchMapping("/{id}/assumir")
    public ResponseEntity<ChamadoResponse> assumir(
            @PathVariable Long id,
            @RequestParam Long analistaId) {

        return ResponseEntity.ok(service.assumirChamado(id, analistaId));
    }

    @PatchMapping("/{id}/resolver")
    public ResponseEntity<ChamadoResponse> resolver(@PathVariable Long id) {
        return ResponseEntity.ok(service.resolverChamado(id));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ChamadoResponse> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(service.cancelarChamado(id));
    }

    @PatchMapping("/{id}/reabrir")
    public ResponseEntity<ChamadoResponse> reabrir(@PathVariable Long id) {
        return ResponseEntity.ok(service.reabrirChamado(id));
    }
}