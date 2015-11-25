package ru.kjd.jwis.jwisfx.gui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ru.kjd.jwis.core.ResourceManager;
import ru.kjd.jwis.core.WisStrings;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by anonymous on 10/16/15.
 */
public class WisDialogController implements Initializable{
    @FXML
    private ComboBox<String> modelCombo;
    @FXML
    private ComboBox<String> yearCombo;
    @FXML
    private Label modelLabel;
    @FXML
    private Label yearLabel;
    @FXML
    private DialogPane dialogPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelLabel.setText(WisStrings.get(WisStrings.STRING_MODEL));
        yearLabel.setText(WisStrings.get(WisStrings.STRING_YEAR));
        yearCombo.setDisable(true);
        modelCombo.getItems().addAll(ResourceManager.getInstance().getModelList());

        dialogPane.setHeaderText(WisStrings.get(WisStrings.STRING_CHOOSE_MODEL_AND_YEAR));
    }

    public void onYearChanged(ActionEvent actionEvent) {
        ButtonType submitButtonType = new ButtonType(WisStrings.get(WisStrings.STRING_OK), ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().add(submitButtonType);
        dialogPane.getScene().getWindow().sizeToScene();
    }

    public void onModelChanged(ActionEvent actionEvent) {
        yearCombo.setDisable(false);
        yearCombo.getItems().addAll(ResourceManager.getInstance().getYears(modelCombo.getValue()));
    }
}
