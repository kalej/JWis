package ru.kjd.jwis.jwisfx;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ru.kjd.jwis.core.*;
import ru.kjd.jwis.core.xml.WisChapter;
import ru.kjd.jwis.core.xml.WisHierarchy;
import ru.kjd.jwis.core.xml.WisItemElement;
import ru.kjd.jwis.core.xml.WisSubElement;
import ru.kjd.jwis.jwisfx.gui.WisCarSelectionDialog;
import ru.kjd.jwis.jwisfx.gui.WisHierarchyTreeView;
import ru.kjd.jwis.jwisfx.gui.WisItemTabPane;
import ru.kjd.jwis.jwisfx.gui.WisTreeChapterItem;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URL;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.logging.Logger;

public class JWisFX extends Application {
    static Logger log = Logger.getLogger(JWisFX.class.getName());
    static WisItemTabPane wisItemTabPane;
    private final int TREE_WIDTH_PERCENT = 30;
    WisHierarchyTreeView treeView;
    WisHierarchy wisHierarchy;
    ResourceManager resourceManager;

    public static void main(String[] args) {
        launch(args);
    }

    public static void showChapter(WisChapter chapter) {
        wisItemTabPane.show(chapter);
    }

    public static void showItemElement(WisItemElement itemElement) {
        wisItemTabPane.show(itemElement);
    }

    public static void showSubElement(WisSubElement subElement) {
        wisItemTabPane.show(subElement);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("JWisFX");

        WisProperties properties = new WisProperties();
        resourceManager = new ResourceManager(properties);

        WisCarSelectionDialog myDialog = new WisCarSelectionDialog(primaryStage, resourceManager);
        myDialog.showAndWait();

        wisHierarchy = resourceManager.loadXMLHierarchy(myDialog.getModel(), myDialog.getYear());

        BorderPane borderPane = new BorderPane();
        Button changeCar = new Button("Change car");
        final Button forward = new Button("Forward");
        Button backward = new Button("Backward");

        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.setPadding(new Insets(10, 10, 0, 10));
        buttons.getChildren().addAll(changeCar, backward, forward);
        HBox statusBar = new HBox();
        statusBar.setPadding(new Insets(0, 10, 10, 10));
        final Label name = new Label(WisProperties.PROG_NAME);
        Label curDoc = new Label();
        statusBar.getChildren().addAll(name, curDoc);

        final GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        ColumnConstraints col0 = new ColumnConstraints();
        col0.setPercentWidth(TREE_WIDTH_PERCENT);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(100 - TREE_WIDTH_PERCENT);
        grid.getColumnConstraints().addAll(col0, col1);

        RowConstraints row0 = new RowConstraints();
        row0.setPercentHeight(100);
        grid.getRowConstraints().add(row0);

        treeView = new WisHierarchyTreeView(wisHierarchy);
        wisItemTabPane = new WisItemTabPane(wisHierarchy, resourceManager);

        URL.setURLStreamHandlerFactory(new URLStreamHandlerFactory() {
            @Override
            public URLStreamHandler createURLStreamHandler(String protocol) {
                switch (protocol) {
                    case "wisimg":
                        return new WisImgStreamHandler(resourceManager, wisHierarchy);
                    case "wisref":
                        return new WisRefStreamHandler(resourceManager, wisHierarchy, wisItemTabPane);
                    case "wisdoc":
                        return new WisDocStreamHandler(resourceManager, wisHierarchy);
                    default:
                        System.err.println("Protocol: " + protocol);
                        return null;
                }
            }
        });

        treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                TreeItem selectedItem = (TreeItem) newValue;

                if (selectedItem instanceof WisTreeChapterItem) {
                    wisItemTabPane.show(((WisTreeChapterItem) selectedItem).getChapter());
                }
            }
        });
        grid.addColumn(0, treeView);
        grid.addColumn(1, wisItemTabPane);

        changeCar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    WisCarSelectionDialog myDialog = new WisCarSelectionDialog(primaryStage, resourceManager);
                    myDialog.showAndWait();

                    wisHierarchy = resourceManager.loadXMLHierarchy(myDialog.getModel(), myDialog.getYear());
                    treeView.setHierarchy(wisHierarchy);
                    wisItemTabPane.clearTabs();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            }
        });

        forward.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                wisItemTabPane.forward();
            }
        });

        backward.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                wisItemTabPane.backward();
            }
        });
        borderPane.setTop(buttons);
        borderPane.setCenter(grid);
        borderPane.setBottom(statusBar);
        primaryStage.setScene(new Scene(borderPane));
        primaryStage.show();
    }
}
