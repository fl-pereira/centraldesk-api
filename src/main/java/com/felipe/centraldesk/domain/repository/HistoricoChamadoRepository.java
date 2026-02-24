package com.felipe.centraldesk.domain.repository;

import com.felipe.centraldesk.domain.entity.HistoricoChamado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistoricoChamadoRepository extends JpaRepository<HistoricoChamado, Long> {

    List<HistoricoChamado> findByChamadoIdOrderByDataRegistroDesc(Long chamadoId);

}