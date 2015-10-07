package ru.kjd.jwis.jwisfx.gui;

import javafx.scene.control.TreeItem;
import ru.kjd.jwis.core.xml.WisItemElement;
import ru.kjd.jwis.core.xml.WisSubElement;

/**
 * Created by anonymous on 10/2/15.
 */
public class WisTreeElement extends TreeItem {
    WisItemElement itemElement;

    public WisTreeElement(WisItemElement itemElement, boolean completeExpand) {
        super(itemElement.getName());
        this.itemElement = itemElement;

        if (completeExpand && (itemElement.getSubElements() != null))
            updateNodes();
    }

    private void updateNodes() {
        for( WisSubElement subElement : itemElement.getSubElements()){
            getChildren().add(new WisTreeSubElement(subElement));
        }
    }

    public WisItemElement getItemElement() {
        return itemElement;
    }
}
