package ru.kjd.jwis.jwisfx.gui;

import javafx.scene.control.TreeItem;
import ru.kjd.jwis.core.xml.WisItem;
import ru.kjd.jwis.core.xml.WisItemElement;

/**
 * Created by anonymous on 10/2/15.
 */
public class WisTreeItem extends TreeItem {
    WisItem item;
    boolean completeExpand;

    public WisTreeItem(WisItem wisItem, boolean completeExpand) {
        super(wisItem.getName());
        this.item = wisItem;
        this.completeExpand = completeExpand;
        updateNodes(completeExpand);
    }

    private void updateNodes(boolean completeExpand) {
        for ( WisItemElement itemElement : item.getElements() ){
            getChildren().add(new WisTreeElement(itemElement, completeExpand));
        }
    }

    public WisItem getItem() {
        return item;
    }
}


