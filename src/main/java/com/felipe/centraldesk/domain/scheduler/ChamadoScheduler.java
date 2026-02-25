package com.felipe.centraldesk.domain.scheduler;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.felipe.centraldesk.domain.service.ChamadoService;

@Component
@EnableScheduling
public class ChamadoScheduler {

    private final ChamadoService service;

    public ChamadoScheduler(ChamadoService service) {
        this.service = service;
    }

    // Executa todo dia às 2 da manhã
    @Scheduled(cron = "0 0 2 * * ?")
    public void executarFinalizacaoAutomatica() {
        service.finalizarChamadosResolvidos();
    }
}