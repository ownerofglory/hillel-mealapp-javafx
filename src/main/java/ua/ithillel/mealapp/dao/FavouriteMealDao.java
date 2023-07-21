package ua.ithillel.mealapp.dao;

import ua.ithillel.mealapp.exception.MealAppException;
import ua.ithillel.mealapp.model.entity.MealEntity;

import java.util.List;

public interface FavouriteMealDao {
    List<MealEntity> findAll(boolean asc) throws MealAppException;
    MealEntity findById(Integer id) throws MealAppException;
    MealEntity findByMealId(String mealId) throws MealAppException;
    void save(MealEntity mealEntity) throws MealAppException;
    void remove(Integer id) throws MealAppException;
}
