package ua.ithillel.mealapp.model.mapper;

import ua.ithillel.mealapp.model.dto.AreaItemDto;
import ua.ithillel.mealapp.model.vm.AreaItemVm;

public class AreaMapper {
    public AreaItemVm areaDtToAreaVm(AreaItemDto areaItemDto) {
        AreaItemVm areaItemVm = new AreaItemVm();
        areaItemVm.setArea(areaItemDto.getStrArea());
        return areaItemVm;
    }
}
