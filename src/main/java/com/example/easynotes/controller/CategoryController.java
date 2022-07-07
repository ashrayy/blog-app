package com.example.easynotes.controller;

import com.example.easynotes.payload.ApiResponse;
import com.example.easynotes.payload.CategoryDTO;
import com.example.easynotes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer id){
        return new ResponseEntity<>(this.categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(this.categoryService.createCategory(categoryDTO),HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        return new ResponseEntity<>(this.categoryService.getAllCategories(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteCategory(@PathVariable Integer id){
        this.categoryService.deleteCategory(id);
        return new ApiResponse("Category with Id "+id+" deleted successfully",String.valueOf(HttpStatus.OK.value()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer id){
        return new ResponseEntity<>(this.categoryService.updateCategory(categoryDTO,id), HttpStatus.OK);
    }
}
