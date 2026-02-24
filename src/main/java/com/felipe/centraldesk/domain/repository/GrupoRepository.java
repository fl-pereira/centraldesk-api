package com.felipe.centraldesk.domain.repository;

import com.felipe.centraldesk.domain.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

}