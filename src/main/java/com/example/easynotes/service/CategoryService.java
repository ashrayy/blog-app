package com.example.easynotes.service;

import com.example.easynotes.payload.CategoryDTO;

import java.util.List;

public interface CategoryService {

    public CategoryDTO createCategory(CategoryDTO categoryDTO);

    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer id);

    void deleteCategory(Integer id);

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Integer id);


}
