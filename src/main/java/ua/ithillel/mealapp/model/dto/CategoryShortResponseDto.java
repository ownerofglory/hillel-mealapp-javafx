package ua.ithillel.mealapp.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryShortResponseDto {
    @JsonProperty("meals")
    private List<CategoryDto> meals;

    public List<CategoryDto> getMeals() {
        return meals;
    }

    public void setMeals(List<CategoryDto> meals) {
        this.meals = meals;
    }
}
