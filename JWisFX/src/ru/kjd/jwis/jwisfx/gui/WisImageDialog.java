package ru.kjd.jwis.jwisfx.gui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ru.kjd.jwis.core.ResourceManager;
import ru.kjd.jwis.core.xml.WisHierarchy;

import java.io.IOException;

public class WisImageDialog extends Stage{
    public WisImageDialog(Stage owner, WisHierarchy hierarchy, ResourceManager resourceManager, String imageName) throws IOException {
        super();
        initOwner(owner);

        Group root = new Group();
        setTitle(imageName);

        Scene scene = new Scene(root);
        setScene(scene);

        //ScrollPane scrollPane = new ScrollPane();
        //scrollPane.setFitToHeight(true);
        //scrollPane.setFitToWidth(true);

        ImageView imageView = new ImageView();

        Image image = resourceManager.getImage(hierarchy, imageName);
        imageView.setImage(image);

        //scrollPane.setContent(imageView);

        root.getChildren().add(imageView);
    }
}
