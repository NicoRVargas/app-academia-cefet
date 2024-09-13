package com.nico.cefet.academia.classes;

public class Treino {
    private String descricao;
    private String cargaMaxima;

    public Treino(String descricao, String cargaMaxima){
        this.descricao = descricao;
        this.cargaMaxima = cargaMaxima;
    }

    public Treino(){
        this.descricao = null;
        this.cargaMaxima = null;
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
