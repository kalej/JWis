package ru.kjd.jwis.jwisfx.gui;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import ru.kjd.jwis.core.ResourceManager;
import ru.kjd.jwis.core.xml.WisChapter;
import ru.kjd.jwis.core.xml.WisHierarchy;
import ru.kjd.jwis.core.xml.WisSection;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class WisHierarchyTreeView extends TreeView {
    TreeItem root;

    public WisHierarchyTreeView(WisHierarchy hierarchy) {
        root = new TreeItem("hide me sucker");
        doRoot();

        for(WisSection section : hierarchy.getSections()){
            root.getChildren().add(new WisTreeSectionItem(section));
        }
    }

    public WisHierarchyTreeView(WisSection section) {
        root = new WisTreeSectionItem(section);
        doRoot();
    }

    public WisHierarchyTreeView(WisChapter chapter) {
        root = new WisTreeChapterItem(chapter);
        doRoot();
    }

    private void doRoot(){
        setRoot(root);
        setShowRoot(false);
        root.setExpanded(true);
    }
}
