package com.felipe.centraldesk.domain.repository;

import com.felipe.centraldesk.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}