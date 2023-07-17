package ua.ithillel.mealapp.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AreaResponseDto {
    @JsonProperty("meals")
    private List<AreaItemDto> meals;

    public List<AreaItemDto> getMeals() {
        return meals;
    }

    public void setMeals(List<AreaItemDto> meals) {
        this.meals = meals;
    }
}
