package com.felipe.centraldesk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import com.felipe.centraldesk.domain.entity.Chamado;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

    @Query("""
        SELECT c FROM Chamado c
        WHERE c.status = 'RESOLVIDO'
        AND c.dataResolucao <= :limite
    """)
    List<Chamado> buscarResolvidosAntesDe(LocalDateTime limite);
}