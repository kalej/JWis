package ru.kjd.jwis.jwisfx.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ru.kjd.jwis.core.ResourceManager;

import ru.kjd.jwis.core.WisPaths;
import ru.kjd.jwis.core.utils.StringExtractor;

import java.util.List;

/**
 * Created by anonymous on 9/30/15.
 */
public class WisCarSelectionDialog extends Stage {
    private static final double PREF_WIDTH = 160;
    private String model;
    private String year;

    public WisCarSelectionDialog(Stage owner, final ResourceManager resourceManager){
        super();
        initOwner(owner);

        Group root = new Group();
        setTitle("select");

        Scene scene = new Scene(root);
        setScene(scene);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.setPadding(new Insets(20));

        Label modelLbl = new Label("Model:");
        modelLbl.setPrefWidth(PREF_WIDTH);

        Label yearLbl = new Label("Year: ");
        yearLbl.setPrefWidth(PREF_WIDTH);

        final ComboBox<String> yearCombo = new ComboBox();
        yearCombo.setPrefWidth(PREF_WIDTH);

        final ComboBox<String> modelCombo = new ComboBox();
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
                List<String> years = resourceManager.getYears(modelCombo.getValue());

                if ( years.size() <= 0 )
                {
                    yearCombo.setDisable(true);
                    okButton.setDisable(true);
                    return;
                }

                yearCombo.setDisable(false);
                yearCombo.getItems().clear();

                for ( String year : years )
                    yearCombo.getItems().add(StringExtractor.extractYear(year));
            }
        });

        yearCombo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String year = (String)yearCombo.getValue();
                if ( year == null || year.isEmpty()  ){
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

    public String getSelectedXMLPath() {
        return WisPaths.getXMLPath(model, year);
    }
}
