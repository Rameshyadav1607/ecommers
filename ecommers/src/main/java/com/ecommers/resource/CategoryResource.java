package com.ecommers.resource;


import com.ecommers.domain.CategoryDomain;
import com.ecommers.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryResource {
  @Autowired
  private CategoryService categoryService;

  @PostMapping("/save")
  public String saveOrUpdateCategory(@Validated @RequestBody CategoryDomain categoryDomain){
      return categoryService.saveOrUpdateCategory(categoryDomain);
  }

  @GetMapping("/all")
  public List<CategoryDomain>  getAllCategoryRecords(){
    return categoryService.getAllCategoryRecords();
  }
  @DeleteMapping("/delete/{categoryId}")
  public String deleteCategoryById(@PathVariable Integer categoryId){
    return categoryService.deleteCategoryById(categoryId);
  }
  @GetMapping("/category/names")
  public List<String> getAllCategoryNames(){
    return categoryService.getAllCategoryNames();

  }
}
