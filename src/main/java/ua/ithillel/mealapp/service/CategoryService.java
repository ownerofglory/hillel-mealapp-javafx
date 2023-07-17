package ua.ithillel.mealapp.service;

import ua.ithillel.mealapp.model.vm.CategoryItemVm;
import ua.ithillel.mealapp.model.vm.CategoryShortItemVm;

import java.util.List;

public interface CategoryService {
    List<CategoryShortItemVm>  getShortCategories();
    List<CategoryItemVm>  getCategories();
}
