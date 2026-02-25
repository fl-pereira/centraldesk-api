package com.felipe.centraldesk.domain.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grupos")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    // Analistas podem estar em vários grupos
    @ManyToMany
    @JoinTable(
            name = "grupo_analistas",
            joinColumns = @JoinColumn(name = "grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "analista_id")
    )

    // Um grupo pode ter vários chamados
    private List<Chamado> chamados = new ArrayList<>();

    @ManyToMany(mappedBy = "grupos")
    private List<Analista> analistas;

    protected Grupo() {}

    public Grupo(String nome) {
        this.nome = nome;
    }

    public void adicionarAnalista(Analista analista) {
        this.analistas.add(analista);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
