package ua.ithillel.mealapp.ui.components;


import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import ua.ithillel.mealapp.model.vm.AreaItemVm;

import java.util.List;

public class CountryFilterMenuButton extends MenuButton {
    private List<AreaItemVm> countries;

    public void setCountries(List<AreaItemVm> countries) {
        this.countries = countries;
    }

    public void updateView() {
        countries.stream().forEach(country -> {
            MenuItem menuItem = new MenuItem(country.getArea());
            this.getItems().add(menuItem);
        });
    }
}
