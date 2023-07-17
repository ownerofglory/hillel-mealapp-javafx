package ua.ithillel.mealapp.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TabPane;
import ua.ithillel.mealapp.service.AreaService;
import ua.ithillel.mealapp.service.CategoryService;
import ua.ithillel.mealapp.service.MealSearchService;
import ua.ithillel.mealapp.ui.util.LoaderUtil;
import ua.ithillel.mealapp.ui.util.UiComponents;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {
    @FXML
    public TabPane tabPane;

    private final MealSearchService mealSearchService;
    private final CategoryService categoryService;
    private final AreaService areaService;

    public MainSceneController(MealSearchService mealSearchService, CategoryService categoryService, AreaService areaService) {
        this.mealSearchService = mealSearchService;
        this.categoryService = categoryService;
        this.areaService = areaService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Node content = LoaderUtil.loadFxml(UiComponents.SEARCH_TAB_CONTENT_PATH,  new AppControllerFactory(mealSearchService, categoryService, areaService));

            tabPane.getSelectionModel().getSelectedItem().setContent(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
    }
}
