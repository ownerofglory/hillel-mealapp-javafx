package ua.ithillel.mealapp.model.vm;

import java.util.Objects;

public class CategoryShortItemVm {
    private String name;

    public CategoryShortItemVm() {
    }

    public CategoryShortItemVm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryShortItemVm that = (CategoryShortItemVm) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
