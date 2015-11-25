package ru.kjd.jwis.jwisfx.gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import ru.kjd.jwis.core.xml.WisChapter;
//import ru.kjd.jwis.jwisfx.JWisFX;

public class WisTreeChapterItem extends TreeItem {
    WisChapter chapter;

    public WisTreeChapterItem(final WisChapter chapter) {
        super(chapter.getName());
        this.chapter = chapter;
    }

    public WisChapter getChapter() {
        return chapter;
    }
}
