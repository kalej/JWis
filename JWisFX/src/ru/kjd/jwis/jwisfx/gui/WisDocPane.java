package ru.kjd.jwis.jwisfx.gui;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.html.HTMLAnchorElement;
import ru.kjd.jwis.core.ResourceManager;
import ru.kjd.jwis.core.xml.WisHierarchy;
import ru.kjd.jwis.core.xml.WisItemElement;
import ru.kjd.jwis.core.xml.WisLink;
import ru.kjd.jwis.core.xml.WisSubElement;
import ru.kjd.jwis.jwisfx.JWisFX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WisDocPane extends StackPane {
    WebView webView;
    //WisImgView imageView;
    WisItemElement itemElement;
    ResourceManager resourceManager;
    String imgName;
    WisHierarchy hierarchy;
    Button imageButton;

    private final static String WIS_IMG_PREFIX = "wisimg://i";
    private final static String WIS_LINK_PREFIX = "wisimg://l";

    public WisDocPane(final WisHierarchy hierarchy, final ResourceManager resourceManager){
        this.resourceManager = resourceManager;
        this.hierarchy = hierarchy;

        webView = new WebView();

        getChildren().add(webView);
        setAlignment(webView, Pos.TOP_LEFT);

        webView.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State state, Worker.State t1) {
                if (t1 == Worker.State.SUCCEEDED){
                    addHyperlinkListeners();
                }
            }
        });

        webView.getEngine().locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, final String s, final String t1) {
                if ( t1.startsWith(WIS_IMG_PREFIX) ){
                    webView.getEngine().getLoadWorker().cancel();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            String content = "<html><body><img src=\"" + t1 + "\"/></body></html>";
                            webView.getEngine().loadContent(content);
                        }
                    });
                }
            }
        });
    }

    private void addHyperlinkListeners() {
        Document doc = webView.getEngine().getDocument();
        NodeList links = doc.getElementsByTagName("a");
        for ( int i = 0; i < links.getLength(); i++ ){
            final Node node = links.item(i);

            if ( node instanceof HTMLAnchorElement){
                ((EventTarget)node).addEventListener("click", new EventListener() {
                    @Override
                    public void handleEvent(Event evt) {
                        HTMLAnchorElement link = (HTMLAnchorElement)node;
                        if ( link.getHref().startsWith(WIS_LINK_PREFIX)){
                            for( WisLink wisLink : itemElement.getLinks() ){
                                if (wisLink.getLinkId() == Integer.parseInt(link.getHref().substring(WIS_IMG_PREFIX.length()))){
                                    webView.getEngine().load("wisref://" + wisLink.getDest());
                                }
                            }
                        }
                    }
                }, false);
            }
        }
    }

    public WisDocPane(final WisHierarchy hierarchy, WisItemElement itemElement, final ResourceManager resourceManager){
        this(hierarchy, resourceManager);
        webView.getEngine().load("wisref://" + itemElement.getDocId());
    }

    public WisDocPane(WisHierarchy hierarchy, WisSubElement subElement, ResourceManager resourceManager) {
        this(hierarchy, resourceManager);
        webView.getEngine().load("wisref://" + subElement.getParent().getDocId() + "#" + subElement.getSiSubId());
    }
}
