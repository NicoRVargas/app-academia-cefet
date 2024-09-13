package com.nico.cefet.academia.controller;

import com.nico.cefet.academia.AcademiaApplication;
import com.nico.cefet.academia.exception.LoginIncorretoException;
import com.nico.cefet.academia.exception.UsuarioRegistradoException;
import com.nico.cefet.academia.service.FichaTreino;
import com.nico.cefet.academia.service.Login;
import com.opencsv.exceptions.CsvValidationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoginController {
    Login gerenciadorCadastro = new Login();
    FichaTreino fichaTreino = FichaTreino.getInstance();


    @FXML
    private Button loginButton;

    @FXML
    private TextArea loginField;

    @FXML
    private TextArea passwordField;

    @FXML
    private Button registerButton;

    @FXML
    void Login(ActionEvent event) {
        try {
            if(gerenciadorCadastro.login(loginField.getText(), passwordField.getText())) {
                System.out.println("logado");
                fichaTreino.carregarTreino(Login.getUsuarioLogado());
                AcademiaApplication.changeScreen("home");
            } else {
                throw new LoginIncorretoException("Login ou senha incorretos");
            }
        } catch (LoginIncorretoException e) {
            System.err.println(e.getMessage());
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void Register(ActionEvent event) {
        try {
            gerenciadorCadastro.register(loginField.getText(), passwordField.getText());
        } catch (UsuarioRegistradoException | IOException e) {
            System.err.println(e.getMessage());
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

}
