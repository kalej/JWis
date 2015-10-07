package ru.kjd.jwis.jwisfx.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLAnchorElement;
import ru.kjd.jwis.core.ResourceManager;
import ru.kjd.jwis.core.xml.WisHierarchy;
import ru.kjd.jwis.core.xml.WisItemElement;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class WisDocPane extends StackPane {
    WebView webView;
    WisImgView imageView;
    WisItemElement itemElement;
    ResourceManager resourceManager;
    String imgName;
    WisHierarchy hierarchy;

    private final static String WIS_IMG_PREFIX = "wisimg://i";

    public WisDocPane(final WisHierarchy hierarchy, WisItemElement itemElement, final ResourceManager resourceManager){
        this.itemElement = itemElement;
        this.resourceManager = resourceManager;
        this.hierarchy = hierarchy;

        webView = new WebView();

        getChildren().add(webView);
        setAlignment(webView, Pos.TOP_LEFT);

        webView.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State state, Worker.State t1) {
                if (t1 == Worker.State.SUCCEEDED){
                    loadElementImage();
                }
            }
        });

        webView.getEngine().load("wisref://" + itemElement.getDocId());
    }

    private void loadElementImage(){
        List<HTMLAnchorElement> anchors = anchors();
        if ( anchors.size() == 1 ){
            imgName = anchors.get(0).getHref().substring(WIS_IMG_PREFIX.length()-1);
            try {
                imageView = new WisImgView(getHeight(), getWidth(), hierarchy, resourceManager, imgName);
                getChildren().add(imageView);
                setAlignment(imageView, Pos.BOTTOM_RIGHT);
            } catch (FileNotFoundException e) {

            }
        }
    }

    private List<HTMLAnchorElement> anchors(){
        List<HTMLAnchorElement> result = new ArrayList<>();
        Document doc = webView.getEngine().getDocument();
        NodeList links = doc.getElementsByTagName("a");
        for ( int i = 0; i < links.getLength(); i++ ){
            Node node = links.item(i);

            if ( node instanceof HTMLAnchorElement){
                HTMLAnchorElement link = (HTMLAnchorElement)node;
                String src = link.getHref();
                if ( src != null && src.toLowerCase().startsWith(WIS_IMG_PREFIX)){
                        result.add(link);
                }
            }
        }
        return result;
    }
}
