package com.felipe.centraldesk.domain.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "analistas")
public class Analista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "analista")
    private List<Chamado> chamadosEmAtendimento = new ArrayList<>();

    @ManyToMany(mappedBy = "analistas")
    private List<Grupo> grupos = new ArrayList<>();

    protected Analista() {}

    public Analista(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
