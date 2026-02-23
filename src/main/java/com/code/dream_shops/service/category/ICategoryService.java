package com.code.dream_shops.service.category;

import com.code.dream_shops.model.Category;

import java.util.List;

public interface ICategoryService {
    //Search
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    //add
    Category addCategory(Category category);
    //update
    Category updateCategory(Category category, Long id);
    //delete
    void deleteCategoryById(Long id);
}
