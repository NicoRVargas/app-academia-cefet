package com.nico.cefet.academia;

import com.nico.cefet.academia.controller.HomeDocumentController;
import com.nico.cefet.academia.service.Login;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AcademiaApplication extends Application {

    private static Stage stage;

    private static Scene loginScene;
    private static Scene homeScene;
    private static Scene addExerciseScene;

    @Override
    public void start(Stage mainStage) throws IOException {
        mainStage.setTitle("Academia Dos Guri");
        stage = mainStage;

        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("loginDocument.fxml"));
        loginScene = new Scene(fxmlLogin, 600, 400);

        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("homeDocument.fxml"));
        Parent fxmlHome = homeLoader.load();
        homeScene = new Scene(fxmlHome, 940, 650);
        HomeDocumentController homeController = homeLoader.getController();
        homeController.setupSceneListener(mainStage);

        Parent fxmladdExcercise = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        addExerciseScene = new Scene(fxmladdExcercise, 600, 541);


        mainStage.setScene(loginScene);
        mainStage.show();
    }

    public static void changeScreen(String screen){
        switch (screen){
            case "login":
                stage.setScene(loginScene);
                break;
            case "home":
                stage.setScene(homeScene);
                break;
            case "addExercise":
                stage.setScene(addExerciseScene);
                break;
        }
    }

    public static Scene getHomeScene() {
        return homeScene;
    }

    public static void main(String[] args) {
        launch(args);
        Login.setUsuarioLogado(null);
    }
}