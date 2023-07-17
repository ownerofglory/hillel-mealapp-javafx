package ua.ithillel.mealapp.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ua.ithillel.mealapp.model.vm.AreaItemVm;
import ua.ithillel.mealapp.model.vm.CategoryShortItemVm;
import ua.ithillel.mealapp.model.vm.MealItemVm;
import ua.ithillel.mealapp.service.AreaService;
import ua.ithillel.mealapp.service.CategoryService;
import ua.ithillel.mealapp.service.MealSearchService;
import ua.ithillel.mealapp.ui.components.CountryFilterMenuButton;
import ua.ithillel.mealapp.ui.components.SearchFilterMenuButton;
import ua.ithillel.mealapp.ui.event.MealChosenEvent;
import ua.ithillel.mealapp.ui.util.UiComponents;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class SearchTabContentController implements Initializable {
    @FXML
    public FlowPane mealItemsContainer;
    @FXML
    public TextField searchTextField;
    @FXML
    public Button searchButton;
    @FXML
    public SearchFilterMenuButton categoryMenuButton;
    @FXML
    public CountryFilterMenuButton countryMenuButton;
    @FXML
    public Label filterLabel;
    @FXML
    public VBox searchTabContent;

    private final MealSearchService mealSearchService;
    private final CategoryService categoryService;
    private final AreaService areaService;


    public SearchTabContentController(MealSearchService mealSearchService, CategoryService categoryService, AreaService areaService) {
        this.mealSearchService = mealSearchService;
        this.categoryService = categoryService;
        this.areaService = areaService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fetch and render categories dropdown
        List<CategoryShortItemVm> shortCategories = categoryService.getShortCategories();
        renderCategoryFilter(shortCategories);

        // Fetch and render country dropdown
        List<AreaItemVm> areas = areaService.getAreas();
        renderFilter(areas);

        // Fetch and render meals
        List<MealItemVm> mealItemVms = mealSearchService.searchMeals("a");
        renderMeals(mealItemVms);

        searchButton.setOnMouseClicked(this::onSearch);

        categoryMenuButton.getItems()
                .forEach(item -> item.setOnAction(this::onCategoryChoose));

        countryMenuButton.getItems()
                .forEach(menuItem -> menuItem.setOnAction(this::onCountryChoose));
    }

    private void onSearch(MouseEvent event) {
        String text = searchTextField.getText();
        List<MealItemVm> mealItemVms = mealSearchService.searchMeals(text);
        mealItemsContainer.getChildren().clear();
        renderMeals(mealItemVms);

        filterLabel.setText(String.format("Results for query: \"%s\"", text));
    }

    private void onCategoryChoose(ActionEvent event) {
        MenuItem item = (MenuItem) event.getTarget();
        String text = item.getText();
        mealItemsContainer.getChildren().clear();
        CategoryShortItemVm categoryShortItemVm = new CategoryShortItemVm(text);
        List<MealItemVm> mealItemVms = mealSearchService.getMealsByCategory(categoryShortItemVm);
        renderMeals(mealItemVms);

        filterLabel.setText(String.format("Results for \"%s\" category", text));
    }

    private void onCountryChoose(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getTarget();
        String text = menuItem.getText();
        mealItemsContainer.getChildren().clear();
        AreaItemVm areaItemVm = new AreaItemVm(text);
        List<MealItemVm> mealItemVms = mealSearchService.getMealsByArea(areaItemVm);
        renderMeals(mealItemVms);

        filterLabel.setText(String.format("Results for \"%s\" cusine", text));
    }

    private void handleMealChosen(MealChosenEvent mealChosenEvent) {
        try {
            MealItemVm chosenMeal = (MealItemVm) mealChosenEvent.getSource();
            Parent parent = buildMealModal(chosenMeal);

            Stage parentStage = (Stage) this.searchTabContent.getScene().getWindow();

            Stage stage = buildModalStage(parent);
            stage.initOwner(parentStage);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void renderCategoryFilter(List<CategoryShortItemVm> categoryShortItemVms) {
        categoryShortItemVms.stream().forEach(cat -> {
            MenuItem menuItem = new MenuItem(cat.getName());
            categoryMenuButton.getItems().add(menuItem);
        });
    }

    private void renderFilter(List<AreaItemVm> categoryShortItemVms) {
        categoryShortItemVms.stream().forEach(areaItemVm -> {
            if (areaItemVm.getArea().equalsIgnoreCase("russian"))
                return;
            MenuItem menuItem = new MenuItem(areaItemVm.getArea());
            countryMenuButton.getItems().add(menuItem);
        });
    }

    private void renderMeals(List<MealItemVm> mealItemVms) {
        for (MealItemVm mealItem : mealItemVms) {
            MealItemController mealItemController = new MealItemController();
            mealItemController.setModel(mealItem);
            mealItemController.setOnMealChosenEvent(this::handleMealChosen);
            mealItemController.updateView();

            mealItemsContainer.getChildren().add(mealItemController.getView());
        }
    }

    private Parent buildMealModal(MealItemVm chosenMeal) throws IOException {
        URL resourceUrl = Objects.requireNonNull(getClass().getResource(UiComponents.MEAL_MODAL_PATH));
        FXMLLoader loader = new FXMLLoader(resourceUrl);
        loader.setControllerFactory(new AppControllerFactory(mealSearchService, categoryService, areaService));
        MealModalController mealModelController = new MealModalController();

        MealItemVm mealById = mealSearchService.getMealById(chosenMeal.getId());

        mealModelController.setModel(mealById);
        loader.setController(mealModelController);
        Parent parent = loader.load();

        mealModelController.updateUi();

        return parent;
    }

    private Stage buildModalStage(Parent parent) {
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        scene.getStylesheets().add(UiComponents.STYLES_PATH);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        return stage;
    }
}
