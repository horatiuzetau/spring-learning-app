package com.reply.ro.service;

import com.reply.ro.models.Category;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryService {

//    ADD
    Category createCategory(Category category);
//    UPDATE
    Category updateCategory(Category category);

//    GET ALL
    List<Category > getAllCategories();

//    GET ONE
    Optional<Category > getCategoryByName(String name);

//    DELETE
    void deleteCategory(String name);

//    Get by names
    Set<Category > getByNames(List<String > names);

}
