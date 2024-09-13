package com.nico.cefet.academia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AcademiaApplication extends Application {

    private static Stage stage;

    private static Scene loginScene;
    private static Scene registerScene;
    private static Scene homeScene;
    private static Scene addExerciseScene;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Academia Dos Guri");

        //FXMLLoader fxmlLoader = new FXMLLoader(AcademiaApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 600, 430);
        //scene.getStylesheets().add(AcademiaApplication.class.getResource("styles.css").toExternalForm());

        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("loginDocument.fxml"));
        loginScene = new Scene(fxmlLogin, 600, 400);

        //Parent fxmlRegister = FXMLLoader.load(getClass().getResource("loginDocument.fxml"));
        //registerScene = new Scene(fxmlRegister, 600, 400);

        //Parent fxmlHome = FXMLLoader.load(getClass().getResource("loginDocument.fxml"));
        //homeScene = new Scene(fxmlHome, 600, 400);

        Parent fxmladdExcercise = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        addExerciseScene = new Scene(fxmladdExcercise, 600, 430);


        stage.setScene(addExerciseScene);
        stage.show();
    }

    public static void changeScreen(String screen){
        switch (screen){
            case "login":
                stage.setScene(loginScene);
                break;
            case "register":
                stage.setScene(registerScene);
                break;
            case "home":
                stage.setScene(homeScene);
                break;
            case "addExercise":
                stage.setScene(addExerciseScene);
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}