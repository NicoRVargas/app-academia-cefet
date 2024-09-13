package com.nico.cefet.academia.entity;

public class Treino {
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
        this.cargaMaxima = null;
        this.repeticoes = null;
        this.nome = null;
        this.grupoMuscular = null;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao(){
        return nome + "\n" + cargaMaxima + "\n" + repeticoes + "\n" + grupoMuscular;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Treino(String cargaMaxima, String repeticoes, String nome, String grupoMuscular) {
        this.cargaMaxima = cargaMaxima;
        this.repeticoes = repeticoes;
        this.nome = nome;
        this.grupoMuscular = grupoMuscular;

    }

    public String getCargaMaxima() {
        return cargaMaxima;
    }

    public void setCargaMaxima(String cargaMaxima) {
        this.cargaMaxima = cargaMaxima;
    }


}
