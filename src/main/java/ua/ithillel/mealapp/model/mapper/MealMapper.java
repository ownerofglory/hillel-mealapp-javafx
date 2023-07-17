package ua.ithillel.mealapp.model.mapper;

import ua.ithillel.mealapp.model.dto.MealDto;
import ua.ithillel.mealapp.model.vm.IngredientVm;
import ua.ithillel.mealapp.model.vm.MealItemVm;

import java.util.ArrayList;
import java.util.List;

public class MealMapper {
    public MealItemVm mealDtoToMealItemVm(MealDto mealDto) {
        MealItemVm mealItemVm = new MealItemVm();
        mealItemVm.setId(mealDto.getIdMeal());
        mealItemVm.setName(mealDto.getStrMeal());
        mealItemVm.setImageUrl(mealDto.getStrMealThumb().replace("\\", ""));
        mealItemVm.setIngredientVms(buildIngredients(mealDto));
        mealItemVm.setRecipe(mealDto.getStrInstructions());

        return mealItemVm;
    }

    private List<IngredientVm> buildIngredients(MealDto mealDto) {
       List<IngredientVm> ingredients = new ArrayList<>();

        if (mealDto.getStrIngredient1() != null && mealDto.getStrMeasure1() != null) {
            IngredientVm ingredient = new IngredientVm(mealDto.getStrIngredient1(), mealDto.getStrMeasure1());
            ingredients.add(ingredient);
        };

        if (mealDto.getStrIngredient2() != null && mealDto.getStrMeasure2() != null) {
            IngredientVm ingredient = new IngredientVm(mealDto.getStrIngredient2(), mealDto.getStrMeasure2());
            ingredients.add(ingredient);
        };

        if (mealDto.getStrIngredient3() != null && mealDto.getStrMeasure3() != null) {
            IngredientVm ingredient = new IngredientVm(mealDto.getStrIngredient3(), mealDto.getStrMeasure3());
            ingredients.add(ingredient);
        };

        if (mealDto.getStrIngredient4() != null && mealDto.getStrMeasure4() != null) {
            IngredientVm ingredient = new IngredientVm(mealDto.getStrIngredient4(), mealDto.getStrMeasure4());
            ingredients.add(ingredient);
        };

        if (mealDto.getStrIngredient5() != null && mealDto.getStrMeasure5() != null) {
            IngredientVm ingredient = new IngredientVm(mealDto.getStrIngredient5(), mealDto.getStrMeasure5());
            ingredients.add(ingredient);
        };

        if (mealDto.getStrIngredient6() != null && mealDto.getStrMeasure6() != null) {
            IngredientVm ingredient = new IngredientVm(mealDto.getStrIngredient6(), mealDto.getStrMeasure6());
            ingredients.add(ingredient);
        };

        if (mealDto.getStrIngredient7() != null && mealDto.getStrMeasure7() != null) {
            IngredientVm ingredient = new IngredientVm(mealDto.getStrIngredient7(), mealDto.getStrMeasure7());
            ingredients.add(ingredient);
        };

        if (mealDto.getStrIngredient8() != null && mealDto.getStrMeasure8() != null) {
            IngredientVm ingredient = new IngredientVm(mealDto.getStrIngredient8(), mealDto.getStrMeasure8());
            ingredients.add(ingredient);
        };

        if (mealDto.getStrIngredient9() != null && mealDto.getStrMeasure9() != null) {
            IngredientVm ingredient = new IngredientVm(mealDto.getStrIngredient9(), mealDto.getStrMeasure9());
            ingredients.add(ingredient);
        };

        if (mealDto.getStrIngredient10() != null && mealDto.getStrMeasure10() != null) {
            IngredientVm ingredient = new IngredientVm(mealDto.getStrIngredient10(), mealDto.getStrMeasure10());
            ingredients.add(ingredient);
        };

        if (mealDto.getStrIngredient11() != null && mealDto.getStrMeasure11() != null) {
            IngredientVm ingredient = new IngredientVm(mealDto.getStrIngredient11(), mealDto.getStrMeasure11());
            ingredients.add(ingredient);
        };

        if (mealDto.getStrIngredient12() != null && mealDto.getStrMeasure12() != null) {
            IngredientVm ingredient = new IngredientVm(mealDto.getStrIngredient12(), mealDto.getStrMeasure12());
            ingredients.add(ingredient);
        };

        if (mealDto.getStrIngredient13() != null && mealDto.getStrMeasure13() != null) {
            IngredientVm ingredient = new IngredientVm(mealDto.getStrIngredient13(), mealDto.getStrMeasure13());
            ingredients.add(ingredient);
        };

        if (mealDto.getStrIngredient14() != null && mealDto.getStrMeasure14() != null) {
            IngredientVm ingredient = new IngredientVm(mealDto.getStrIngredient14(), mealDto.getStrMeasure14());
            ingredients.add(ingredient);
        };

        if (mealDto.getStrIngredient15() != null && mealDto.getStrMeasure15() != null) {
            IngredientVm ingredient = new IngredientVm(mealDto.getStrIngredient15(), mealDto.getStrMeasure15());
            ingredients.add(ingredient);
        };

        if (mealDto.getStrIngredient16() != null && mealDto.getStrMeasure16() != null) {
            IngredientVm ingredient = new IngredientVm(mealDto.getStrIngredient16(), mealDto.getStrMeasure16());
            ingredients.add(ingredient);
        };

        if (mealDto.getStrIngredient17() != null && mealDto.getStrMeasure17() != null) {
            IngredientVm ingredient = new IngredientVm(mealDto.getStrIngredient17(), mealDto.getStrMeasure17());
            ingredients.add(ingredient);
        };

        if (mealDto.getStrIngredient18() != null && mealDto.getStrMeasure18() != null) {
            IngredientVm ingredient = new IngredientVm(mealDto.getStrIngredient18(), mealDto.getStrMeasure18());
            ingredients.add(ingredient);
        };

        if (mealDto.getStrIngredient19() != null && mealDto.getStrMeasure19() != null) {
            IngredientVm ingredient = new IngredientVm(mealDto.getStrIngredient19(), mealDto.getStrMeasure19());
            ingredients.add(ingredient);
        };

        if (mealDto.getStrIngredient20() != null && mealDto.getStrMeasure20() != null) {
            IngredientVm ingredient = new IngredientVm(mealDto.getStrIngredient20(), mealDto.getStrMeasure20());
            ingredients.add(ingredient);
        };

        ingredients.removeIf(i -> i.getName().isEmpty());

       return ingredients;
    }
}
