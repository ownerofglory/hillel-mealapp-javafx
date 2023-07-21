package ua.ithillel.mealapp.model.mapper;

import ua.ithillel.mealapp.model.dto.CategoryDto;
import ua.ithillel.mealapp.model.vm.CategoryItemVm;
import ua.ithillel.mealapp.model.vm.CategoryShortItemVm;

public class CategoriesMapperDefault implements CategoriesMapper {
    @Override
    public CategoryShortItemVm categoryDtoToCategoryShortVm(CategoryDto categoryDto) {
        CategoryShortItemVm categoryShortItemVm = new CategoryShortItemVm();
        categoryShortItemVm.setName(categoryDto.getStrCategory());
        return categoryShortItemVm;
    }

    @Override
    public CategoryItemVm categoryDtoToCategoryVm(CategoryDto categoryDto) {
        CategoryItemVm categoryItemVm = new CategoryItemVm();
        categoryItemVm.setName(categoryDto.getStrCategory());
        categoryItemVm.setDescription(categoryDto.getStrCategoryDescription());
        categoryItemVm.setImageUrl(categoryDto.getStrCategoryThumb());
        return categoryItemVm;
    }
}
