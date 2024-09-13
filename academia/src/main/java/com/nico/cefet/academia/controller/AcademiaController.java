package com.nico.cefet.academia.controller;

import com.nico.cefet.academia.AcademiaApplication;
import com.nico.cefet.academia.exception.DuplicidadeException;
import com.nico.cefet.academia.service.FichaTreino;
import com.nico.cefet.academia.entity.Treino;
import com.nico.cefet.academia.service.Login;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class AcademiaController implements Initializable {
    private FichaTreino fichaTreino = FichaTreino.getInstance();
    private ToggleGroup grupoBotoes = new ToggleGroup();

    @FXML
    private ComboBox<String> diaDaSemana;

    @FXML
    private TextArea descricaoField;

    @FXML
    private TextArea nomeField;

    @FXML
    private TextArea cargaValue;

    @FXML
    private TextArea repeticoesValue;

    @FXML
    private RadioButton peito;

    @FXML
    private RadioButton biceps;

    @FXML
    private RadioButton triceps;

    @FXML
    private RadioButton costas;

    @FXML
    private RadioButton perna;

    @FXML
    private RadioButton ombro;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        peito.setToggleGroup(grupoBotoes);
        peito.setSelected(true);
        biceps.setToggleGroup(grupoBotoes);
        triceps.setToggleGroup(grupoBotoes);
        costas.setToggleGroup(grupoBotoes);
        perna.setToggleGroup(grupoBotoes);
        ombro.setToggleGroup(grupoBotoes);
        diaDaSemana.getItems().addAll("Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sabado");
        diaDaSemana.setValue("Domingo");
    }

    public void Incluir(){
        String botaoSelecionado = ((RadioButton)grupoBotoes.getSelectedToggle()).getText();
        String diaSelecionado = diaDaSemana.getSelectionModel().getSelectedItem();
        int diaSemanaSelecionado = fichaTreino.conversaoDiaSemana(diaSelecionado);
        Treino treino = new Treino(cargaValue.getText(), repeticoesValue.getText(), nomeField.getText(), botaoSelecionado);

        try{
            fichaTreino.cadastrarTreino(diaSemanaSelecionado, treino);
        } catch (DuplicidadeException e) {
            throw new RuntimeException(e);
        }
    }

    public void Salvar(){
        fichaTreino.escreverFicha(Login.getUsuarioLogado());
    }

    public void Remover(){
        String nomeTreino = nomeField.getText();
        String diaSelecionado = diaDaSemana.getSelectionModel().getSelectedItem();
        int diaSemanaSelecionado = fichaTreino.conversaoDiaSemana(diaSelecionado);

        fichaTreino.removerTreino(nomeTreino, diaSemanaSelecionado);
    }

    public void Voltar() {
        AcademiaApplication.changeScreen("home");
    }

    public void Limpar(){
        nomeField.setText("");
        cargaValue.setText("");
        repeticoesValue.setText("");
        peito.setSelected(true);
    }
}
