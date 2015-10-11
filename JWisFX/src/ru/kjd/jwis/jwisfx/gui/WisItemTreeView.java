package ru.kjd.jwis.jwisfx.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import ru.kjd.jwis.core.xml.WisItem;
import ru.kjd.jwis.core.xml.WisItemElement;
import ru.kjd.jwis.jwisfx.JWisFX;

public class WisItemTreeView extends TreeView {
    TreeItem root;
    WisItem item;

    public WisItemTreeView(WisItem wisItem) {
        root = new TreeItem("hide me sucker");
        this.item = wisItem;
        doRoot();

        for(final WisItemElement itemElement : item.getElements()){
            root.getChildren().add(new WisTreeElement(itemElement));
        }

        getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                if ( t1 instanceof WisTreeElement ){
                    JWisFX.showItemElement(((WisTreeElement)t1).getItemElement());
                }else if ( t1 instanceof WisTreeSubElement ) {
                    JWisFX.showSubElement(((WisTreeSubElement)t1).getSubElement());
                }
            }
        });
    }

    public WisItem getItem() {
        return item;
    }

    private void doRoot(){
        setRoot(root);
        setShowRoot(false);
        root.setExpanded(true);
    }
}


