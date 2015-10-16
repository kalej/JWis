package ru.kjd.jwis.jwisfx;/**
 * Created by anonymous on 10/16/15.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class JWis extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("jwisfx.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        showWindow();
    }

    public void showWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("jwisfxdialog.fxml"));
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
