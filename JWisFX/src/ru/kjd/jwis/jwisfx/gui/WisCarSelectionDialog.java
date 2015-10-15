package ru.kjd.jwis.jwisfx.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.kjd.jwis.core.ResourceManager;
import ru.kjd.jwis.core.utils.StringExtractor;

import java.util.List;

public class WisCarSelectionDialog extends Stage {
    private static final double PREF_WIDTH = 160;
    private String model;
    private String year;

    public WisCarSelectionDialog(Stage owner, final ResourceManager resourceManager) {
        super();
        initOwner(owner);

        initStyle(StageStyle.UNDECORATED);

        Group root = new Group();
        setTitle("select");

        Scene scene = new Scene(root);
        setScene(scene);

        GridPane gridPane = new GridPane();

        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.setPadding(new Insets(20));

        Label modelLbl = new Label("Model:");
        modelLbl.setPrefWidth(PREF_WIDTH);

        Label yearLbl = new Label("Year: ");
        yearLbl.setPrefWidth(PREF_WIDTH);

        final ComboBox<String> yearCombo = new ComboBox<>();
        yearCombo.setPrefWidth(PREF_WIDTH);

        final ComboBox<String> modelCombo = new ComboBox<>();
        modelCombo.getItems().addAll(resourceManager.getModelList());
        modelCombo.setPrefWidth(PREF_WIDTH);

        final Button okButton = new Button("Ok");
        okButton.setPrefWidth(PREF_WIDTH);
        okButton.setDisable(true);

        gridPane.addRow(0, modelLbl, yearLbl);
        gridPane.addRow(1, modelCombo, yearCombo);
        gridPane.add(okButton, 1, 2);

        modelCombo.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                List<String> years = resourceManager.getXmls(modelCombo.getValue());

                if (years.size() <= 0) {
                    yearCombo.setDisable(true);
                    okButton.setDisable(true);
                    return;
                }

                yearCombo.setDisable(false);
                yearCombo.getItems().clear();

                for (String year : years)
                    yearCombo.getItems().add(StringExtractor.extractYear(year));
            }
        });

        yearCombo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String year = yearCombo.getValue();
                if (year == null || year.isEmpty()) {
                    okButton.setDisable(true);
                }

                okButton.setDisable(false);
            }
        });

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model = modelCombo.getValue();
                year = yearCombo.getValue();

                close();
            }
        });
        root.getChildren().add(gridPane);
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }
}
