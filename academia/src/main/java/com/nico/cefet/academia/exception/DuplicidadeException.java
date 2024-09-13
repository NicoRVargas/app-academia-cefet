package com.nico.cefet.academia.exception;

public class DuplicidadeException extends Exception{
    public DuplicidadeException(){
        super("Já existe uma refeição cadastrada nesse horário.");
    }
}
