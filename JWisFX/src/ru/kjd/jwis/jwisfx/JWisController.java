package ru.kjd.jwis.jwisfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Pair;
import ru.kjd.jwis.core.ResourceManager;
import ru.kjd.jwis.core.WisProperties;
import ru.kjd.jwis.core.WisStrings;
import ru.kjd.jwis.core.enums.WisItemType;
import ru.kjd.jwis.core.xml.*;
import ru.kjd.jwis.jwisfx.gui.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by ������� on 24.10.2015.
 */
public class JWisController implements Initializable {
    @FXML
    Button selectButton;
    @FXML
    Button backButton;
    @FXML
    Button fwdButton;
    @FXML
    Label statusLabel;
    @FXML
    Label docLabel;
    @FXML
    Label picLabel;
    @FXML
    TreeView wisTreeView;
    @FXML
    TabPane wisTabPane;
    @FXML
    Tab techDataTab;
    @FXML
    Tab specialToolsTab;
    @FXML
    Tab techDescTab;
    @FXML
    Tab commonTrblshootTab;
    @FXML
    Tab faultCodeTab;
    @FXML
    Tab symptomsTab;
    @FXML
    Tab replacementTab;
    @FXML
    Tab locationTab;
    @FXML
    Tab electricsTab;
    @FXML
    Tab bulletinsTab;
    @FXML
    Tab serviceTab;
    @FXML
    CheckBox marketChbox;
    @FXML
    ComboBox<String> marketCombo;

    Map<WisItemType, Tab> tabMap = new HashMap<>();

    private StringProperty model = new SimpleStringProperty();
    private StringProperty year = new SimpleStringProperty();

    public void onSelectClicked(Event event) {
        changeCar();
    }

    public void onBackClicked(Event event) {

    }

    public void onFwdClicked(Event event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectButton.setGraphic(new ImageView("/main/change.png"));
        backButton.setGraphic(new ImageView("/main/backwd.png"));
        fwdButton.setGraphic(new ImageView("/main/forwd.png"));
        marketChbox.setText(WisStrings.get(WisStrings.STRING_MARKET));
        initTabs();

        ChangeListener<String> carChangeListener = new CarChangeListener(this);
        model.addListener(carChangeListener);
        year.addListener(carChangeListener);

        changeCar();
    }

    private void initTabs() {
        tabMap.put(WisItemType.TECH_DATA, techDataTab);
        tabMap.put(WisItemType.SPECIAL_TOOLS, specialToolsTab);
        tabMap.put(WisItemType.TECH_DESC, techDescTab);
        tabMap.put(WisItemType.TROUBLESHOOT, commonTrblshootTab);
        tabMap.put(WisItemType.FAULT_CODES, faultCodeTab);
        tabMap.put(WisItemType.SYMPTOMS, symptomsTab);
        tabMap.put(WisItemType.REPLACEMENT, replacementTab);
        tabMap.put(WisItemType.LOCATION, locationTab);
        tabMap.put(WisItemType.ELECTRICS, electricsTab);
        tabMap.put(WisItemType.BULLETINS, bulletinsTab);
        tabMap.put(WisItemType.SERVICE, serviceTab);

        for (WisItemType itemType : tabMap.keySet()) {
            Tab tab = tabMap.get(itemType);
            tab.setGraphic(new ImageView(itemType.getPicture()));
        }
    }

    private void changeCar() {
        WisDialog dialog = new WisDialog(null);
        dialog.showAndWait();
        Pair<String, String> result = dialog.getResult();

        model.setValue(result.getKey());
        year.setValue(result.getValue());
    }

    public void onCarChanged() throws IOException, JAXBException {
        if (model.getValue() == null || year.getValue() == null)
            return;

        WisHierarchy hierarchy = ResourceManager.getInstance().loadXMLHierarchy(model.getValue(), year.getValue());
        JWisHistory.setCurrentHierarchy(hierarchy);
        WisHierarchyTreeView.makeTreeView(wisTreeView, hierarchy);
        marketCombo.getItems().clear();
        String[] markets = hierarchy.getMarkets();
        Arrays.sort(markets);
        marketCombo.getItems().setAll(markets);
        marketCombo.getSelectionModel().select(WisProperties.getLanguage().getShortName().toUpperCase());

        wisTreeView.getSelectionModel().selectedItemProperty().addListener(new WisHierarchyTreeSelectionListener(this));
    }

    public void show(WisTreeSectionItem wisTreeSectionItem) {
        show(wisTreeSectionItem.getSection());
    }

    private void show(WisSection section) {
        clearTabs();

        JWisHistory.setCurrentSection(section);

        bulletinsTab.setDisable(false);
        wisTabPane.getSelectionModel().select(bulletinsTab);

        TreeView<String> bulletinsTree = new TreeView<>();
        TreeItem root = new TreeItem("hide me");
        bulletinsTree.setRoot(root);
        bulletinsTree.setShowRoot(false);
        root.setExpanded(true);

        for (WisChapter chapter : section.getChapters()) {
            for (WisItem item : chapter.getItems()) {
                if (item.getType() == WisItemType.BULLETINS) {
                    for (WisItemElement itemElement : item.getElements()) {
                        if (marketChbox.isSelected() && !itemElement.isApplicable(marketCombo.getValue()))
                            continue;

                        List<WisSubElement> subElementList = itemElement.getSubElements();
                        if (subElementList != null && subElementList.size() > 0) {
                            TreeItem parent = new WisTreeElement(itemElement);
                            root.getChildren().add(parent);
                        }
                    }
                }
            }
        }
        bulletinsTree.getSelectionModel().selectedItemProperty().addListener(new WisTreeItemSelectionListener(this));
        bulletinsTab.setContent(bulletinsTree);
    }

    public void show(WisTreeChapterItem wisTreeChapterItem) {
        show(wisTreeChapterItem.getChapter());
    }

    private void show(WisChapter chapter) {
        clearTabs();

        JWisHistory.setCurrentChapter(chapter);
        for (WisItem item : chapter.getItems()) {
            WisItemType type = item.getType();
            Tab tab = tabMap.get(type);
            tab.setDisable(false);
            WisItemTreeView wisItemTreeView = new WisItemTreeView(item);
            wisItemTreeView.getSelectionModel().selectedItemProperty().addListener(new WisTreeItemSelectionListener(this));
            tab.setContent(wisItemTreeView);
        }
    }

    public void onElementSelected(WisTreeElement wisTreeElement) {
        show(wisTreeElement.getItemElement());
    }

    private void show(WisItemElement itemElement) {
        JWisHistory.setCurrentElement(itemElement);

        Tab currentTab = wisTabPane.getSelectionModel().getSelectedItem();
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);

        webView.getEngine().load("wisdoc://" + itemElement.getDocId());

        currentTab.setContent(webView);
        statusLabel.setText(itemElement.toString());
    }

    private void clearTabs() {
        for (Tab tab : tabMap.values()) {
            tab.setDisable(true);
            tab.setContent(null);
        }
    }

    public void onMarketFilter(ActionEvent actionEvent) {
        marketCombo.setDisable(!marketChbox.isSelected());
    }

    public void show(WisTreeSubElement wisTreeSubElement) {
        show(wisTreeSubElement.getSubElement());

    }

    private void show(WisSubElement subElement) {
        JWisHistory.setCurrentSubelement(subElement);

        Tab currentTab = wisTabPane.getSelectionModel().getSelectedItem();
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);

        WisItemElement itemElement = subElement.getParent();
        webView.getEngine().load("wisdoc://" + itemElement.getDocId() + "#" + subElement.getSiSubId());

        currentTab.setContent(webView);
    }
}
