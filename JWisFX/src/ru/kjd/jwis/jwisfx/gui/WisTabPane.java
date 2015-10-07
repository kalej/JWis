package ru.kjd.jwis.jwisfx.gui;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import ru.kjd.jwis.core.ResourceManager;
import ru.kjd.jwis.core.enums.WisItemType;
import ru.kjd.jwis.core.xml.*;

import java.util.Arrays;
import java.util.Set;

public class WisTabPane extends TabPane {
    private WisHierarchy wisHierarchy;
    private ResourceManager resourceManager;

    public WisTabPane(WisHierarchy wisHierarchy, ResourceManager resourceManager) {
        this.wisHierarchy = wisHierarchy;
        this.resourceManager = resourceManager;
    }

    public void show(WisChapter chapter) {
        getTabs().clear();

        Set<WisItemType> typeSet = chapter.getTypes();
        WisItemType[] types = typeSet.toArray(new WisItemType[typeSet.size()]);
        Arrays.sort(types);
        for ( WisItemType type : types ){
            Tab tab = new Tab();
            tab.setText(type.name());

            tab.setContent(new WisTreeView(chapter.filter(type)));
            tab.setClosable(false);
            getTabs().add(tab);
        }
    }

    public void show(WisItemElement itemElement) {
        getTabs().clear();

        WisChapter chapter = itemElement.getParent().getParent();
        WisItemType selType = itemElement.getParent().getType();

        Set<WisItemType> typeSet = chapter.getTypes();
        WisItemType[] types = typeSet.toArray(new WisItemType[typeSet.size()]);
        Arrays.sort(types);
        for ( WisItemType type : types ){
            Tab tab = new Tab();
            tab.setText(type.name());
            tab.setClosable(false);

            if ( selType == type ) {
                getSelectionModel().select(tab);
                tab.setContent(new WisDocPane(wisHierarchy, itemElement, resourceManager));
            }
            else
                tab.setContent(new WisTreeView(chapter.filter(type)));

            getTabs().add(tab);
        }
    }

    public void show(WisItem item) {
        show(item.getParent(), item.getType());
    }

    private void show(WisChapter chapter, WisItemType selType) {
        getTabs().clear();

        Set<WisItemType> typeSet = chapter.getTypes();
        WisItemType[] types = typeSet.toArray(new WisItemType[typeSet.size()]);
        Arrays.sort(types);
        for ( WisItemType type : types ){
            Tab tab = new Tab();
            if ( selType == type )
                getSelectionModel().select(tab);

            tab.setText(type.name());

            tab.setContent(new WisTreeView(chapter.filter(type)));
            tab.setClosable(false);
            getTabs().add(tab);
        }
    }

    public void show(WisSection section) {
        getTabs().clear();

        Set<WisItemType> typeSet = section.getTypes();
        WisItemType[] types = typeSet.toArray(new WisItemType[typeSet.size()]);
        Arrays.sort(types);
        for ( WisItemType type : types ){
            Tab tab = new Tab();
            tab.setText(type.name());

            tab.setContent(new WisTreeView(section.filter(type)));
            tab.setClosable(false);
            getTabs().add(tab);
        }
    }

    public void show(WisSubElement subElement) {

    }
}
