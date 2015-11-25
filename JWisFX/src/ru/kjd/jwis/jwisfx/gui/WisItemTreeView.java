package ru.kjd.jwis.jwisfx.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import ru.kjd.jwis.core.xml.WisItem;
import ru.kjd.jwis.core.xml.WisItemElement;

public class WisItemTreeView extends TreeView {
    TreeItem root;
    WisItem item;

    public WisItemTreeView(WisItem wisItem) {
        root = new TreeItem("hide me sucker");
        this.item = wisItem;
        doRoot();

        for (final WisItemElement itemElement : item.getElements()) {
            root.getChildren().add(new WisTreeElement(itemElement));
        }
    }

    public WisItem getItem() {
        return item;
    }

    private void doRoot() {
        setRoot(root);
        setShowRoot(false);
        root.setExpanded(true);
    }
}


