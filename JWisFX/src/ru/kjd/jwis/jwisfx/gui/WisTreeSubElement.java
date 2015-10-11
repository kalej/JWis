package ru.kjd.jwis.jwisfx.gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import ru.kjd.jwis.core.xml.WisSubElement;
import ru.kjd.jwis.jwisfx.JWisFX;

public class WisTreeSubElement extends TreeItem {
    WisSubElement subElement;

    public WisTreeSubElement(final WisSubElement subElement) {
        super(subElement.getName());
        this.subElement = subElement;

        addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler() {
            @Override
            public void handle(Event event) {
                JWisFX.showSubElement(subElement);
            }
        });
    }

    public WisSubElement getSubElement() {
        return subElement;
    }
}
