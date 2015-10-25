package ru.kjd.jwis.jwisfx.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import ru.kjd.jwis.core.WisStrings;
import ru.kjd.jwis.jwisfx.JWisController;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created by Николай on 25.10.2015.
 */
public class CarChangeListener implements ChangeListener<String> {
    JWisController jWisController;

    public CarChangeListener(JWisController jWisController){
        this.jWisController = jWisController;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        try {
            this.jWisController.onCarChanged();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(WisStrings.get(WisStrings.STRING_COULD_NOT_FIND));
            alert.setContentText(e.getLocalizedMessage());

            alert.showAndWait();
        }
    }
}
