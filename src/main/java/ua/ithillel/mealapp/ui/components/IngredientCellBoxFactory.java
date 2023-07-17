package ua.ithillel.mealapp.ui.components;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import ua.ithillel.mealapp.model.vm.IngredientVm;

public class IngredientCellBoxFactory implements Callback<ListView<IngredientVm>, ListCell<IngredientVm>> {

    @Override
    public ListCell<IngredientVm> call(ListView<IngredientVm> listView) {
        return new ListCell<>() {
            @Override
            protected void updateItem(IngredientVm ingredientVm, boolean empty) {
                super.updateItem(ingredientVm, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else if (ingredientVm != null) {
                    setText(null);
                    setPrefHeight(36);
                    setGraphic(new IngredientCellDelegate(ingredientVm));
                    setHeight(90);
                } else {
                    setGraphic(null);
                    setText(null);
                }
            }
        };
    }
}
