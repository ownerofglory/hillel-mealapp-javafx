package ua.ithillel.mealapp.model.entity;

import java.util.List;
import java.util.Objects;

public class MealEntity {
    private Integer id;
    private String name;
    private String imageUrl;
    private String recipe;
    private String videoUrl;
    private String theMealDbId;
    private List<IngredientEntity> ingredients;

    public List<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getTheMealDbId() {
        return theMealDbId;
    }

    public void setTheMealDbId(String theMealDbId) {
        this.theMealDbId = theMealDbId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealEntity that = (MealEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(imageUrl, that.imageUrl) && Objects.equals(recipe, that.recipe) && Objects.equals(videoUrl, that.videoUrl) && Objects.equals(theMealDbId, that.theMealDbId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, imageUrl, recipe, videoUrl, theMealDbId);
    }
}
