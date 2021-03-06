package ru.kjd.jwis.jwisfx.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;
import ru.kjd.jwis.jwisfx.JWisController;

import java.util.logging.Logger;

/**
 * Created by ������� on 25.10.2015.
 */
public class WisHierarchyTreeSelectionListener implements ChangeListener<TreeItem>{
    private static Logger log = Logger.getLogger(WisHierarchyTreeSelectionListener.class.getName());
    JWisController jWisController;

    public WisHierarchyTreeSelectionListener(JWisController jWisController) {
        this.jWisController = jWisController;
    }

    @Override
    public void changed(ObservableValue<? extends TreeItem> observable, TreeItem oldValue, TreeItem newValue) {
        if ( newValue instanceof WisTreeSectionItem ){
            jWisController.show((WisTreeSectionItem) newValue);
        } else if ( newValue instanceof WisTreeChapterItem ) {
            jWisController.show((WisTreeChapterItem) newValue);
        }
    }
}
