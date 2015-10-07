package ru.kjd.jwis.jwisfx.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import ru.kjd.jwis.core.ResourceManager;
import ru.kjd.jwis.core.xml.WisHierarchy;

import java.io.FileNotFoundException;

/**
 * Created by anonymous on 10/7/15.
 */
public class WisImgView extends ScrollPane {
    Double maxHeight;
    Double maxWidth;
    Double previewRatio = 0.3;
    WisHierarchy hierarchy;
    ResourceManager resourceManager;
    String imgName;
    ImageView imageView;

    EventHandler<MouseEvent> previewImageListener = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent){
            imageView.removeEventHandler(MouseEvent.MOUSE_CLICKED, previewImageListener);

            try {
                imageView.setImage(resourceManager.getImage(hierarchy, imgName));
                imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, expandedImageListener);
                setFitToHeight(true);
                setFitToWidth(true);
            } catch (FileNotFoundException e) {
                imageView.setVisible(false);
            }
        }
    };

    EventHandler<MouseEvent> expandedImageListener = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            imageView.removeEventHandler(MouseEvent.MOUSE_CLICKED, expandedImageListener);

            try {
                imageView.setImage(resourceManager.getImagePreview(hierarchy, imgName, maxWidth * previewRatio, maxHeight * previewRatio));
                imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, previewImageListener);
                setFitToHeight(false);
                setFitToWidth(false);
            } catch (FileNotFoundException e) {
                imageView.setVisible(false);
            }
        }
    };

    public WisImgView(Double maxHeight, Double maxWidth, WisHierarchy hierarchy, ResourceManager resourceManager, String imgName) throws FileNotFoundException {
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
        this.hierarchy = hierarchy;
        this.resourceManager = resourceManager;
        this.imgName = imgName;

        imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setImage(resourceManager.getImagePreview(hierarchy, imgName, maxWidth * previewRatio, maxHeight * previewRatio));
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, previewImageListener);

        setPrefSize(imageView.getImage().getWidth(), imageView.getImage().getHeight());
        setContent(imageView);
    }
}
