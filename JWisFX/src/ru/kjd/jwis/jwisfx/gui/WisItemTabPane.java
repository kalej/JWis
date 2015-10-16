package ru.kjd.jwis.jwisfx.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import ru.kjd.jwis.core.ResourceManager;
import ru.kjd.jwis.core.enums.WisItemType;
import ru.kjd.jwis.core.xml.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class WisItemTabPane extends TabPane {
    private WisHierarchy wisHierarchy;
    private ResourceManager resourceManager;
    private WisChapter currentChapter;
    private WisItemElement currentElement;

    private Map<WisItemType, Tab> tabs = new HashMap<>();

    static Logger log = Logger.getLogger(WisItemTabPane.class.getName());

    public WisItemTabPane(){
        for (WisItemType itemType : WisItemType.values()) {
            Tab tab = new Tab();
            tab.setClosable(false);
            tab.setDisable(true);
            //tab.setText(itemType.name());
            tab.setGraphic(new ImageView(itemType.getPicture()));
            tabs.put(itemType, tab);
            getTabs().add(tab);
        }
    }

    public WisItemTabPane(WisHierarchy wisHierarchy, ResourceManager resourceManager) {
        this.wisHierarchy = wisHierarchy;
        this.resourceManager = resourceManager;

        for (WisItemType itemType : WisItemType.values()) {
            Tab tab = new Tab();
            tab.setClosable(false);
            tab.setDisable(true);
            //tab.setText(itemType.name());
            tab.setGraphic(new ImageView(itemType.getPicture()));
            tabs.put(itemType, tab);
            getTabs().add(tab);
        }

        getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab t1) {
                WisItemType type = null;
                for (WisItemType itemType : tabs.keySet()) {
                    if (tabs.get(itemType) == tab) {
                        type = itemType;
                        break;
                    }
                }

                for (WisItem item : currentChapter.getItems()) {
                    if (item.getType() == type) {
                        tab.setContent(new WisItemTreeView(item));
                        break;
                    }
                }
            }
        });
    }

    public void show(WisChapter chapter) {
        boolean hasActive = false;
        this.currentChapter = chapter;
        clearTabs();

        for (WisItem item : chapter.getItems()) {
            log.info("Item: " + item.getNum() + " -> " + item.getType() + " -> " + item.getType().getPicture());
            Tab tab = tabs.get(item.getType());
            tab.setDisable(false);
            tab.setContent(new WisItemTreeView(item));

            if (!hasActive) {
                getSelectionModel().select(tab);
                hasActive = true;
            }
        }
    }

    public void clearTabs() {
        for (Tab tab : tabs.values()) {
            tab.setDisable(true);
            tab.setContent(null);
        }
    }

    public int getDestId(int linkId) {
        if (currentElement != null) {
            for (WisLink link : currentElement.getLinks()) {
                if (link.getLinkId() == linkId)
                    return Integer.parseInt(link.getDest());
            }
        }

        return -1;
    }

    public void show(WisItemElement itemElement) {
        currentElement = itemElement;
        Tab tab = getSelectionModel().getSelectedItem();
        tab.setContent(null);
        tab.setContent(new WisDocPane(wisHierarchy, itemElement, resourceManager));
    }

    public void show(WisItem item) {
        show(item.getParent(), item.getType());
    }

    private void show(WisChapter chapter, WisItemType selType) {
        getTabs().clear();

        Set<WisItemType> typeSet = chapter.getTypes();
        WisItemType[] types = typeSet.toArray(new WisItemType[typeSet.size()]);
        Arrays.sort(types);
        for (WisItemType type : types) {
            Tab tab = new Tab();
            if (selType == type)
                getSelectionModel().select(tab);

            tab.setText(type.name());

            tab.setContent(new WisHierarchyTreeView(chapter.filter(type)));
            tab.setClosable(false);
            getTabs().add(tab);
        }
    }

    public void show(WisSection section) {
        getTabs().clear();

        Set<WisItemType> typeSet = section.getTypes();
        WisItemType[] types = typeSet.toArray(new WisItemType[typeSet.size()]);
        Arrays.sort(types);
        for (WisItemType type : types) {
            Tab tab = new Tab();
            tab.setText(type.name());

            tab.setContent(new WisHierarchyTreeView(section.filter(type)));
            tab.setClosable(false);
            getTabs().add(tab);
        }
    }

    public void show(WisSubElement subElement) {
        Tab tab = getSelectionModel().getSelectedItem();
        tab.setContent(null);
        tab.setContent(new WisDocPane(wisHierarchy, subElement, resourceManager));
    }

    public int getDocId(int dest) {
        for (WisItemElement itemElement : currentElement.getParent().getElements()) {
            if (itemElement.getId() == dest)
                return itemElement.getDocId();
        }

        return -1;
    }

    public void forward() {

    }

    public void backward() {

    }
}
