package com.example.easynotes.service.impl;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Category;
import com.example.easynotes.payload.CategoryDTO;
import com.example.easynotes.repository.CategoryRepo;
import com.example.easynotes.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = this.categoryRepo.save(this.modelMapper.map(categoryDTO, Category.class));
        return this.modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer id) {
        Category category = this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","id",id));
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());
        this.categoryRepo.save(category);
        return this.modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Integer id) {
     Category cat = this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","id",id));
     this.categoryRepo.deleteById(id);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> cat = this.categoryRepo.findAll();
        List<CategoryDTO> catDTO = new ArrayList<>();
        cat.forEach(val->catDTO.add(this.modelMapper.map(val,CategoryDTO.class)));
        return catDTO;
    }

    @Override
    public CategoryDTO getCategoryById(Integer id) {
        Category cat = this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","id",id));
        return this.modelMapper.map(cat,CategoryDTO.class);
    }
}
