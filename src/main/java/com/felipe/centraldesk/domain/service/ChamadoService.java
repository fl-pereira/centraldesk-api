package com.felipe.centraldesk.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import com.felipe.centraldesk.domain.repository.ChamadoRepository;
import com.felipe.centraldesk.domain.entity.Chamado;

@Service
public class ChamadoService {

    private final ChamadoRepository repository;

    public ChamadoService(ChamadoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void finalizarChamadosResolvidos() {

        LocalDateTime limite = LocalDateTime.now().minusDays(3);

        List<Chamado> chamados =
                repository.buscarResolvidosAntesDe(limite);

        chamados.forEach(Chamado::finalizarAutomaticamente);
    }
}