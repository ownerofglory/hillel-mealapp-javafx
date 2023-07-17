package ua.ithillel.mealapp.model.vm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MealItemVm {
    private String name;
    private String imageUrl;
    private String recipe;
    private String videoUrl;
    private String id;

    private List<IngredientVm> ingredientVms = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public List<IngredientVm> getIngredientVms() {
        return ingredientVms;
    }

    public void setIngredientVms(List<IngredientVm> ingredientVms) {
        this.ingredientVms = ingredientVms;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealItemVm that = (MealItemVm) o;
        return Objects.equals(name, that.name) && Objects.equals(imageUrl, that.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, imageUrl);
    }
}
