package ua.ithillel.mealapp.ui.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import ua.ithillel.mealapp.model.vm.IngredientVm;

public class IngredientCellDelegate extends HBox {
    private final IngredientVm ingredient;

    public IngredientCellDelegate(IngredientVm ingredient) {
        this.ingredient = ingredient;

        setPadding(new Insets(12));
        Label nameLabel = new Label(ingredient.getName() + ": ");
        nameLabel.setFont(new Font(12));

        Label measureLabel = new Label(ingredient.getMeasure());
        measureLabel.setFont(new Font(12));

        this.getChildren().add(nameLabel);
        this.getChildren().add(measureLabel);
    }


}
