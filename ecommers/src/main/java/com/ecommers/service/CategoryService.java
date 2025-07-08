package com.ecommers.service;


import com.ecommers.domain.CategoryDomain;
import com.ecommers.model.Category;
import com.ecommers.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public String saveOrUpdateCategory(CategoryDomain categoryDomain) {

        Category category = categoryRepository.findByCategoryName(categoryDomain.getCategoryName());

        if (category == null) {
            category = new Category();
            category.setCategoryName(categoryDomain.getCategoryName());
            category.setDescription(categoryDomain.getDescription());
            category.setIsMainCategory(categoryDomain.getIsMainCategory());
            category.setCreatedAt(LocalDateTime.now());
            if (categoryDomain.getParentCategoryName() != null && !categoryDomain.getParentCategoryName().isBlank()) {
                Category parentCategory = categoryRepository.findByCategoryName(categoryDomain.getParentCategoryName());
                category.setCategory(parentCategory);
            }

        }

        category.setCategoryName(categoryDomain.getCategoryName());
        category.setDescription(categoryDomain.getDescription());
        category.setIsMainCategory(categoryDomain.getIsMainCategory());

        if (categoryDomain.getParentCategoryName() != null && !categoryDomain.getParentCategoryName().isBlank()) {
            Category parentCategory = categoryRepository.findByCategoryName(categoryDomain.getParentCategoryName());
            category.setCategory(parentCategory);
        }

        categoryRepository.save(category);

        return "Category saved or updated successfully";
    }


    public List<CategoryDomain> getAllCategoryRecords() {

        List<Category> all = categoryRepository.findAll();
        return  all.stream().map(category -> {
            CategoryDomain domain=new CategoryDomain();
            BeanUtils.copyProperties(category,domain);
            return  domain;
        }).collect(Collectors.toList());

    }

    public String deleteCategoryById(Integer categoryId) {

        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if(categoryOptional.isPresent()){
            categoryRepository.deleteById(categoryId);
            return "category deleted with :" + categoryId;
        }
        return  "category not found with : " + categoryId;
    }

    public List<String> getAllCategoryNames() {
        return categoryRepository.findAllCategoryName();
    }
}
