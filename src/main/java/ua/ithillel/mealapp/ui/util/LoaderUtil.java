package ua.ithillel.mealapp.ui.util;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.util.Callback;
import ua.ithillel.mealapp.ui.controller.AppControllerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class LoaderUtil {
    public static <T> T loadFxml(String path, Callback<Class<?>, Object> controllerFactory) throws IOException {
        URL resourceUrl = Objects.requireNonNull(LoaderUtil.class.getResource(path));
        FXMLLoader loader = new FXMLLoader(resourceUrl);
        loader.setControllerFactory(controllerFactory);
        return loader.<T>load();
    }

    public static <T> T loadFxml(String path, Initializable controller) throws IOException {
        URL resourceUrl = Objects.requireNonNull(LoaderUtil.class.getResource(path));
        FXMLLoader loader = new FXMLLoader(resourceUrl);
        loader.setController(controller);
        return loader.<T>load();
    }
}
