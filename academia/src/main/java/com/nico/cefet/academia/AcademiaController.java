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
        int tipoSelecionado = fichaTreino.conversaotipoRefeicao(botaoSelecionado);
        int diaSemanaSelecionado = fichaTreino.conversaoDiaSemana(diaSelecionado);
        Treino treino = new Treino(descricaoField.getText(), valueDescription.getText());

        try{
            fichaTreino.cadastrarRefeicao(tipoSelecionado, diaSemanaSelecionado, treino);
        } catch (DuplicidadeException e) {
            throw new RuntimeException(e);
        }
    }

    public void Remover(){
        String botaoSelecionado = ((RadioButton)grupoBotoes.getSelectedToggle()).getText();
        String diaSelecionado = diaDaSemana.getSelectionModel().getSelectedItem();
        int tipoSelecionado = fichaTreino.conversaotipoRefeicao(botaoSelecionado);
        int diaSemanaSelecionado = fichaTreino.conversaoDiaSemana(diaSelecionado);
        fichaTreino.removerRefeicao(tipoSelecionado, diaSemanaSelecionado);
    }

    public void Consultar(){
        String botaoSelecionado = ((RadioButton)grupoBotoes.getSelectedToggle()).getText();
        String diaSelecionado = diaDaSemana.getSelectionModel().getSelectedItem();
        int tipoSelecionado = fichaTreino.conversaoDiaSemana(botaoSelecionado);
        int diaSemanaSelecionado = fichaTreino.conversaoDiaSemana(diaSelecionado);
        Treino treino = fichaTreino.consultarRefeicao(tipoSelecionado, diaSemanaSelecionado);

        descricaoField.setText(treino.getDescricao());
        valueDescription.setText(treino.getValorCalorico());
    }

    public void Alterar(){
        String botaoSelecionado = ((RadioButton)grupoBotoes.getSelectedToggle()).getText();
        String diaSelecionado = diaDaSemana.getSelectionModel().getSelectedItem();
        int tipoSelecionado = fichaTreino.conversaotipoRefeicao(botaoSelecionado);
        int diaSemanaSelecionado = fichaTreino.conversaoDiaSemana(diaSelecionado);

        Treino treino = new Treino(descricaoField.getText(), valueDescription.getText());

        fichaTreino.sobrescreverRefeicao(tipoSelecionado, diaSemanaSelecionado, treino);
    }

    public void Limpar(){
        descricaoField.setText("");
        valueDescription.setText("");
        cafeManha.setSelected(true);
    }
}
