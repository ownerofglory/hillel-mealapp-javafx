package ua.ithillel.mealapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ua.ithillel.mealapp.client.MealClient;
import ua.ithillel.mealapp.client.TheMealDbClient;
import ua.ithillel.mealapp.dao.FavouriteMealDao;
import ua.ithillel.mealapp.dao.FavouriteMealJdbcDao;
import ua.ithillel.mealapp.db.DbSchemaInitializer;
import ua.ithillel.mealapp.exception.DbInitException;
import ua.ithillel.mealapp.model.mapper.AreaMapperDefault;
import ua.ithillel.mealapp.model.mapper.CategoriesMapperDefault;
import ua.ithillel.mealapp.model.mapper.MealMapperDefault;
import ua.ithillel.mealapp.service.*;
import ua.ithillel.mealapp.ui.controller.AppControllerFactory;
import ua.ithillel.mealapp.ui.util.UiComponents;

import java.io.IOException;
import java.net.http.HttpClient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {
    private static Connection connection;

    public static void main(String[] args) {
        System.out.println("Starting application...");

        launch(args);

        System.out.println("Application finished");
    }

    @Override
    public void start(Stage stage)  {
        try {
            // create a DB connection string
            String sqliteConnStringEnv = System.getenv("SQLITE_CONN_STRING");
            String sqliteConnString = sqliteConnStringEnv != null
                    ? sqliteConnStringEnv : System.getProperty("sqliteConnString");

            if (sqliteConnString == null) {
                sqliteConnString = "jdbc:sqlite:meal-app.db";
            }

            System.out.println("Connection string: " + sqliteConnString);

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(sqliteConnString);

            // init DB schema
            DbSchemaInitializer.init(connection, "sqlite-schema.sql");


            // create DAO
            FavouriteMealDao favouriteMealDao = new FavouriteMealJdbcDao(connection);
            // create client
            MealClient mealClient = new TheMealDbClient(HttpClient.newHttpClient(), new ObjectMapper());
            // create services
            MealSearchService mealSearchService = new MealSearchServiceDefault(mealClient, new MealMapperDefault(), favouriteMealDao);
            CategoryService categoryService = new CategoriesServiceDefault(mealClient, new CategoriesMapperDefault());
            AreaService areaService = new AreaServiceDefault(mealClient, new AreaMapperDefault());

            // load UI initially
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(new AppControllerFactory(mealSearchService, categoryService, areaService));
            loader.setLocation(getClass().getResource(UiComponents.MAIN_SCENE_PATH));
            VBox vBox = loader.<VBox>load();

            Scene searchScene = new Scene(vBox);
            searchScene.getStylesheets().add(UiComponents.STYLES_PATH);
            stage.setScene(searchScene);
            stage.setTitle("Hillel Meal app");
            stage.show();
        } catch (DbInitException e) {
            System.out.println("Unable to initialize database: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load Sqlite driver");
        } catch (IOException | SQLException e) {
            System.out.println("Unable to load init scene: " + e.getMessage());
        }
    }

    @Override
    public void stop() throws Exception {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Unable to close connection: " + e.getMessage());
            }
        }
        super.stop();
    }
}
