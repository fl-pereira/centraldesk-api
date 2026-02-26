package com.felipe.centraldesk.api.controller;

import com.felipe.centraldesk.api.dto.ChamadoResponse;
import com.felipe.centraldesk.api.dto.CriarChamadoRequest;
import com.felipe.centraldesk.domain.enums.StatusChamado;
import com.felipe.centraldesk.domain.service.ChamadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ChamadoResponse> criar(
            @Valid @RequestBody CriarChamadoRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.criar(request));
    }

    @GetMapping
    public ResponseEntity<Page<ChamadoResponse>> listar(
            @RequestParam(required = false) StatusChamado status,
            Pageable pageable) {
        return ResponseEntity.ok(service.listar(status, pageable));
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