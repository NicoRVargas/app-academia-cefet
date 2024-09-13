package com.nico.cefet.academia.service;

import com.nico.cefet.academia.entity.Treino;
import com.nico.cefet.academia.exception.DuplicidadeException;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FichaTreino {
    private Treino[][] ficha;
    private static final String CSV_FILE_PATH = "src/main/resources/com/nico/cefet/academia/treino.csv";
    private static FichaTreino instance;

    private FichaTreino() {
        ficha = new Treino[7][7];
    }

    public static FichaTreino getInstance() {
        if (instance == null) {
            instance = new FichaTreino();
        }
        return instance;
    }

    public Treino[][] getFicha() {
        return ficha;
    }


    public void exercicioRepetido(int dia, Treino treino) throws DuplicidadeException {
        for (Treino t : ficha[dia]) {
            if (t != null && t.getNome().equals(treino.getNome())) {
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

    public int conversaoDiaSemana(String diaSemana) {
        return switch (diaSemana) {
            case "Domingo" -> 0;
            case "Segunda" -> 1;
            case "Terça" -> 2;
            case "Quarta" -> 3;
            case "Quinta" -> 4;
            case "Sexta" -> 5;
            case "Sabado" -> 6;
            default -> throw new IllegalStateException("Unexpected value: " + diaSemana);
        };
    }

    public void cadastrarTreino(int dia, Treino treino) throws DuplicidadeException {
        try {
            this.exercicioRepetido(dia, treino);
            for (int i = 0; i < ficha[dia].length; i++) {
                if (ficha[dia][i] == null) {
                    ficha[dia][i] = treino;
                    break;
                }
            }
        } catch (DuplicidadeException e) {
            System.err.println("É necessário alterar a data de cadastro ou sobrescrever o treino existente");
        }
    }

    public List<Integer> findLines(String identifier) {
        List<Integer> lineNumbers = new ArrayList<>();
        int lineNumber = 0;

        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line[0].equals(identifier)) {
                    lineNumbers.add(lineNumber);
                }
                lineNumber++;
            }
        } catch (IOException | CsvException e) {
            System.err.println("Error finding lines: " + e.getMessage());
        }

        return lineNumbers;
    }

    public void deleteLines(List<Integer> linesToDelete) {
        try {
            CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH));
            List<String[]> data = reader.readAll();
            reader.close();

            // Organiza as linhas para deletar em ordem decrescente para evitar problemas de indexação
            Collections.sort(linesToDelete, Collections.reverseOrder());

            // Remove as linhas especificadas
            for (int lineToDelete : linesToDelete) {
                data.remove(lineToDelete);
            }

            // Escreve os dados modificados no arquivo de saida CSV
            FileWriter fileWriter = new FileWriter(CSV_FILE_PATH, false);
            CSVWriter writer = new CSVWriter(fileWriter);
            writer.writeAll(data);
            writer.close();

        } catch (IOException | CsvException e) {
            System.err.println("Error deleting lines: " + e.getMessage());
        }
    }

    public List<String[]> readLines(String identifier) {
        List<String[]> lines = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line[0].equals(identifier)) {
                    lines.add(line);
                }
            }
        } catch (IOException | CsvException e) {
            System.err.println("Error finding lines: " + e.getMessage());
        }

        return lines;
    }

    public void carregarTreino(String usuario){
        List<String[]> treinosUsuario = readLines(usuario);
        for (String[] line : treinosUsuario) {
            Treino treino = textoParaTreino(line);
            int dia = Integer.parseInt(line[1]);
            int index = Integer.parseInt(line[2]);
            ficha[dia][index] = treino;
        }
    }

    public Treino textoParaTreino(String[] line) {
        String nome = line[3];
        String cargaMaxima = line[4];
        String repeticoes = line[5];
        String grupoMuscular = line[6];

        return new Treino(cargaMaxima, repeticoes, nome, grupoMuscular);
    }

    public void escreverFicha(String identifier) {
        File treinoFile = new File(CSV_FILE_PATH);
        if (!treinoFile.exists()) {
            try {
                treinoFile.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        }

        try (FileWriter fileWriter = new FileWriter(CSV_FILE_PATH, true);
             CSVWriter writer = new CSVWriter(fileWriter)) {
            // Checa se o arquivo está vazio
            if (treinoFile.length() == 0) {
                String[] header = {"Identifier", "Dia", "Numero do Treino", "Nome", "Carga Maxima", "Repetições", "Grupo Muscular"};
                writer.writeNext(header);
            }

            List<Integer> lineNumberCollection = this.findLines(identifier);

            if (!lineNumberCollection.isEmpty()) {
                deleteLines(lineNumberCollection);
            }

            List<String[]> newRows = new ArrayList<>();

            for (int i = 0; i < this.ficha.length; i++) {
                for (int j = 0; j < this.ficha[i].length; j++) {
                    if (ficha[i][j] != null) {
                        String[] data = {identifier, String.valueOf(i), String.valueOf(j), this.ficha[i][j].getNome(),this.ficha[i][j].getCargaMaxima(), this.ficha[i][j].getRepeticoes() ,this.ficha[i][j].getGrupoMuscular()};
                        newRows.add(data);
                    }
                }
            }

            writer.writeAll(newRows);
            System.out.println("File " + CSV_FILE_PATH + " saved successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void removerTreino(String nome, int dia) {
        for(int i = 0; i < ficha[dia].length; i++) {
            if(ficha[dia][i] != null && ficha[dia][i].getNome().equals(nome)) {
                ficha[dia][i] = null;
                break;
            }
        }
    }

    public Treino consultarTreino(int index, int dia) {
        return ficha[dia][index];
    }
}