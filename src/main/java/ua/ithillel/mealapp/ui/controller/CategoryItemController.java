package ua.ithillel.mealapp.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import ua.ithillel.mealapp.model.vm.CategoryItemVm;
import ua.ithillel.mealapp.ui.util.LoaderUtil;
import ua.ithillel.mealapp.ui.util.UiComponents;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CategoryItemController implements Initializable {
    @FXML
    public Text categoryDescriptionText;
    @FXML
    public Label categoryItemTitle;
    @FXML
    public ImageView categoryImage;
    @FXML
    public HBox categoryItem;

    private CategoryItemVm model;

    public CategoryItemController() {
        try {
            LoaderUtil.loadFxml(UiComponents.CATEGORY_ITEM_PATH, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setModel(CategoryItemVm model) {
        this.model = model;
    }

    public void updateView() {
        try {
            this.categoryItemTitle.setText(model.getName());
            this.categoryImage.setImage(new Image(new URL(model.getImageUrl()).openStream()));
            this.categoryDescriptionText.setText(model.getDescription());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Node getView() {
        return categoryItem;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
