package com.felipe.centraldesk.domain.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import com.felipe.centraldesk.domain.enums.StatusChamado;
import com.felipe.centraldesk.domain.entity.HistoricoChamado;

@Entity
@Table(name = "chamados")
public class Chamado {
    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusChamado status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataResolucao;
    private LocalDateTime dataFinalizacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "analista_id")
    private Analista analista;

    @OneToMany(mappedBy = "chamado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoricoChamado> historicos = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    protected Chamado() {}

    // CONSTRUTOR
    public Chamado(String titulo, String descricao, Usuario usuario, Grupo grupo) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.grupo = grupo;
        this.status = StatusChamado.ABERTO;
        this.dataCriacao = LocalDateTime.now();
        this.adicionarHistorico("Chamado criado com status ABERTO");
    }

    // GETTERS E SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public void setStatus(StatusChamado status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Analista getAnalista() {
        return analista;
    }

    public void setAnalista(Analista analista) {
        this.analista = analista;
    }

    public List<HistoricoChamado> getHistoricos() {
        return historicos;
    }

    public void setHistoricos(List<HistoricoChamado> historicos) {
        this.historicos = historicos;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    // MÉTODOS
    public void adicionarHistorico(String mensagem) {
        HistoricoChamado historico = new HistoricoChamado(mensagem, this);
        this.historicos.add(historico);
    }

    public void atribuirAnalista(Analista analista) {
        this.analista = analista;
        alterarStatus(StatusChamado.EM_ATENDIMENTO);
    }

    public void finalizar() {

        if (this.status == StatusChamado.FINALIZADO) {
            throw new IllegalStateException("Chamado já finalizado.");
        }

        alterarStatus(StatusChamado.FINALIZADO);
        this.dataFinalizacao = LocalDateTime.now();
    }

    public void transferirGrupo(Grupo novoGrupo) {

        if (this.grupo.equals(novoGrupo)) {
            return;
        }

        Grupo grupoAnterior = this.grupo;
        this.grupo = novoGrupo;

        // Remove analista atual
        if (this.analista != null) {
            this.analista = null;
        }

        // Volta para aberto
        alterarStatus(StatusChamado.ABERTO);

        this.adicionarHistorico(
                "Chamado transferido do grupo "
                        + grupoAnterior.getNome()
                        + " para "
                        + novoGrupo.getNome()
        );
    }

    private void validarNaoEncerrado() {
        if (this.status == StatusChamado.FINALIZADO ||
                this.status == StatusChamado.CANCELADO) {

            throw new IllegalStateException("Chamado encerrado. Apenas consulta permitida.");
        }
    }

    public void cancelar() {

        validarNaoEncerrado();

        alterarStatus(StatusChamado.CANCELADO);

        this.dataFinalizacao = LocalDateTime.now();

        this.adicionarHistorico("Chamado cancelado.");
    }

    public void finalizarAutomaticamente() {

        if (this.status != StatusChamado.RESOLVIDO) {
            return;
        }

        alterarStatus(StatusChamado.FINALIZADO);
        this.dataFinalizacao = LocalDateTime.now();

        this.adicionarHistorico(
                "Chamado finalizado automaticamente após 3 dias."
        );
    }

    private void alterarStatus(StatusChamado novoStatus) {

        if (this.status == novoStatus) {
            return;
        }

        StatusChamado statusAnterior = this.status;
        this.status = novoStatus;

        this.adicionarHistorico(
                "Status alterado de " + statusAnterior + " para " + novoStatus
        );
    }

    public void assumir(Analista analista) {

        if (this.status == StatusChamado.FINALIZADO) {
            throw new IllegalStateException("Chamado já finalizado");
        }

        if (this.status == StatusChamado.CANCELADO) {
            throw new IllegalStateException("Chamado cancelado não pode ser assumido");
        }

        if (this.analista != null) {
            throw new IllegalStateException("Chamado já possui analista atribuído");
        }

        if (!analista.getGrupos().contains(this.grupo)) {
            throw new IllegalStateException("Analista não pertence ao grupo do chamado");
        }

        this.analista = analista;
        this.status = StatusChamado.EM_ATENDIMENTO;

        this.historicos.add(
                new HistoricoChamado("Chamado assumido por " + analista.getNome(), this)
        );
    }

    public void resolver() {

        if (this.status == StatusChamado.FINALIZADO) {
            throw new IllegalStateException("Chamado já finalizado");
        }

        if (this.status == StatusChamado.CANCELADO) {
            throw new IllegalStateException("Chamado cancelado não pode ser resolvido");
        }

        if (this.status != StatusChamado.EM_ATENDIMENTO) {
            throw new IllegalStateException("Chamado precisa estar em atendimento para ser resolvido");
        }

        if (this.analista == null) {
            throw new IllegalStateException("Chamado precisa ter analista para ser resolvido");
        }

        this.status = StatusChamado.RESOLVIDO;
        this.dataResolucao = LocalDateTime.now();

        this.historicos.add(
                new HistoricoChamado("Chamado resolvido", this)
        );
    }
}