package ru.kjd.jwis.jwisfx.gui;

import javafx.scene.control.TreeItem;
import ru.kjd.jwis.core.xml.WisChapter;
import ru.kjd.jwis.core.xml.WisItem;

public class WisTreeChapterItem extends TreeItem {
    WisChapter chapter;
    boolean completeExpand;

    public WisTreeChapterItem(WisChapter chapter, boolean completeExpand) {
        super(chapter.getName());
        this.chapter = chapter;
        this.completeExpand = completeExpand;
        updateNodes(completeExpand);
    }

    private void updateNodes(boolean completeExpand) {
        for ( WisItem item : chapter.getItems()){
            getChildren().add(new WisTreeItem(item, completeExpand));
        }
    }

    public WisChapter getChapter() {
        return chapter;
    }
}
