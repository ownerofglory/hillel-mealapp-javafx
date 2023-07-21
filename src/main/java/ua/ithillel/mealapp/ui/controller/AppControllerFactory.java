package ua.ithillel.mealapp.ui.controller;

import javafx.util.Callback;
import ua.ithillel.mealapp.service.AreaService;
import ua.ithillel.mealapp.service.CategoryService;
import ua.ithillel.mealapp.service.MealSearchService;

import java.lang.reflect.InvocationTargetException;

public class AppControllerFactory implements Callback<Class<?>, Object> {
    private final MealSearchService mealSearchService;
    private final CategoryService categoryService;
    private final AreaService areaService;

    public AppControllerFactory(MealSearchService mealSearchService, CategoryService categoryService, AreaService areaService) {
        this.mealSearchService = mealSearchService;
        this.categoryService = categoryService;
        this.areaService = areaService;
    }

    @Override
    public Object call(Class<?> type) {
        if (type == TabsController.class) {
            return new TabsController(mealSearchService, categoryService, areaService);
        } else if (type == SearchTabContentController.class) {
            return new SearchTabContentController(mealSearchService, categoryService, areaService);
        } else if (type == CategoriesTabContentController.class) {
            return new CategoriesTabContentController(categoryService);
        } else if (type == FavouritesTabContentController.class) {
            return new FavouritesTabContentController(mealSearchService);
        } else if (type == MainSceneController.class) {
            return new MainSceneController(mealSearchService, categoryService, areaService);
        } else {
            try {
                return type.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
