package ru.kjd.jwis.jwisfx.gui;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;
import ru.kjd.jwis.core.xml.WisItem;
import ru.kjd.jwis.jwisfx.JWisController;


public class WisTreeItemSelectionListener implements ChangeListener<TreeItem> {
    JWisController jWisController;

    public WisTreeItemSelectionListener(JWisController jWisController) {
        this.jWisController = jWisController;
    }

    @Override
    public void changed(ObservableValue<? extends TreeItem> observable, TreeItem oldValue, TreeItem newValue) {
        if ( newValue instanceof WisTreeElement ){
            jWisController.onElementSelected((WisTreeElement)newValue);
        } else if ( newValue instanceof WisTreeSubElement ) {
            jWisController.show((WisTreeSubElement)newValue);
        }
    }
}
