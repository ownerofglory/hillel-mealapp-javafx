package ua.ithillel.mealapp.ui.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ua.ithillel.mealapp.exception.MealAppException;
import ua.ithillel.mealapp.model.vm.MealItemVm;
import ua.ithillel.mealapp.service.MealSearchService;
import ua.ithillel.mealapp.ui.components.FavSortMenuButton;
import ua.ithillel.mealapp.ui.event.MealChosenEvent;
import ua.ithillel.mealapp.ui.event.MealSetFavouriteEvent;
import ua.ithillel.mealapp.ui.event.MealUnsetFavourite;
import ua.ithillel.mealapp.ui.util.UiComponents;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class FavouritesTabContentController implements Initializable {
    @FXML
    public FavSortMenuButton favSortMenuButton;
    @FXML
    public Label sortLabel;
    @FXML
    public FlowPane favMealItemsContainer;
    @FXML
    public VBox favouriteTabContent;

    private boolean currentOrder;

    private final MealSearchService mealService;

    public FavouritesTabContentController(MealSearchService mealService) {
        this.mealService = mealService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<MealItemVm> favouriteMeals = mealService.getFavouriteMeals(true);
            renderMeals(favouriteMeals);

            favSortMenuButton.getItems()
                    .forEach(item -> item.setOnAction(this::handleSort));
        } catch (MealAppException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleSort(ActionEvent event) {
        try {
            MenuItem chosenItem = (MenuItem) event.getTarget();
            String sortText = chosenItem.getText();

            boolean order = sortText.equalsIgnoreCase("newest");
            currentOrder = order;

            List<MealItemVm> favouriteMeals = mealService.getFavouriteMeals(order);

            clearView();
            renderMeals(favouriteMeals);

        } catch (MealAppException e) {
            throw new RuntimeException(e);
        }
    }

    private void renderMeals(List<MealItemVm> mealItemVms) {
        for (MealItemVm mealItem : mealItemVms) {
            MealItemController mealItemController = new MealItemController();
            mealItemController.setModel(mealItem);
            mealItemController.setOnMealChosenEvent(this::handleMealChosen);
            mealItemController.updateView();

            favMealItemsContainer.getChildren().add(mealItemController.getView());
        }
    }

    private void handleMealChosen(MealChosenEvent mealChosenEvent) {
        try {
            MealItemVm chosenMeal = (MealItemVm) mealChosenEvent.getSource();
            Parent parent = buildMealModal(chosenMeal);

            Stage parentStage = (Stage) this.favouriteTabContent.getScene().getWindow();
            Stage stage = buildModalStage(parent);
            stage.initOwner(parentStage);
            stage.setOnCloseRequest(this::handleOnModalClose);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (MealAppException e) {
            throw new RuntimeException(e);
        }
    }

    private Parent buildMealModal(MealItemVm chosenMeal) throws IOException, MealAppException {
        URL resourceUrl = Objects.requireNonNull(getClass().getResource(UiComponents.MEAL_MODAL_PATH));
        FXMLLoader loader = new FXMLLoader(resourceUrl);

        MealModalController mealModelController = new MealModalController();
        mealModelController.setOnMealSetFavourite(this::handleMealFavouriteToggle);

        MealItemVm mealById = mealService.getFavouriteMealByMealId(chosenMeal.getId());

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

    private void handleMealFavouriteToggle(MealSetFavouriteEvent event) {
        try {
            MealItemVm favouriteMeal = (MealItemVm) event.getSource();

            mealService.toggleFavouriteMeal(favouriteMeal);
        } catch (MealAppException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleOnModalClose(WindowEvent event) {
        try {
            clearView();
            List<MealItemVm> favouriteMeals = mealService.getFavouriteMeals(currentOrder);
            renderMeals(favouriteMeals);
        } catch (MealAppException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearView() {
        favMealItemsContainer.getChildren().clear();
    }
}
