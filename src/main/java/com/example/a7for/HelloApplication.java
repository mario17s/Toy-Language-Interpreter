package com.example.a7for;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load());
        MainController mainController = fxmlLoader1.getController();

        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("select_form.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load());
        SelectController selectController = fxmlLoader2.getController();

        mainController.setSelectController(selectController);

        Stage stage2 = new Stage();
        stage2.setTitle("Select Window");
        stage2.setScene(scene2);
        stage2.show();

        stage.setTitle("Program Window");
        stage.setScene(scene1);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}