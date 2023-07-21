package ua.ithillel.mealapp.model.entity;

public class IngredientEntity {
    private Integer id;
    private String name;
    private String measure;
    private MealEntity mealEntity;

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

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public MealEntity getMealEntity() {
        return mealEntity;
    }

    public void setMealEntity(MealEntity mealEntity) {
        this.mealEntity = mealEntity;
    }
}
