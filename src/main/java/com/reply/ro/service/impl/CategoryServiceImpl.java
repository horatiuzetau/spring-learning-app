package com.reply.ro.service.impl;


import com.reply.ro.models.Category;
import com.reply.ro.repository.CategoryRepository;
import com.reply.ro.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public void deleteCategory(String name) {
        Optional<Category > categoryOptional = categoryRepository.findByName(name);

        if(categoryOptional.isPresent()){
            categoryRepository.delete(categoryOptional.get());
        }else{
            System.out.println("No category to delete! " + name + " does not exist!");
        }

    }

    @Override
    public Set<Category> getByNames(List<String> names) {
        return categoryRepository.findByNameIn(names);
    }
}
