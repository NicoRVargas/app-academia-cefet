package com.nico.cefet.cardapio.classes;

public class Cardapio {
    private Refeicao[][] cardapio;

    public Cardapio() {
        cardapio = new Refeicao[6][7];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                cardapio[i][j] = new Refeicao();
            }
        }
    }

    public void refeicaoVazia(int tipoRefeicao, int dia) throws DuplicidadeException {
        //Confere se ja tem alguma refeicao cadastrada
        //no dia em questao

        //Caso haja alguma refeicao cadastrada, a excecao é lancada
        if (cardapio[tipoRefeicao][dia].getDescricao() != null) {
            throw new DuplicidadeException();
        } else if (cardapio[tipoRefeicao][dia].getValorCalorico() != null) {
            throw new DuplicidadeException();
        }

    }

    public int conversaotipoRefeicao(String tipoRefeicao) {

        return switch (tipoRefeicao) {
            case "Café da Manhã" -> 0;
            case "Lanche Manhã" -> 1;
            case "Almoço" -> 2;
            case "Lanche Tarde" -> 3;
            case "Jantar" -> 4;
            case "Ceia" -> 5;
            default -> 6;
        };
    }

    public int conversaoDiaSemana(String diaSemana) {
        int numeroDia = switch (diaSemana) {
            case "Domingo" -> 0;
            case "Segunda" -> 1;
            case "Terça" -> 2;
            case "Quarta" -> 3;
            case "Quinta" -> 4;
            case "Sexta" -> 5;
            case "Sábado" -> 6;
            default -> 7;
        };

        return numeroDia;
    }

    public void cadastrarRefeicao(int tipoRefeicao, int dia, Refeicao refeicao) throws DuplicidadeException {

        try {
            this.refeicaoVazia(tipoRefeicao, dia);

            cardapio[tipoRefeicao][dia].setDescricao(refeicao.getDescricao());
            cardapio[tipoRefeicao][dia].setValorCalorico(refeicao.getValorCalorico());
        } catch (DuplicidadeException e) {
            System.err.println("É necessário alterar a data de cadastro ou sobrescrever a refeicao existente");
        }


    }

    public void sobrescreverRefeicao(int tipoRefeicao, int dia, Refeicao refeicao) {

        cardapio[tipoRefeicao][dia].setDescricao(refeicao.getDescricao());
        cardapio[tipoRefeicao][dia].setValorCalorico(refeicao.getValorCalorico());

    }

    public void removerRefeicao(int tipoRefeicao, int dia) {
        cardapio[tipoRefeicao][dia].setDescricao(null);
        cardapio[tipoRefeicao][dia].setValorCalorico(null);
    }

    public Refeicao consultarRefeicao(int tipoRefeicao, int dia) {

        return cardapio[tipoRefeicao][dia];
    }

    public Refeicao[][] getCardapio() {
        return cardapio;
    }

    public void setCardapio(Refeicao[][] cardapio) {
        this.cardapio = cardapio;
    }
}
