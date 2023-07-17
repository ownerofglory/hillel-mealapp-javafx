package ua.ithillel.mealapp.ui.controller;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import ua.ithillel.mealapp.service.AreaService;
import ua.ithillel.mealapp.service.CategoryService;
import ua.ithillel.mealapp.service.MealSearchService;
import ua.ithillel.mealapp.ui.util.LoaderUtil;
import ua.ithillel.mealapp.ui.util.UiComponents;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TabsController implements Initializable {
    @FXML
    public Tab searchTab;
    @FXML
    public Tab categoriesTab;
    @FXML
    public Tab favouriteTab;
    @FXML
    public TabPane tabPane;

    private final MealSearchService mealSearchService;
    private final CategoryService categoryService;
    private final AreaService areaService;

    public TabsController(MealSearchService mealSearchService, CategoryService categoryService, AreaService areaService) {
        this.mealSearchService = mealSearchService;
        this.categoryService = categoryService;
        this.areaService = areaService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabPane.getSelectionModel()
                .selectedItemProperty()
                .addListener(this::onTabSelect);
    }

    private void onTabSelect(ObservableValue<? extends Tab> val, Tab oldTab, Tab newTab) {
        try {
            Node content;
            if (newTab == categoriesTab) {
                content = loadTab(UiComponents.CATEGORIES_TAB_CONTENT_PATH);
            } else if (newTab == searchTab) {
                content = loadTab(UiComponents.SEARCH_TAB_CONTENT_PATH);
            } else if (newTab == favouriteTab) {
                content = loadTab(UiComponents.FAVOURITES_TAB_CONTENT_PATH);
            } else {
                content = newTab.getContent();
            }
            newTab.setContent(content);
        } catch (IOException e) {
            System.out.println("Unable to load tab");
        }
    }

    private Node loadTab(String path) throws IOException {
        return LoaderUtil.loadFxml(path, new AppControllerFactory(mealSearchService, categoryService, areaService));
    }
}
