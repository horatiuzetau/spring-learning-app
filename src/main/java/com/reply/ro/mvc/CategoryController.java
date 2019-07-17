package com.reply.ro.mvc;

import com.reply.ro.models.Category;
import com.reply.ro.models.Product;
import com.reply.ro.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.util.resources.cldr.teo.CalendarData_teo_KE;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
public class CategoryController {

    @Autowired private CategoryServiceImpl categoryService;

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories(){
        List<Category > res = categoryService.getAllCategories();

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/categories/{name}")
    public ResponseEntity<?> getCategoryByName(@PathVariable String name){
        Optional<Category > categoryOptional = categoryService.getCategoryByName(name);

        if(categoryOptional.isPresent()){
            return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No category with the name " + name + " found!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/categories-by-names")
    public Set<Category > getCategoriesByNames(@RequestBody Map<String,List<String >> names){
        return categoryService.getByNames(names.get("names"));
    }

//    DEBUGGING

    @PostMapping("/manage/categories/add")
    public ResponseEntity<Category > createCategory(@RequestBody Category category){
        Category res = categoryService.createCategory(category);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/manage/categories/update")
    public ResponseEntity<? > updateCategory(@RequestBody Category category){

        Category res = categoryService.updateCategory(category);
        if(res == null)
            return new ResponseEntity<>("No category with that name...", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/manage/categories/drop/{name}")
    public void deleteAllCategories(@PathVariable String name){
        categoryService.deleteCategory(name);
    }

}
