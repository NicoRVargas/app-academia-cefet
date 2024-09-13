package com.nico.cefet.academia.controller;

import com.nico.cefet.academia.AcademiaApplication;
import com.nico.cefet.academia.entity.Treino;
import com.nico.cefet.academia.service.FichaTreino;
import com.nico.cefet.academia.service.Login;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class HomeDocumentController implements Initializable {
    FichaTreino fichaTreino = FichaTreino.getInstance();

    @FXML
    private GridPane treinoGridPane;

    @FXML
    private TextArea userName;

    public void GoToAcademia(){
        AcademiaApplication.changeScreen("addExercise");
    }

    public void ReadData(){
        fichaTreino.readLines(Login.getUsuarioLogado());
    }

    public void UpdateGridPane() {
        Treino[][] ficha = fichaTreino.getFicha();

        //treinoGridPane.getChildren().clear(); // Clear existing children

        for (int dia = 0; dia < ficha.length; dia++) {
            for (int i = 0; i < ficha[dia].length; i++) {
                Treino treino = ficha[dia][i];
                if (treino != null) {
                    TextArea child = (TextArea) treinoGridPane.getChildren().get(dia * treinoGridPane.getColumnCount() + i);;

                    child.setText(treino.getDescricao());
                    System.out.println(treino.getDescricao() + " " + dia + " " + i);
                } else {
                    TextArea child = (TextArea) treinoGridPane.getChildren().get(dia * treinoGridPane.getColumnCount() + i);
                    child.setText("");
                }
            }
        }
    }

//    public void UpdateGridPane(){
//        Treino[][] ficha = fichaTreino.getFicha();
//// ... add children to the gridPane ...
//
//// Create a 2D array to store child nodes
//        Node[][] childNodes = new Node[treinoGridPane.getRowCount()][treinoGridPane.getColumnCount()];
//
//// Populate the 2D array
//        int i = 1;
//        for (Node node : treinoGridPane.getChildren()) {
//
////            Integer row = GridPane.getRowIndex(node);
////            Integer column = GridPane.getColumnIndex(node);
////            if (row != null && column != null) {
////                childNodes[row][column] = node;
////                if (node instanceof TextArea textArea) {
////                    textArea.setText(ficha[column][row].getDescricao());
////                }
////            }
//            System.out.println(node);
//            System.out.println(i);
//            i++;
//        }
//    }

    public void setupSceneListener(Stage stage) {
        stage.sceneProperty().addListener((observable, oldScene, newScene) -> {
                    if (newScene == AcademiaApplication.getHomeScene()) {
                        ReadData();
                        UpdateGridPane();
                        userName.setText(Login.getUsuarioLogado());
                    }
                }
        );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ReadData();
        UpdateGridPane();
        System.out.println("receba");
    }
}
