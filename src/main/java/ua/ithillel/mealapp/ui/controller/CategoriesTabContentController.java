package ua.ithillel.mealapp.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import ua.ithillel.mealapp.model.vm.CategoryItemVm;
import ua.ithillel.mealapp.service.CategoryService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CategoriesTabContentController implements Initializable {
    @FXML
    public VBox categoriesContainer;

    private final CategoryService categoryService;

    public CategoriesTabContentController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<CategoryItemVm> categoryItemVms = categoryService.getCategories();

        for (CategoryItemVm categoryItemVm : categoryItemVms) {
            CategoryItemController categoryItemController = new CategoryItemController();
            categoryItemController.setModel(categoryItemVm);
            categoryItemController.updateView();

            categoriesContainer.getChildren().add(categoryItemController.getView());
        }
        System.out.println();
    }
}
