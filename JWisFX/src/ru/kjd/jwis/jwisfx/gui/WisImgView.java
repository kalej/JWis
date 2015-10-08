package ru.kjd.jwis.jwisfx.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import ru.kjd.jwis.core.ResourceManager;
import ru.kjd.jwis.core.xml.WisHierarchy;

import java.io.FileNotFoundException;
import java.io.IOException;

public class WisImgView extends ImageView {
    Double maxHeight;
    Double maxWidth;
    Double previewRatio = 0.3;
    WisHierarchy hierarchy;
    ResourceManager resourceManager;
    String imgName;

    EventHandler<MouseEvent> previewImageListener = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent){
            removeEventHandler(MouseEvent.MOUSE_CLICKED, previewImageListener);

            try {
                setImage(resourceManager.getImage(hierarchy, imgName));
                addEventHandler(MouseEvent.MOUSE_CLICKED, expandedImageListener);
            } catch (IOException e) {
                setVisible(false);
            }
        }
    };

    EventHandler<MouseEvent> expandedImageListener = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            removeEventHandler(MouseEvent.MOUSE_CLICKED, expandedImageListener);

            try {
                setImage(resourceManager.getImagePreview(hierarchy, imgName, maxWidth * previewRatio, maxHeight * previewRatio));
                addEventHandler(MouseEvent.MOUSE_CLICKED, previewImageListener);
            } catch (IOException e) {
                setVisible(false);
            }
        }
    };

    public WisImgView(Double maxHeight, Double maxWidth, WisHierarchy hierarchy, ResourceManager resourceManager, String imgName) throws IOException {
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
        this.hierarchy = hierarchy;
        this.resourceManager = resourceManager;
        this.imgName = imgName;

        setPreserveRatio(true);
        Image image = resourceManager.getImagePreview(hierarchy, imgName, maxWidth * previewRatio, maxHeight * previewRatio);
        setImage(image);
        addEventHandler(MouseEvent.MOUSE_CLICKED, previewImageListener);
    }
}
