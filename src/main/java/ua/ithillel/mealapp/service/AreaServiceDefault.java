package ua.ithillel.mealapp.service;

import ua.ithillel.mealapp.client.MealClient;
import ua.ithillel.mealapp.model.dto.AreaItemDto;
import ua.ithillel.mealapp.model.dto.AreaResponseDto;
import ua.ithillel.mealapp.model.mapper.AreaMapper;
import ua.ithillel.mealapp.model.vm.AreaItemVm;

import java.util.List;
import java.util.stream.Collectors;

public class AreaServiceDefault implements AreaService {
    private final MealClient mealClient;
    private final AreaMapper areaMapper;

    public AreaServiceDefault(MealClient mealClient, AreaMapper areaMapper) {
        this.mealClient = mealClient;
        this.areaMapper = areaMapper;
    }

    @Override
    public List<AreaItemVm> getAreas() {
        AreaResponseDto areas = mealClient.getAreas();
        List<AreaItemDto> areaItemDtos = areas.getMeals();
        return areaItemDtos.stream()
                .map(areaMapper::areaDtToAreaVm)
                .collect(Collectors.toList());
    }
}
