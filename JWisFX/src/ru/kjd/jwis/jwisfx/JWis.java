package ru.kjd.jwis.jwisfx;/**
 * Created by anonymous on 10/16/15.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.control.Dialog;
import ru.kjd.jwis.core.WisStrings;
import ru.kjd.jwis.core.utils.StringExtractor;
import ru.kjd.jwis.jwisfx.gui.WisDialog;

import java.io.IOException;
import java.util.Optional;

public class JWis extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("jwis.fxml"));

        stage.setTitle(WisStrings.get(WisStrings.STRING_WIS)+" JWis by Kalej");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
