package com.nico.cefet.academia.service;

public class Treino {
    private String descricao;
    private String cargaMaxima;
    private String repeticoes;
    private String nome;
    private String grupoMuscular;

    public String getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(String repeticoes) {
        this.repeticoes = repeticoes;
    }

    public String getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public Treino() {
        this.descricao = null;
        this.cargaMaxima = null;
        this.repeticoes = null;
        this.nome = null;
        this.grupoMuscular = null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Treino(String descricao, String cargaMaxima, String repeticoes, String nome, String grupoMuscular) {
        this.descricao = descricao;
        this.cargaMaxima = cargaMaxima;
        this.repeticoes = repeticoes;
        this.nome = nome;
        this.grupoMuscular = grupoMuscular;

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCargaMaxima() {
        return cargaMaxima;
    }

    public void setCargaMaxima(String cargaMaxima) {
        this.cargaMaxima = cargaMaxima;
    }


}
