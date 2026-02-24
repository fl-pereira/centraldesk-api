package com.felipe.centraldesk.domain.service;

import com.felipe.centraldesk.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.centraldesk.domain.repository.ChamadoRepository;
import com.felipe.centraldesk.domain.repository.UsuarioRepository;
import com.felipe.centraldesk.domain.repository.GrupoRepository;

import com.felipe.centraldesk.domain.entity.Chamado;
import com.felipe.centraldesk.domain.entity.Usuario;
import com.felipe.centraldesk.domain.entity.Grupo;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final GrupoRepository grupoRepository;

    public ChamadoService(
            ChamadoRepository chamadoRepository,
            UsuarioRepository usuarioRepository,
            GrupoRepository grupoRepository) {

        this.chamadoRepository = chamadoRepository;
        this.usuarioRepository = usuarioRepository;
        this.grupoRepository = grupoRepository;
    }

    @Transactional
    public Chamado criar(String titulo, String descricao, Long usuarioId, Long grupoId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));

        Chamado chamado = new Chamado(titulo, descricao, usuario, grupo);

        return chamadoRepository.save(chamado);
    }

    public void finalizarChamadosResolvidos() {
        // implementação futura
    }
}