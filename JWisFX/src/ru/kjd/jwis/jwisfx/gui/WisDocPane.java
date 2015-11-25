package ru.kjd.jwis.jwisfx.gui;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import ru.kjd.jwis.core.ResourceManager;
import ru.kjd.jwis.core.xml.WisHierarchy;
import ru.kjd.jwis.core.xml.WisItemElement;
import ru.kjd.jwis.core.xml.WisSubElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class WisDocPane extends StackPane {
    private static Logger log = Logger.getLogger(WisDocPane.class.getName());
    WebView webView;

    public WisDocPane() {
        webView = new WebView();
        webView.getEngine().setJavaScriptEnabled(true);

        getChildren().add(webView);
        setAlignment(webView, Pos.TOP_LEFT);

        webView.getEngine().locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, final String s, final String t1) {
                log.info("Old url: " + s);
                log.info("New url: " + t1);
            }
        });
    }

    public void load(WisItemElement itemElement) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                webView.getEngine().load("wisdoc://" + itemElement.getDocId());
            }
        });
    }

    public void load(WisSubElement subElement) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                WisItemElement itemElement = subElement.getParent();
                webView.getEngine().load("wisdoc://" + itemElement.getDocId() + "#" + subElement.getSiSubId());
            }
        });
    }
}
