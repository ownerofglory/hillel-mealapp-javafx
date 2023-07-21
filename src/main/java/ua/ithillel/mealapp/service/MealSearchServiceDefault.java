package ua.ithillel.mealapp.service;

import ua.ithillel.mealapp.client.MealClient;
import ua.ithillel.mealapp.dao.FavouriteMealDao;
import ua.ithillel.mealapp.exception.MealAppException;
import ua.ithillel.mealapp.model.dto.MealDto;
import ua.ithillel.mealapp.model.dto.MealResponseDto;
import ua.ithillel.mealapp.model.entity.MealEntity;
import ua.ithillel.mealapp.model.mapper.MealMapper;
import ua.ithillel.mealapp.model.vm.AreaItemVm;
import ua.ithillel.mealapp.model.vm.CategoryShortItemVm;
import ua.ithillel.mealapp.model.vm.MealItemVm;

import java.util.List;
import java.util.stream.Collectors;

public class MealSearchServiceDefault implements MealSearchService {
    private final MealClient client;
    private final MealMapper mealMapper;
    private final FavouriteMealDao favouriteMealDao;

    public MealSearchServiceDefault(MealClient client, MealMapper mealMapper, FavouriteMealDao favouriteMealDao) {
        this.client = client;
        this.mealMapper = mealMapper;
        this.favouriteMealDao = favouriteMealDao;
    }

    @Override
    public List<MealItemVm> searchMeals(String name) {
        MealResponseDto mealResponseDto = client.searchMealByName(name);
        List<MealDto> meals = mealResponseDto.getMeals();
        return meals.stream()
                .map(mealMapper::mealDtoToMealItemVm)
                .collect(Collectors.toList());
    }

    @Override
    public List<MealItemVm> getMealsByCategory(CategoryShortItemVm category) {
        MealResponseDto mealResponseDto = client.searchMealByCategory(category.getName());
        List<MealDto> meals = mealResponseDto.getMeals();
        return meals.stream()
                .map(mealMapper::mealDtoToMealItemVm)
                .collect(Collectors.toList());
    }

    @Override
    public MealItemVm getMealById(String id) {
        MealResponseDto mealById = client.getMealById(id);
        List<MealDto> meals = mealById.getMeals();
        if (meals != null && meals.size() == 1) {
            MealDto mealDto = meals.get(0);
            return mealMapper.mealDtoToMealItemVm(mealDto);
        }
        return null;
    }

    @Override
    public List<MealItemVm> getMealsByArea(AreaItemVm area) {
        MealResponseDto mealResponseDto = client.searchMealByArea(area.getArea());
        List<MealDto> meals = mealResponseDto.getMeals();
        return meals.stream()
                .map(mealMapper::mealDtoToMealItemVm)
                .collect(Collectors.toList());
    }

    @Override
    public void toggleFavouriteMeal(MealItemVm mealItemVm) throws MealAppException {
        MealEntity byMealId = favouriteMealDao.findByMealId(mealItemVm.getId());
        if (byMealId != null) {
            favouriteMealDao.remove(byMealId.getId());
            return;
        }

        MealEntity mealEntity = mealMapper.mealItemVmToMealEntity(mealItemVm);

        favouriteMealDao.save(mealEntity);
    }

    @Override
    public List<MealItemVm> getFavouriteMeals(boolean order) throws MealAppException {
        List<MealEntity> allMealEntities = favouriteMealDao.findAll(order);
        return allMealEntities.stream().map(mealMapper::mealEntityToMealItemVm).collect(Collectors.toList());
    }

    @Override
    public MealItemVm getFavouriteMeal(Integer id) throws MealAppException {
        MealEntity byMealId = favouriteMealDao.findById(id);
        return mealMapper.mealEntityToMealItemVm(byMealId);
    }

    @Override
    public MealItemVm getFavouriteMealByMealId(String mealId) throws MealAppException {
        MealEntity byMealId = favouriteMealDao.findByMealId(mealId);
        return mealMapper.mealEntityToMealItemVm(byMealId);
    }
}
