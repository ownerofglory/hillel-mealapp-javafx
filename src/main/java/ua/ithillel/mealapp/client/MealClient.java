package ua.ithillel.mealapp.client;

import ua.ithillel.mealapp.model.dto.AreaResponseDto;
import ua.ithillel.mealapp.model.dto.CategoryResponseDto;
import ua.ithillel.mealapp.model.dto.CategoryShortResponseDto;
import ua.ithillel.mealapp.model.dto.MealResponseDto;

public interface MealClient {
    MealResponseDto getMealById(String id);
    MealResponseDto searchMealByName(String name);
    MealResponseDto searchMealByCategory(String categoryName);
    MealResponseDto searchMealByArea(String areaName);

    CategoryResponseDto getCategories();
    CategoryShortResponseDto getCategoriesList();
    AreaResponseDto getAreas();
}
