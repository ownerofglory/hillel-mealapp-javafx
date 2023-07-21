package ua.ithillel.mealapp.dao;

import ua.ithillel.mealapp.exception.MealAppException;
import ua.ithillel.mealapp.model.entity.IngredientEntity;
import ua.ithillel.mealapp.model.entity.MealEntity;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FavouriteMealJdbcDao implements FavouriteMealDao {
    private final Connection connection;

    public FavouriteMealJdbcDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<MealEntity> findAll(boolean asc) throws MealAppException {
        String sql = String.format( "SELECT * FROM meal " +
                "ORDER BY added_timestamp %s ", asc ? "ASC" : "DESC");
        try (PreparedStatement pst = connection.prepareStatement(sql)) {

            ResultSet resultSet = pst.executeQuery();

            List<MealEntity> favouriteMeals = new ArrayList<>();
            while (resultSet.next()) {
                MealEntity mealEntity = new MealEntity();
                mealEntity.setId(resultSet.getInt("id"));
                mealEntity.setName(resultSet.getString("name"));
                mealEntity.setRecipe(resultSet.getString("recipe"));
                mealEntity.setTheMealDbId(resultSet.getString("themealdb_id"));
                mealEntity.setImageUrl(resultSet.getString("image_url"));
                favouriteMeals.add(mealEntity);
            }
            return favouriteMeals;
        } catch (SQLException e) {
            throw new MealAppException("Unable to find all meals: ", e);
        }
    }

    @Override
    public MealEntity findById(Integer id) throws MealAppException {
        String sql = "SELECT * FROM meal AS m " +
                "JOIN ingredient AS i " +
                "ON m.id = i.meal_id " +
                "WHERE m.id = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, id);

            ResultSet resultSet = pst.executeQuery();

            MealEntity favouriteMeal = null;
            List<IngredientEntity> ingredients = new ArrayList<>();


            while (resultSet.next()) {
                if (favouriteMeal == null) {
                    favouriteMeal = new MealEntity();
                    favouriteMeal.setId(resultSet.getInt(1));
                    favouriteMeal.setName(resultSet.getString(2));
                    favouriteMeal.setRecipe(resultSet.getString(4));
                    favouriteMeal.setTheMealDbId(resultSet.getString(6));
                    favouriteMeal.setImageUrl(resultSet.getString(3));
                    favouriteMeal.setIngredients(ingredients);
                }

                IngredientEntity ingredient = new IngredientEntity();
                ingredient.setName(resultSet.getString(9));
                ingredient.setMeasure(resultSet.getString(10));
                ingredient.setMealEntity(favouriteMeal);

                ingredients.add(ingredient);
            }
            return favouriteMeal;
        } catch (SQLException e) {
            throw new MealAppException("Unable to find meal by id: " + id, e);
        }
    }

    @Override
    public MealEntity findByMealId(String mealId) throws MealAppException {
        String sql = "SELECT m.*, i.* FROM meal AS m " +
                "JOIN ingredient AS i " +
                "ON m.id = i.meal_id " +
                "WHERE m.themealdb_id = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, mealId);

            ResultSet resultSet = pst.executeQuery();

            // TODO: implement
            MealEntity favouriteMeal = null;
            List<IngredientEntity> ingredients = new ArrayList<>();


            while (resultSet.next()) {
                if (favouriteMeal == null) {
                    favouriteMeal = new MealEntity();
                    favouriteMeal.setId(resultSet.getInt(1));
                    favouriteMeal.setName(resultSet.getString(2));
                    favouriteMeal.setRecipe(resultSet.getString(4));
                    favouriteMeal.setTheMealDbId(resultSet.getString(6));
                    favouriteMeal.setImageUrl(resultSet.getString(3));
                    favouriteMeal.setIngredients(ingredients);
                }

                IngredientEntity ingredient = new IngredientEntity();
                ingredient.setName(resultSet.getString(9));
                ingredient.setMeasure(resultSet.getString(10));
                ingredient.setMealEntity(favouriteMeal);

                ingredients.add(ingredient);
            }
            return favouriteMeal;
        } catch (SQLException e) {
            throw new MealAppException("Unable to find meal by id: " + mealId, e);
        }
    }

    @Override
    public void save(MealEntity mealEntity) throws MealAppException {
        String sqlMeal = "INSERT INTO meal " +
                "(name, image_url, recipe, video_url, themealdb_id, added_timestamp) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        String sqlIngredient = "INSERT INTO ingredient " +
                "(name, measure, meal_id) " +
                "VALUES ";

        try (PreparedStatement pst = connection.prepareStatement(sqlMeal, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, mealEntity.getName());
            pst.setString(2, mealEntity.getImageUrl());
            pst.setString(3, mealEntity.getRecipe());
            pst.setString(4, mealEntity.getVideoUrl());
            pst.setString(5, mealEntity.getTheMealDbId());
            pst.setString(6, String.valueOf(Timestamp.valueOf(LocalDateTime.now()).getTime()));

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected == 0) {
                throw new MealAppException("Unable to insert meal");
            }

            ResultSet generatedKeys = pst.getGeneratedKeys();
            generatedKeys.next();
            int insertedMealId = generatedKeys.getInt(1);

            String sqlIngredientsValues = mealEntity.getIngredients()
                    .stream()
                    .map(ing -> "(?, ?, ?)")
                    .collect(Collectors.joining(", "));

            try (PreparedStatement innerPst = connection.prepareStatement(sqlIngredient + sqlIngredientsValues)) {
                int index = 1;
                for (IngredientEntity ingredient : mealEntity.getIngredients()) {
                    innerPst.setString(index++, ingredient.getName());
                    innerPst.setString(index++, ingredient.getMeasure());
                    innerPst.setInt(index++, insertedMealId);
                }

                 innerPst.executeUpdate();

                connection.commit();
            }

        } catch (SQLException e) {
            throw new MealAppException("Unable to insert meal: " + mealEntity, e);
        }

    }

    @Override
    public void remove(Integer id) throws MealAppException {
        String sql = "DELETE FROM meal " +
                "WHERE id = ?";

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, id);

            pst.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            throw new MealAppException("Unable to find all meals: ", e);
        }
    }
}
