package ru.kjd.jwis.jwisfx.gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import ru.kjd.jwis.core.xml.WisDiagnostic;
import ru.kjd.jwis.core.xml.WisItemElement;
import ru.kjd.jwis.core.xml.WisSubElement;
import ru.kjd.jwis.jwisfx.JWisFX;

import java.util.List;

public class WisTreeElement extends TreeItem {
    private WisItemElement itemElement;

    public WisTreeElement(final WisItemElement itemElement) {
        super(itemElement.getName());
        this.itemElement = itemElement;

        if ( "bltn-si".equals(itemElement.getType()) ){
            setGraphic(new ImageView("/bulletins/06.png"));
        } else if ( "bltn-mi".equals(itemElement.getType()) ){
            setGraphic(new ImageView("/bulletins/08.png"));
        } else if ( "bltn-tn".equals(itemElement.getType()) ){
            setGraphic(new ImageView("/bulletins/07.png"));
        } else {
            setGraphic(new ImageView("/bulletins/01.png"));
        }
        /*
        addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler() {
            @Override
            public void handle(Event event) {
                JWisFX.showItemElement(itemElement);
            }
        });
        */
        List<WisSubElement> subElements = itemElement.getSubElements();

        if (subElements != null && subElements.size() > 0)
            for (WisSubElement subElement : subElements) {
                getChildren().add(new WisTreeSubElement(subElement));
            }

        /*
        WisDiagnostic diagnostic = itemElement.getDiagnostic();
        if (diagnostic != null && diagnostic.getFcodes().size() > 0)
            for (String fcode : diagnostic.getFcodes()) {
                getChildren().add(new WisTreeFcode(fcode));
            }
            */
    }

    public WisItemElement getItemElement() {
        return itemElement;
    }
}
