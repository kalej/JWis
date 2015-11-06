package ru.kjd.jwis.jwisfx;/**
 * Created by anonymous on 10/16/15.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.kjd.jwis.core.WisProperties;
import ru.kjd.jwis.jwisfx.urlhandlers.WisDocStreamHandler;
import ru.kjd.jwis.jwisfx.urlhandlers.WisImgStreamHandler;
import ru.kjd.jwis.jwisfx.urlhandlers.WisRefStreamHandler;

import java.io.IOException;
import java.net.URL;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public class JWis extends Application {

    public static void main(String[] args) {
        URL.setURLStreamHandlerFactory(new URLStreamHandlerFactory() {
            @Override
            public URLStreamHandler createURLStreamHandler(String protocol) {
                switch (protocol) {
                    case "wisimg":
                        return new WisImgStreamHandler();
                    case "wisref":
                        return new WisRefStreamHandler();
                    case "wisdoc":
                        return new WisDocStreamHandler();
                    default:
                        return null;
                }
            }
        });

        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("jwis.fxml"));

        stage.setTitle(WisProperties.PROG_NAME);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
