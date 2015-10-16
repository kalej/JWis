package ru.kjd.jwis.jwisfx.gui;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import ru.kjd.jwis.core.xml.WisChapter;
import ru.kjd.jwis.core.xml.WisHierarchy;
import ru.kjd.jwis.core.xml.WisSection;

public class WisHierarchyTreeView extends TreeView {
    TreeItem root;
    WisHierarchy hierarchy;

    public WisHierarchyTreeView(){
        root = new TreeItem("hide me sucker");
        doRoot();

    }

    public WisHierarchyTreeView(WisHierarchy hierarchy) {
        this();
        setHierarchy(hierarchy);
    }

    public WisHierarchyTreeView(WisSection section) {
        root = new WisTreeSectionItem(section);
        doRoot();
    }

    public WisHierarchyTreeView(WisChapter chapter) {
        root = new WisTreeChapterItem(chapter);
        doRoot();
    }

    private void doRoot() {
        setRoot(root);
        setShowRoot(false);
        root.setExpanded(true);
    }

    public void setHierarchy(WisHierarchy hierarchy) {
        this.hierarchy = hierarchy;

        root.getChildren().clear();
        for (WisSection section : hierarchy.getSections()) {
            root.getChildren().add(new WisTreeSectionItem(section));
        }
    }
}
