package ru.kjd.jwis.jwisfx.gui;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;
import ru.kjd.jwis.core.xml.WisItem;
import ru.kjd.jwis.jwisfx.JWisController;

/**
 * Created by Николай on 26.10.2015.
 */
public class BulletinsTreeSelectionListener implements ChangeListener<TreeItem> {
    JWisController jWisController;

    public BulletinsTreeSelectionListener(JWisController jWisController) {
        this.jWisController = jWisController;
    }

    @Override
    public void changed(ObservableValue<? extends TreeItem> observable, TreeItem oldValue, TreeItem newValue) {
        if ( newValue instanceof WisTreeElement ){
            jWisController.onElementSelected((WisTreeElement)newValue);
        }
    }
}
