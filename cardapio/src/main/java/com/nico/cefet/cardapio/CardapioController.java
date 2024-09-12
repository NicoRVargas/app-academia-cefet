package com.nico.cefet.cardapio;

import com.nico.cefet.cardapio.classes.Cardapio;
import com.nico.cefet.cardapio.classes.DuplicidadeException;
import com.nico.cefet.cardapio.classes.Refeicao;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CardapioController implements Initializable {
    private Cardapio cardapio = new Cardapio();
    private ToggleGroup grupoBotoes = new ToggleGroup();

    @FXML
    private ComboBox<String> diaDaSemana;

    @FXML
    private TextArea descricaoField;

    @FXML
    private TextArea valueDescription;

    @FXML
    private RadioButton cafeManha;

    @FXML
    private RadioButton lancheManha;

    @FXML
    private RadioButton almoco;

    @FXML
    private RadioButton lancheTarde;

    @FXML
    private RadioButton jantar;

    @FXML
    private RadioButton ceia;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cafeManha.setToggleGroup(grupoBotoes);
        cafeManha.setSelected(true);
        lancheManha.setToggleGroup(grupoBotoes);
        almoco.setToggleGroup(grupoBotoes);
        lancheTarde.setToggleGroup(grupoBotoes);
        jantar.setToggleGroup(grupoBotoes);
        ceia.setToggleGroup(grupoBotoes);
        diaDaSemana.getItems().addAll("Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sabado");
    }

    public void Incluir(){
        String botaoSelecionado = ((RadioButton)grupoBotoes.getSelectedToggle()).getText();
        String diaSelecionado = diaDaSemana.getSelectionModel().getSelectedItem();
        int tipoSelecionado = cardapio.conversaotipoRefeicao(botaoSelecionado);
        int diaSemanaSelecionado = cardapio.conversaoDiaSemana(diaSelecionado);
        Refeicao refeicao = new Refeicao(descricaoField.getText(), valueDescription.getText());

        try{
            cardapio.cadastrarRefeicao(tipoSelecionado, diaSemanaSelecionado, refeicao);
        } catch (DuplicidadeException e) {
            throw new RuntimeException(e);
        }
    }

    public void Remover(){
        String botaoSelecionado = ((RadioButton)grupoBotoes.getSelectedToggle()).getText();
        String diaSelecionado = diaDaSemana.getSelectionModel().getSelectedItem();
        int tipoSelecionado = cardapio.conversaotipoRefeicao(botaoSelecionado);
        int diaSemanaSelecionado = cardapio.conversaoDiaSemana(diaSelecionado);
        cardapio.removerRefeicao(tipoSelecionado, diaSemanaSelecionado);
    }

    public void Consultar(){
        String botaoSelecionado = ((RadioButton)grupoBotoes.getSelectedToggle()).getText();
        String diaSelecionado = diaDaSemana.getSelectionModel().getSelectedItem();
        int tipoSelecionado = cardapio.conversaotipoRefeicao(botaoSelecionado);
        int diaSemanaSelecionado = cardapio.conversaoDiaSemana(diaSelecionado);
        Refeicao refeicao = cardapio.consultarRefeicao(tipoSelecionado, diaSemanaSelecionado);

        descricaoField.setText(refeicao.getDescricao());
        valueDescription.setText(refeicao.getValorCalorico());
    }

    public void Alterar(){
        String botaoSelecionado = ((RadioButton)grupoBotoes.getSelectedToggle()).getText();
        String diaSelecionado = diaDaSemana.getSelectionModel().getSelectedItem();
        int tipoSelecionado = cardapio.conversaotipoRefeicao(botaoSelecionado);
        int diaSemanaSelecionado = cardapio.conversaoDiaSemana(diaSelecionado);

        Refeicao refeicao = new Refeicao(descricaoField.getText(), valueDescription.getText());

        cardapio.sobrescreverRefeicao(tipoSelecionado, diaSemanaSelecionado, refeicao);
    }

    public void Limpar(){
        descricaoField.setText("");
        valueDescription.setText("");
        cafeManha.setSelected(true);
    }
}
