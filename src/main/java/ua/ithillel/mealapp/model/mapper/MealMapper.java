package ua.ithillel.mealapp.model.mapper;

import ua.ithillel.mealapp.model.dto.MealDto;
import ua.ithillel.mealapp.model.entity.IngredientEntity;
import ua.ithillel.mealapp.model.entity.MealEntity;
import ua.ithillel.mealapp.model.vm.IngredientVm;
import ua.ithillel.mealapp.model.vm.MealItemVm;

import java.util.List;

public interface MealMapper {
    MealItemVm mealDtoToMealItemVm(MealDto mealDto);
    MealEntity mealItemVmToMealEntity(MealItemVm mealItemVm);
    IngredientEntity ingredientVmToIngredientEntity(IngredientVm ingredientVm);
    MealItemVm mealEntityToMealItemVm(MealEntity mealEntity);
    IngredientVm ingredientEntityToIngredientVm(IngredientEntity ingredientEntity);
}
