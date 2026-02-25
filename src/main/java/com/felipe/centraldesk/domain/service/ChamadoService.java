package com.felipe.centraldesk.domain.service;

import com.felipe.centraldesk.domain.entity.Analista;
import com.felipe.centraldesk.domain.exception.RecursoNaoEncontradoException;
import com.felipe.centraldesk.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.centraldesk.domain.repository.ChamadoRepository;
import com.felipe.centraldesk.domain.repository.GrupoRepository;
import com.felipe.centraldesk.domain.repository.AnalistaRepository;

import com.felipe.centraldesk.domain.entity.Chamado;
import com.felipe.centraldesk.domain.entity.Usuario;
import com.felipe.centraldesk.domain.entity.Grupo;

import com.felipe.centraldesk.api.dto.ChamadoResponse;

import java.util.List;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final GrupoRepository grupoRepository;
    private final AnalistaRepository analistaRepository;

    public ChamadoService(
            ChamadoRepository chamadoRepository,
            UsuarioRepository usuarioRepository,
            GrupoRepository grupoRepository,
            AnalistaRepository analistaRepository) {

        this.chamadoRepository = chamadoRepository;
        this.usuarioRepository = usuarioRepository;
        this.grupoRepository = grupoRepository;
        this.analistaRepository = analistaRepository;
    }

    @Transactional
    public ChamadoResponse criar(String titulo, String descricao, Long usuarioId, Long grupoId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));

        Chamado chamado = new Chamado(titulo, descricao, usuario, grupo);

        Chamado salvo = chamadoRepository.save(chamado);
        return toResponse(salvo);
    }

    public List<ChamadoResponse> listarTodos() {
        return chamadoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ChamadoResponse buscarPorId(Long id) {

        Chamado chamado = chamadoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Chamado não encontrado"));

        return toResponse(chamado);
    }

    @Transactional
    public ChamadoResponse assumirChamado(Long chamadoId, Long analistaId) {

        Chamado chamado = chamadoRepository.findById(chamadoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Chamado não encontrado"));

        Analista analista = analistaRepository.findById(analistaId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Analista não encontrado"));

        chamado.assumir(analista); // regra dentro da entidade

        return toResponse(chamado);
    }

    @Transactional
    public ChamadoResponse resolverChamado(Long chamadoId) {

        Chamado chamado = chamadoRepository.findById(chamadoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Chamado não encontrado"));

        chamado.resolver();

        return toResponse(chamado);
    }

    public void finalizarChamadosResolvidos() {
        // implementação futura
    }

    private ChamadoResponse toResponse(Chamado chamado) {

        return new ChamadoResponse(
                chamado.getId(),
                chamado.getTitulo(),
                chamado.getDescricao(),
                chamado.getStatus().name(),
                chamado.getDataCriacao(),
                chamado.getDataFinalizacao(),
                new ChamadoResponse.UsuarioResumo(
                        chamado.getUsuario().getId(),
                        chamado.getUsuario().getNome()
                ),
                new ChamadoResponse.GrupoResumo(
                        chamado.getGrupo().getId(),
                        chamado.getGrupo().getNome()
                ),
                chamado.getHistoricos().stream()
                        .map(h -> new ChamadoResponse.HistoricoResumo(
                                h.getMensagem(),
                                h.getDataRegistro()
                        ))
                        .toList()
        );
    }
}