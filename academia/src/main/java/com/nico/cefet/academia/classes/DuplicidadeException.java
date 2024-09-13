package com.nico.cefet.academia.classes;

public class DuplicidadeException extends Exception{
    public DuplicidadeException(){
        super("Já existe uma refeição cadastrada nesse horário.");
    }
}
