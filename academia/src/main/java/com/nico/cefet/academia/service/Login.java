package com.nico.cefet.academia.service;

import com.nico.cefet.academia.exception.UsuarioRegistradoException;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;

public class Login {

    private static final String CSV_FILE_PATH = "src/main/resources/com/nico/cefet/academia/users.csv";
    private static String usuarioLogado;

    public boolean login(String login, String senha) throws CsvValidationException, IOException {
        File userFile = new File(CSV_FILE_PATH);
        if (!userFile.exists()) {
            throw new FileNotFoundException("User file not found: " + CSV_FILE_PATH);
        }

        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine[0].equals(login) && nextLine[1].equals(senha)) {
                    Login.usuarioLogado = nextLine[0];
                    return true;
                }
            }
        }
        return false;
    }

    public static String getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(String usuarioLogado) {
        Login.usuarioLogado = usuarioLogado;
    }

    public boolean userExists(String login) throws CsvValidationException, IOException {
        File userFile = new File(CSV_FILE_PATH);
        if (!userFile.exists()) {
            return false;
        }

        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine[0].equals(login)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean register(String login, String senha) throws IOException, UsuarioRegistradoException, CsvValidationException {


        File userFile = new File(CSV_FILE_PATH);
        if (!userFile.exists()) {
            userFile.createNewFile();
        }

        if (userExists(login)) {
            throw new UsuarioRegistradoException("Usuário Já Registrado.");
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_PATH, true))) {
            writer.writeNext(new String[]{login, senha});
        }
        return true;
    }
}