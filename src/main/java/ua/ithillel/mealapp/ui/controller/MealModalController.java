package ua.ithillel.mealapp.ui.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import ua.ithillel.mealapp.model.vm.MealItemVm;
import ua.ithillel.mealapp.ui.components.IngredientsListView;
import ua.ithillel.mealapp.ui.event.MealSetFavouriteEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MealModalController implements Initializable {
    @FXML
    public ImageView mealModalImage;
    @FXML
    public Label mealModalTitle;
    @FXML
    public Text mealModalRecipeText;
    @FXML
    public IngredientsListView ingredientsListView;
    @FXML
    public Button addToFavButton;

    private EventHandler<MealSetFavouriteEvent> onMealSetFavourite;

    private MealItemVm model;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addToFavButton.setOnMouseClicked(event -> {
            MealSetFavouriteEvent mealSetFavouriteEvent
                    = new MealSetFavouriteEvent(model, event.getTarget(), event.getEventType());

            onMealSetFavourite.handle(mealSetFavouriteEvent);
        });
    }

    public void setModel(MealItemVm model) {
        this.model = model;
    }

    public void setOnMealSetFavourite(EventHandler<MealSetFavouriteEvent> onMealSetFavourite) {
        this.onMealSetFavourite = onMealSetFavourite;
    }

    public void updateUi() {
        try {
            this.mealModalImage.setImage(new Image(new URL(model.getImageUrl()).openStream()));
            this.mealModalTitle.setText(model.getName());
            this.mealModalRecipeText.setText(model.getRecipe());
            this.ingredientsListView.addIngredients(model.getIngredientVms());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
