package com.timvdg.projectapplicationframworks.services;

import com.timvdg.projectapplicationframworks.Repositories.CategoryRepository;
import com.timvdg.projectapplicationframworks.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }//findAll

    public void updateCategory(Category category){categoryRepository.save(category); }//add or update (tuy vao pri-key)

    public void removeCategoryById(long id){
        categoryRepository.deleteById(id);
    }//delete dua vao pri-key

    public Optional<Category> getCategoryById(long id){
        return categoryRepository.findById(id);
    }
}
