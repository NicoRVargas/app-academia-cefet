package com.nico.cefet.academia.service;

import com.nico.cefet.academia.exception.DuplicidadeException;

import java.util.ArrayList;
import java.util.List;

public class FichaTreino {
    private List<Treino>[] ficha;

    public FichaTreino() {
        ficha = new ArrayList[7];
        for (int i = 0; i < 7; i++) {
            ficha[i] = new ArrayList<>();
        }
    }

    public List<Treino>[] getFicha() {
        return ficha;
    }

    public void setFicha(List<Treino>[] ficha) {
        this.ficha = ficha;
    }

public void exercicioRepetido(int dia, Treino treino) throws DuplicidadeException {
    for (Treino t : ficha[dia]) {
        if (t.getDescricao().equals(treino.getDescricao())) {
            throw new DuplicidadeException();
        }
    }
}

    public int conversaoGrupoMuscular(String grupoMuscular) {
        return switch (grupoMuscular) {
            case "Peito" -> 0;
            case "Bíceps" -> 1;
            case "Tríceps" -> 2;
            case "Costas" -> 3;
            case "Perna" -> 4;
            case "Ombro" -> 5;
            default -> 6;
        };
    }

    public int      conversaoDiaSemana(String diaSemana) {
        return switch (diaSemana) {
            case "Domingo" -> 0;
            case "Segunda" -> 1;
            case "Terça" -> 2;
            case "Quarta" -> 3;
            case "Quinta" -> 4;
            case "Sexta" -> 5;
            case "Sábado" -> 6;
            default -> 7;
        };
    }

    public void cadastrarTreino(int dia, Treino treino) throws DuplicidadeException {
        try {
            this.exercicioRepetido(dia, treino);
            ficha[dia].add(treino);
        } catch (DuplicidadeException e) {
            System.err.println("É necessário alterar a data de cadastro ou sobrescrever o treino existente");
        }
    }

    public void sobrescreverTreino(int index, int dia, Treino treino) {
        ficha[dia].set(index, treino);
    }

    public void removerTreino(int index, int dia) {
        ficha[dia].remove(index);
    }

    public Treino consultarTreino(int index, int dia) {
        return ficha[dia].get(index);
    }
}