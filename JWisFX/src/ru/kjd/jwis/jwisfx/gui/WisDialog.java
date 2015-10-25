package ru.kjd.jwis.jwisfx.gui;

import com.sun.javafx.tk.Toolkit;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Pair;
import ru.kjd.jwis.core.WisStrings;

import java.io.IOException;

/**
 * Created by anonymous on 10/19/15.
 */
public class WisDialog extends Dialog<Pair<String,String>> {
    private ComboBox<String> modelCombo, yearCombo;

    public WisDialog(Parent parent){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("jwisfxdialog.fxml"));
        initModality(Modality.APPLICATION_MODAL);
        initStyle(StageStyle.UNDECORATED);

        try {
            DialogPane dialogPane = (DialogPane)fxmlLoader.load();

            GridPane grid = (GridPane)dialogPane.getContent();
            for( Node node : grid.getChildren() ) {
                if (node.getId().equals("modelCombo"))
                    modelCombo = (ComboBox<String>)node;
                if (node.getId().equals("yearCombo"))
                    yearCombo = (ComboBox<String>)node;

            }
            setDialogPane(dialogPane);
            ButtonType submitButtonType = new ButtonType(WisStrings.get(WisStrings.STRING_OK), ButtonBar.ButtonData.OK_DONE);
            getDialogPane().getButtonTypes().add(submitButtonType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setResultConverter(new Callback<ButtonType, Pair<String, String>>() {
            @Override
            public Pair<String, String> call(ButtonType param) {
                String model = modelCombo.getValue();
                String year = yearCombo.getValue();
                return new Pair<>(model, year);
            }
        });
    }
}
