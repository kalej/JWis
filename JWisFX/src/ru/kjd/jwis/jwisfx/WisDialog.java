package ru.kjd.jwis.jwisfx;

import com.sun.javafx.tk.Toolkit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;

import java.io.IOException;

/**
 * Created by anonymous on 10/19/15.
 */
public class WisDialog extends Stage {
    @FXML
    private ComboBox<String> modelCombo;
    @FXML
    private ComboBox<String> yearCombo;

    public WisDialog(Parent parent){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("jwisfxdialog.fxml"));
        fxmlLoader.setController(this);

        initModality(Modality.APPLICATION_MODAL);
        //initStyle(StageStyle.UNDECORATED);

        try{
            setScene(new Scene((Parent) fxmlLoader.load()));
        } catch (IOException ex) {

        }
    }

    public Pair<String, String> getResult(){
        showAndWait();
        return new Pair<>(modelCombo.getValue(), yearCombo.getValue());
    }
}
