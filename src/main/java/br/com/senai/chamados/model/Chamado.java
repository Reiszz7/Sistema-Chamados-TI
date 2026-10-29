package br.com.senai.chamados.model;

import java.time.LocalDateTime;

public class Chamado {

    private int id;
    private String solicitante;
    private String descricao;
    private Prioridade prioridade;
    private StatusChamado status;
    private LocalDateTime dataAbertura;

    public Chamado(int id, String solicitante, String descricao,
                   Prioridade prioridade, StatusChamado status,
                   LocalDateTime dataAbertura) {
        this.id = id;
        this.solicitante = solicitante;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.status = status;
        this.dataAbertura = dataAbertura;
    }

    public int getId() {
        return id;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public String getDescricao() {
        return descricao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }
}
