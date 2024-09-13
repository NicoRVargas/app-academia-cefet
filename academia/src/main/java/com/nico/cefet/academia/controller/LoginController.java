package com.nico.cefet.academia.controller;

import com.nico.cefet.academia.exception.LoginIncorretoException;
import com.nico.cefet.academia.exception.UsuarioRegistradoException;
import com.nico.cefet.academia.service.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class LoginController {
    Login login = new Login();

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
            login.login(loginField.getText(), passwordField.getText());
        } catch (LoginIncorretoException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Register(ActionEvent event) {
        try {
            login.register(loginField.getText(), passwordField.getText());
        } catch (UsuarioRegistradoException e) {
            e.printStackTrace();
        }
    }

}
