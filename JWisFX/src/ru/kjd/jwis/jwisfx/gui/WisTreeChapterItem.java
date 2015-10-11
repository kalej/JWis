package ru.kjd.jwis.jwisfx.gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import ru.kjd.jwis.core.xml.WisChapter;
import ru.kjd.jwis.jwisfx.JWisFX;

public class WisTreeChapterItem extends TreeItem {
    WisChapter chapter;

    public WisTreeChapterItem(final WisChapter chapter) {
        super(chapter.getName());
        this.chapter = chapter;

        addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler() {
            @Override
            public void handle(Event event) {
                JWisFX.showChapter(chapter);
            }
        });
    }

/*    private void updateNodes(boolean completeExpand) {
        for ( WisItem item : chapter.getItems()){
            getChildren().add(new WisItemTreeView(item, completeExpand));
        }
    }*/

    public WisChapter getChapter() {
        return chapter;
    }
}
