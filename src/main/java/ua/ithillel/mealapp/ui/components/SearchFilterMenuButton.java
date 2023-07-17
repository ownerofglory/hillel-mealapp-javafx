package ua.ithillel.mealapp.ui.components;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class SearchFilterMenuButton extends MenuButton {
    public SearchFilterMenuButton() {
        getItems().add(new MenuItem("Cat 1"));
        getItems().add(new MenuItem("Cat 2"));
        getItems().add(new MenuItem("Cat 3"));
    }
}
