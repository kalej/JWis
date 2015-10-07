package ru.kjd.jwis.jwisfx.gui;

import javafx.scene.control.TreeItem;
import ru.kjd.jwis.core.xml.WisSubElement;

/**
 * Created by anonymous on 10/6/15.
 */
public class WisTreeSubElement extends TreeItem {
    WisSubElement subElement;

    public WisTreeSubElement(WisSubElement subElement) {
        super(subElement.getName());
        this.subElement = subElement;
    }

    public WisSubElement getSubElement() {
        return subElement;
    }
}
