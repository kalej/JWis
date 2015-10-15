package ru.kjd.jwis.jwisfx.gui;

import javafx.scene.control.TreeItem;
import ru.kjd.jwis.core.xml.WisChapter;
import ru.kjd.jwis.core.xml.WisSection;

public class WisTreeSectionItem extends TreeItem {
    WisSection section;

    public WisTreeSectionItem(WisSection section) {
        super(section.getName());
        this.section = section;

        for (WisChapter chapter : section.getChapters()) {
            getChildren().add(new WisTreeChapterItem(chapter));
        }
    }

    public WisSection getSection() {
        return section;
    }
}
