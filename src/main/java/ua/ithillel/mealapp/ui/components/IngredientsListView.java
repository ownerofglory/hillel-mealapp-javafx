package ua.ithillel.mealapp.ui.components;

import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import ua.ithillel.mealapp.model.vm.IngredientVm;

import java.util.List;

public class IngredientsListView extends ListView<IngredientVm>  {
    public IngredientsListView() {
        setCellFactory(new IngredientCellBoxFactory());
        setMaxHeight(400);
    }

    public void addIngredients(List<IngredientVm> ingredients) {
        if (ingredients != null) {
            setItems(FXCollections.observableList(ingredients));
        }
    }
}
