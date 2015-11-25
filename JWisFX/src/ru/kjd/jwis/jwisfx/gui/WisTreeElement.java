package ru.kjd.jwis.jwisfx.gui;

import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import ru.kjd.jwis.core.xml.WisItemElement;
import ru.kjd.jwis.core.xml.WisSubElement;

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

        List<WisSubElement> subElements = itemElement.getSubElements();

        if (subElements != null && subElements.size() > 0)
            for (WisSubElement subElement : subElements) {
                getChildren().add(new WisTreeSubElement(subElement));
            }
    }

    public WisItemElement getItemElement() {
        return itemElement;
    }
}
