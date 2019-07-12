package com.reply.ro.service;


import com.reply.ro.models.Product;
import com.reply.ro.models.Warrant;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductService {

//    ADD
    Product createProduct(Product product);

//    UPDATE
    Product updateProduct(Product product);

//    GET ONE
    Optional<Product > getProductById(Long id);

//    GET ALL
    List<Product> getAllProducts();

//    DELETE ONE
    void deleteProductById(Long id);

//    DELETE ALL
    void deleteAllProducts();

//    REMOVE WARRANT
    Product removeWarrant(Long id);

//    ADD WARRANT
    Optional<Product > addWarrant(Warrant warrant, Long id);

//    ADD CATEGORY
    Product addCategory(String name, Long id);

//    GET BY CATEGORY NAME
    Set<Product > getProductsByCategoryName(String name);

//    GET BY BRAND NAME
    Set<Product > getProductsByBrandName(String name);

//    DELETE ALL FROM CATEGORY
    void deleteAllByCategoryName(String name);

//    REMOVE CATEGORY BY NAME
    Product removeCategoryByName(Long id, String name);

}
