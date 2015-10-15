package ru.kjd.jwis.jwisfx.gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
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

        addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler() {
            @Override
            public void handle(Event event) {
                JWisFX.showItemElement(itemElement);
            }
        });

        List<WisSubElement> subElements = itemElement.getSubElements();

        if (subElements != null && subElements.size() > 0)
            for (WisSubElement subElement : subElements) {
                getChildren().add(new WisTreeSubElement(subElement));
            }

        WisDiagnostic diagnostic = itemElement.getDiagnostic();
        if (diagnostic != null && diagnostic.getFcodes().size() > 0)
            for (String fcode : diagnostic.getFcodes()) {
                getChildren().add(new WisTreeFcode(fcode));
            }
    }

    public WisItemElement getItemElement() {
        return itemElement;
    }
}
