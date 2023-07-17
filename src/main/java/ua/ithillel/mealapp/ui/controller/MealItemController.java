package ua.ithillel.mealapp.ui.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import ua.ithillel.mealapp.model.vm.MealItemVm;
import ua.ithillel.mealapp.ui.event.MealChosenEvent;
import ua.ithillel.mealapp.ui.util.LoaderUtil;
import ua.ithillel.mealapp.ui.util.UiComponents;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MealItemController implements Initializable {
    @FXML
    public ImageView mealImage;
    @FXML
    public Label mealItemTitle;
    @FXML
    public VBox mealItem;

    private MealItemVm model;

    private EventHandler<MealChosenEvent> onMealChosenEvent;


    public MealItemController() {
        try {
            LoaderUtil.loadFxml(UiComponents.MEAL_ITEM_PATH,this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setModel(MealItemVm model) {
        this.model = model;
    }

    public void setOnMealChosenEvent(EventHandler<MealChosenEvent> onMealChosenEvent) {
        this.onMealChosenEvent = onMealChosenEvent;
    }
    public void updateView() {
        try {
            this.mealItemTitle.setText(model.getName());
            this.mealImage.setImage(new Image(new URL(model.getImageUrl()).openStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Node getView() {
        return mealItem;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.mealItem.setOnMouseClicked(mouseEvent -> {
            MealChosenEvent mealChosenEvent = new MealChosenEvent(this.model, this.mealItem, MouseEvent.ANY);
            onMealChosenEvent.handle(mealChosenEvent);
        });
    }
}
