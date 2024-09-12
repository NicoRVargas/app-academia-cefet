package com.nico.cefet.cardapio.classes;

public class Refeicao {
    private String descricao;
    private String valorCalorico;

    public Refeicao(String descricao, String valorCalorico){
        this.descricao = descricao;
        this.valorCalorico = valorCalorico;
    }

    public Refeicao(){
        this.descricao = null;
        this.valorCalorico = null;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValorCalorico() {
        return valorCalorico;
    }

    public void setValorCalorico(String valorCalorico) {
        this.valorCalorico = valorCalorico;
    }


}
