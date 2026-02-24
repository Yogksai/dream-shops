package com.code.dream_shops.controller;


import com.code.dream_shops.exceptions.AlreadyExistsException;
import com.code.dream_shops.exceptions.ResourceNotFoundException;
import com.code.dream_shops.model.Category;
import com.code.dream_shops.response.ApiResponse;
import com.code.dream_shops.service.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/categories")
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories(){
        try{
            List<Category> categoryList = categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("got all categories success", categoryList));
        } catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: ", INTERNAL_SERVER_ERROR));
        }
    }
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category categoryName){
        try{
            Category category = categoryService.addCategory(categoryName);
            return ResponseEntity.ok(new ApiResponse("category created success", category));
        } catch(AlreadyExistsException e){
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/category/{name}/category")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name){
        try{
            Category theCategory = categoryService.getCategoryByName(name);
            return ResponseEntity.ok(new ApiResponse("got category success", theCategory));
        } catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/category/{id}/category")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id){
        try{
            Category theCategory = categoryService.getCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("got category success", theCategory));
        } catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @PutMapping("/category/{id}/update")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody Category categoryName){
        try{
                Category updatedCategory = categoryService.updateCategory(categoryName, id);
                return ResponseEntity.ok(new ApiResponse("update success", updatedCategory));
        } catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @DeleteMapping("/category/{id}/delete")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id){
        try{
            categoryService.deleteCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("delete success", null));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
