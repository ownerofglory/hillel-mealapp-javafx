package ua.ithillel.mealapp.model.mapper;

import ua.ithillel.mealapp.model.dto.AreaItemDto;
import ua.ithillel.mealapp.model.vm.AreaItemVm;

public interface AreaMapper {
    AreaItemVm areaDtToAreaVm(AreaItemDto areaItemDto);
}
