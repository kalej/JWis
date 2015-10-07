package ru.kjd.jwis.jwisfx.gui;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import ru.kjd.jwis.core.ResourceManager;
import ru.kjd.jwis.core.xml.WisChapter;
import ru.kjd.jwis.core.xml.WisHierarchy;
import ru.kjd.jwis.core.xml.WisSection;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created by anonymous on 10/2/15.
 */
public class WisTreeView extends TreeView {
    TreeItem root;

    public WisTreeView(WisHierarchy hierarchy) {
        root = new TreeItem("hide me sucker");
        doRoot();

        for(WisSection section : hierarchy.getSections()){
            root.getChildren().add(new WisTreeSectionItem(section, false));
        }
    }

    public WisTreeView(WisSection section) {
        root = new WisTreeSectionItem(section, true);
        doRoot();
    }

    public WisTreeView(WisChapter chapter) {
        root = new WisTreeChapterItem(chapter, true);
        doRoot();
    }

    private void doRoot(){
        setRoot(root);
        setShowRoot(false);
        root.setExpanded(true);
    }
}
