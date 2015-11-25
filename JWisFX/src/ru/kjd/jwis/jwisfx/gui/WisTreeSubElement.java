package ru.kjd.jwis.jwisfx.gui;

import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import ru.kjd.jwis.core.xml.WisSubElement;

public class WisTreeSubElement extends TreeItem {
    WisSubElement subElement;

    public WisTreeSubElement(final WisSubElement subElement) {
        super(subElement.getName());
        this.subElement = subElement;

        setGraphic(new ImageView("/bulletins/00.png"));
    }

    public WisSubElement getSubElement() {
        return subElement;
    }
}
