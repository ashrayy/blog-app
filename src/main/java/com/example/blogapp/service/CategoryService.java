package com.example.blogapp.service;

import com.example.blogapp.payload.CategoryDTO;

import java.util.List;

public interface CategoryService {

    public CategoryDTO createCategory(CategoryDTO categoryDTO);

    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer id);

    void deleteCategory(Integer id);

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Integer id);


}
