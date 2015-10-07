package ru.kjd.jwis.jwisfx.gui;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import ru.kjd.jwis.core.xml.WisChapter;
import ru.kjd.jwis.core.xml.WisHierarchy;
import ru.kjd.jwis.core.xml.WisSection;

public class WisTreeSectionItem extends TreeItem {
    WisSection section;
    boolean completeExpand;
    public WisTreeSectionItem(WisSection section, boolean completeExpand) {
        super(section.getName());
        this.section = section;
        this.completeExpand = completeExpand;
        updateNodes(completeExpand);
    }

    private void updateNodes(boolean completeExpand) {
        for( WisChapter chapter : section.getChapters()){
            getChildren().add(new WisTreeChapterItem(chapter, completeExpand));
        }
    }

    public WisSection getSection() {
        return section;
    }
}
