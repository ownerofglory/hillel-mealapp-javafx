package ua.ithillel.mealapp.service;

import ua.ithillel.mealapp.exception.MealAppException;
import ua.ithillel.mealapp.model.vm.AreaItemVm;
import ua.ithillel.mealapp.model.vm.CategoryShortItemVm;
import ua.ithillel.mealapp.model.vm.MealItemVm;

import java.util.List;

public interface MealSearchService {
    List<MealItemVm> searchMeals(String name);
    List<MealItemVm> getMealsByCategory(CategoryShortItemVm category);
    MealItemVm getMealById(String id);
    List<MealItemVm> getMealsByArea(AreaItemVm category);
    void toggleFavouriteMeal(MealItemVm mealItemVm) throws MealAppException;
    List<MealItemVm> getFavouriteMeals(boolean order) throws MealAppException;
    MealItemVm getFavouriteMeal(Integer id) throws MealAppException;
    MealItemVm getFavouriteMealByMealId(String mealId) throws MealAppException;
}
