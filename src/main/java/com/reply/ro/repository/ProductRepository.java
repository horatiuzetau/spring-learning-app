package com.reply.ro.repository;

import com.reply.ro.models.Brand;
import com.reply.ro.models.Category;
import com.reply.ro.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface
ProductRepository extends JpaRepository<Product, Long> {

    Set<Product > findAllByCategories(Category category);

    Set<Product > findAllByBrand(Brand brand);

    void deleteAllByCategoriesIs(Category category);


}
