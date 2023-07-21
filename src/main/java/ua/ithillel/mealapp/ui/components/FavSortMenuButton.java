package ua.ithillel.mealapp.ui.components;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;


public class FavSortMenuButton extends MenuButton {
    public FavSortMenuButton() {
        this.getItems().add(new MenuItem("Newest"));
        this.getItems().add(new MenuItem("Oldest"));
    }
}
