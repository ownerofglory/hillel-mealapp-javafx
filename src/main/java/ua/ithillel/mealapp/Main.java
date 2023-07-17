package ua.ithillel.mealapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ua.ithillel.mealapp.client.MealClient;
import ua.ithillel.mealapp.client.TheMealDbClient;
import ua.ithillel.mealapp.model.mapper.AreaMapper;
import ua.ithillel.mealapp.model.mapper.CategoriesMapper;
import ua.ithillel.mealapp.model.mapper.MealMapper;
import ua.ithillel.mealapp.service.*;
import ua.ithillel.mealapp.ui.controller.AppControllerFactory;
import ua.ithillel.mealapp.ui.util.UiComponents;

import java.net.http.HttpClient;

public class Main extends Application {
    public static void main(String[] args) {
        System.out.println("Starting application...");

        launch(args);

        System.out.println("Application finished");
    }

    @Override
    public void start(Stage stage) throws Exception {
        MealClient mealClient = new TheMealDbClient(HttpClient.newHttpClient(), new ObjectMapper());
        MealSearchService mealSearchService = new MealSearchServiceDefault(mealClient, new MealMapper());
        CategoryService categoryService =  new CategoriesServiceDefault(mealClient, new CategoriesMapper());
        AreaService areaService = new AreaServiceDefault(mealClient, new AreaMapper());

        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(new AppControllerFactory(mealSearchService, categoryService, areaService));
        loader.setLocation(getClass().getResource(UiComponents.MAIN_SCENE_PATH));
        VBox vBox = loader.<VBox>load();

        Scene searchScene = new Scene(vBox);
        searchScene.getStylesheets().add(UiComponents.STYLES_PATH);
        stage.setScene(searchScene);
        stage.setTitle("Hillel Meal app");
        stage.show();
    }
}
