package ua.ithillel.mealapp.model.mapper;

import ua.ithillel.mealapp.model.dto.CategoryDto;
import ua.ithillel.mealapp.model.vm.CategoryItemVm;
import ua.ithillel.mealapp.model.vm.CategoryShortItemVm;

public interface CategoriesMapper {
    CategoryShortItemVm categoryDtoToCategoryShortVm(CategoryDto categoryDto);

    CategoryItemVm categoryDtoToCategoryVm(CategoryDto categoryDto);
}
