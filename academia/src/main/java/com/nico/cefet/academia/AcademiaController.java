package com.nico.cefet.academia;

import com.nico.cefet.academia.classes.DuplicidadeException;
import com.nico.cefet.academia.classes.FichaTreino;
import com.nico.cefet.academia.classes.Treino;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class AcademiaController implements Initializable {
    private FichaTreino fichaTreino = new FichaTreino();
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
    }

    public void Incluir(){
        String botaoSelecionado = ((RadioButton)grupoBotoes.getSelectedToggle()).getText();
        String diaSelecionado = diaDaSemana.getSelectionModel().getSelectedItem();
        int diaSemanaSelecionado = fichaTreino.conversaoDiaSemana(diaSelecionado);
        Treino treino = new Treino(descricaoField.getText(), cargaValue.getText(), repeticoesValue.getText(), nomeField.getText(), botaoSelecionado);

        try{
            fichaTreino.cadastrarTreino(diaSemanaSelecionado, treino);
            System.out.println(fichaTreino.getFicha()[diaSemanaSelecionado].get(0));
        } catch (DuplicidadeException e) {
            throw new RuntimeException(e);
        }
    }

//    public void Remover(){
//        String botaoSelecionado = ((RadioButton)grupoBotoes.getSelectedToggle()).getText();
//        String diaSelecionado = diaDaSemana.getSelectionModel().getSelectedItem();
//        int tipoSelecionado = fichaTreino.conversaoGrupoMuscular(botaoSelecionado);
//        int diaSemanaSelecionado = fichaTreino.conversaoDiaSemana(diaSelecionado);
//        fichaTreino.removerTreino(tipoSelecionado, diaSemanaSelecionado);
//    }
//
//    public void Consultar(){
//        String botaoSelecionado = ((RadioButton)grupoBotoes.getSelectedToggle()).getText();
//        String diaSelecionado = diaDaSemana.getSelectionModel().getSelectedItem();
//        int tipoSelecionado = fichaTreino.conversaoDiaSemana(botaoSelecionado);
//        int diaSemanaSelecionado = fichaTreino.conversaoDiaSemana(diaSelecionado);
//        Treino treino = fichaTreino.consultarTreino(tipoSelecionado, diaSemanaSelecionado);
//
//        descricaoField.setText(treino.getDescricao());
//        nomeField.setText(treino.getNome());
//        cargaValue.setText(treino.getCargaMaxima());
//    }
//
//    public void Alterar(){
//        String botaoSelecionado = ((RadioButton)grupoBotoes.getSelectedToggle()).getText();
//        String diaSelecionado = diaDaSemana.getSelectionModel().getSelectedItem();
//        int tipoSelecionado = fichaTreino.conversaoGrupoMuscular(botaoSelecionado);
//        int diaSemanaSelecionado = fichaTreino.conversaoDiaSemana(diaSelecionado);
//
//        Treino treino = new Treino(descricaoField.getText(), cargaValue.getText(), repeticoesValue.getText(), nomeField.getText());
//
//        fichaTreino.sobrescreverTreino(tipoSelecionado, diaSemanaSelecionado, treino);
//    }

    public void Voltar() {
        System.out.println("Voltando...");
    }

    public void Limpar(){
        descricaoField.setText("");
        nomeField.setText("");
        cargaValue.setText("");
        peito.setSelected(true);
    }
}
