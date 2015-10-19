package ru.kjd.jwis.jwisfx.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import ru.kjd.jwis.core.ResourceManager;
import ru.kjd.jwis.core.WisStrings;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by anonymous on 10/16/15.
 */
public class WisDialogController implements Initializable{
    @FXML
    ComboBox<String> modelCombo;
    @FXML
    ComboBox<String> yearCombo;
    @FXML
    Label modelLabel;
    @FXML
    Label yearLabel;
    @FXML
    Button commitButton;

    String model, year;
    public void commit(ActionEvent actionEvent) {
        model = modelCombo.getValue();
        year = yearCombo.getValue();
        commitButton.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelLabel.setText(WisStrings.get(WisStrings.STRING_MODEL));
        yearLabel.setText(WisStrings.get(WisStrings.STRING_YEAR));
        commitButton.setText(WisStrings.get(WisStrings.STRING_OK));
        yearCombo.setDisable(true);
        commitButton.setDisable(true);
        modelCombo.getItems().addAll(ResourceManager.getInstance().getModelList());
    }

    public void onYearChanged(ActionEvent actionEvent) {
        commitButton.setDisable(false);
    }

    public void onModelChanged(ActionEvent actionEvent) {
        yearCombo.setDisable(false);
        yearCombo.getItems().addAll(ResourceManager.getInstance().getYears(modelCombo.getValue()));
    }
}
