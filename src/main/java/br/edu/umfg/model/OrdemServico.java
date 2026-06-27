package br.edu.umfg.model;

import java.math.BigDecimal;

public class OrdemServico {

    private int id;
    private int idVeiculo;
    private String descricao;
    private BigDecimal valor;
    private String status;

    public OrdemServico() {
    }

    public OrdemServico(int id, int idVeiculo, String descricao, BigDecimal valor, String status) {
        this.id = id;
        this.idVeiculo = idVeiculo;
        this.descricao = descricao;
        this.valor = valor;
        this.status = status;
    }

    public OrdemServico(int idVeiculo, String descricao, BigDecimal valor, String status) {
        this.idVeiculo = idVeiculo;
        this.descricao = descricao;
        this.valor = valor;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
