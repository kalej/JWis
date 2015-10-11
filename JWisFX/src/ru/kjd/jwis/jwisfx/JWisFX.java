package ru.kjd.jwis.jwisfx;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.kjd.jwis.core.*;
import ru.kjd.jwis.core.xml.WisChapter;
import ru.kjd.jwis.core.xml.WisHierarchy;
import ru.kjd.jwis.core.xml.WisItemElement;
import ru.kjd.jwis.core.xml.WisSubElement;
import ru.kjd.jwis.jwisfx.gui.*;

import java.net.URL;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.logging.Logger;

public class JWisFX extends Application {
    private final int TREE_WIDTH_PERCENT = 30;
    private static Stage mainWindow;
    static Logger log = Logger.getLogger(JWisFX.class.getName());

    static WisItemTabPane wisItemTabPane;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainWindow = primaryStage;
        primaryStage.setTitle("JWisFX");

        WisProperties properties = new WisProperties();
        final ResourceManager resourceManager = new ResourceManager(properties);

        WisCarSelectionDialog myDialog = new WisCarSelectionDialog(primaryStage, resourceManager);
        myDialog.showAndWait();

        final WisHierarchy wisHierarchy = resourceManager.loadXMLHierarchy(myDialog.getModel(), myDialog.getYear());

        BorderPane borderPane = new BorderPane();
        //Button changeCar = new Button("Change car");
        //Button forward = new Button("Forward");
        //Button backward = new Button("Backward");

        //HBox buttons = new HBox();
        //buttons.setSpacing(10);
        //buttons.setPadding(new Insets(10,10,0,10));
        //buttons.getChildren().addAll(changeCar, backward, forward);
        HBox statusBar = new HBox();
        statusBar.setPadding(new Insets(0,10,10,10));
        final Label name  = new Label(WisProperties.PROG_NAME);
        Label curDoc = new Label();
        statusBar.getChildren().addAll(name, curDoc);

        final GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10,10,10,10));
        ColumnConstraints col0 = new ColumnConstraints();
        col0.setPercentWidth(TREE_WIDTH_PERCENT);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(100 -TREE_WIDTH_PERCENT);
        grid.getColumnConstraints().addAll(col0,col1);

        RowConstraints row0 = new RowConstraints();
        row0.setPercentHeight(100);
        grid.getRowConstraints().add(row0);

        TreeView treeView = new WisHierarchyTreeView(wisHierarchy);
        wisItemTabPane = new WisItemTabPane(wisHierarchy, resourceManager);

        URL.setURLStreamHandlerFactory(new URLStreamHandlerFactory() {
            @Override
            public URLStreamHandler createURLStreamHandler(String protocol) {
                switch (protocol) {
                    case "wisimg":
                        return new WisImgStreamHandler(resourceManager, wisHierarchy);
                    case "wisref":
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

                if ( selectedItem instanceof  WisTreeChapterItem ){
                    wisItemTabPane.show(((WisTreeChapterItem) selectedItem).getChapter());
                } /*else if (selectedItem instanceof WisTreeElement) {
                    wisItemTabPane.show(((WisTreeElement) selectedItem).getItemElement());
                } else if ( selectedItem instanceof WisItemTreeView) {
                    wisItemTabPane.show(((WisItemTreeView) selectedItem).getItem());
                } else if (selectedItem instanceof WisTreeSectionItem) {
                    wisItemTabPane.show(((WisTreeSectionItem)selectedItem).getSection());
                } else if ( selectedItem instanceof  WisTreeSubElement) {
                    wisItemTabPane.show(((WisTreeSubElement)selectedItem).getSubElement());
                }*/
            }
        });
        grid.addColumn(0, treeView);
        grid.addColumn(1, wisItemTabPane);

        //borderPane.setTop(buttons);
        borderPane.setCenter(grid);
        borderPane.setBottom(statusBar);
        primaryStage.setScene(new Scene(borderPane));
        primaryStage.show();
    }

    private static void logDoc(Node node){
        log.info("node name: " + node.getNodeName() + "; node class: " + node.getClass().getName());

        NodeList subnodes = node.getChildNodes();
        for ( int i = 0; i < subnodes.getLength(); i++)
            logDoc(subnodes.item(i));
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getMainWindow() {
        return mainWindow;
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
}
