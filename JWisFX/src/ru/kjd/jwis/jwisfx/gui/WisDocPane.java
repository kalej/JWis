package ru.kjd.jwis.jwisfx.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import ru.kjd.jwis.core.ResourceManager;
import ru.kjd.jwis.core.xml.WisHierarchy;
import ru.kjd.jwis.core.xml.WisItemElement;
import ru.kjd.jwis.core.xml.WisSubElement;

import java.util.logging.Logger;

public class WisDocPane extends StackPane {
    private final static String WIS_IMG_PREFIX = "wisimg://i";
    private final static String WIS_LINK_PREFIX = "wisref://";
    private static Logger log = Logger.getLogger(WisDocPane.class.getName());
    WebView webView;
    WisItemElement itemElement;
    ResourceManager resourceManager;
    WisHierarchy hierarchy;

    public WisDocPane(final WisHierarchy hierarchy, final ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
        this.hierarchy = hierarchy;

        webView = new WebView();
        webView.getEngine().setJavaScriptEnabled(true);

        getChildren().add(webView);
        setAlignment(webView, Pos.TOP_LEFT);

        webView.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State state, Worker.State t1) {
                if (t1 == Worker.State.SUCCEEDED) {

                }
            }
        });

        webView.getEngine().locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, final String s, final String t1) {
                log.info("Old url: " + s);
                log.info("New url: " + t1);
            }
        });
    }

    public WisDocPane(final WisHierarchy hierarchy, WisItemElement itemElement, final ResourceManager resourceManager) {
        this(hierarchy, resourceManager);
        this.itemElement = itemElement;
        webView.getEngine().load("wisdoc://" + itemElement.getDocId());
    }

    public WisDocPane(WisHierarchy hierarchy, WisSubElement subElement, ResourceManager resourceManager) {
        this(hierarchy, resourceManager);
        this.itemElement = subElement.getParent();
        webView.getEngine().load("wisdoc://" + itemElement.getDocId() + "#" + subElement.getSiSubId());
    }
}
