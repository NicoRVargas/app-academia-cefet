package com.nico.cefet.academia.service;

import com.nico.cefet.academia.exception.LoginIncorretoException;
import com.nico.cefet.academia.exception.UsuarioRegistradoException;

public class Login {
    public void login(String login, String senha) throws LoginIncorretoException {
        System.out.println("Login: " + login + " Senha: " + senha);
    }

    public void register(String login, String senha) throws UsuarioRegistradoException {
        System.out.println("Login: " + login + " Senha: " + senha);
    }
}
