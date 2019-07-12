package com.reply.ro.service.impl;

import com.reply.ro.models.Category;
import com.reply.ro.models.Product;
import com.reply.ro.models.Warrant;
import com.reply.ro.repository.CategoryRepository;
import com.reply.ro.repository.ProductRepository;
import com.reply.ro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

//    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!! UITE AICI -> intrebarea 3!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Override
    public Product createProduct(Product product) {
        if(product.getId() != null){
            System.out.println("No man, nu poti sa adaugi un produs care are deja id.");
//            AICI AM AVEA UN THROW EXCEPTION
        }

        Set<Category> categories = new HashSet<>();
        List<String > names = new ArrayList<>();

//        Punem numele intr-o lista
        for(Category c : product.getCategories()){
            names.add(c.getName());
        }

        categories = categoryRepository.findByNameIn(names);


        if(categories.size() > 0)
            product.setCategories(categories);



        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        if(product.getId() != null){
            System.out.println("No man, nu poti sa modifici produs daca nu specifici care(id)!");
//            AICI AM AVEA UN THROW EXCEPTION
        }

//        PARTEA DE UPDATAT WARRANT
        Optional<Warrant > warrantOptional = Optional.ofNullable(product.getWar());

        if(warrantOptional.isPresent()){

            Warrant warrant = warrantOptional.get();
            warrant.setId(product.getId());
            warrant.setProduct(product);

            product.setWar(warrant);
        }

//        PARTEA DE UPDATAT CATEGORII
        Set<Category> categories = new HashSet<>();
        List<String > names = new ArrayList<>();

        for(Category c : product.getCategories()){
            names.add(c.getName());
        }

        categories = categoryRepository.findByNameIn(names);

        if(categories.size() > 0)
            product.setCategories(categories);


        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    @Override
    public Product removeWarrant(Long id){
        Optional<Product > productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            Product productObject = productOptional.get();

//            Warrant warrantToDelete = productObject.getWar();

            productObject.setWar(null);

            productRepository.save(productObject);

            return productObject;
        }

        return null;
    }

    @Override
    public Optional<Product > addWarrant(Warrant warrant, Long id){
        Optional<Product > productOptional = productRepository.findById(id);

        if(productOptional.isPresent()){
            Product  productObject = productOptional.get();
            warrant.setProduct(productObject);
            productObject.setWar(warrant);

            productRepository.save(productObject);
            return Optional.of(productObject);
        }else{
            System.out.println("The product with id = " + id + " does not exist!");

            return productOptional;
        }

    }

    @Override
    public Set<Product > getProductsByCategoryName(String name){
        Category cat = categoryRepository.findByName(name).get();

        return productRepository.findAllByCategories(cat);
    }

    @Override
    public Product addCategory(String name, Long id){
        Category category = categoryRepository.findByName(name).get();
        Product product = productRepository.findById(id).get();

        category.addProduct(product);
        product.addCategory(category);

        return this.updateProduct(product);
    }

    @Override
    public void deleteAllByCategoryName(String name) {
        Optional<Category > categoryOptional = categoryRepository.findByName(name);

        categoryOptional.ifPresent(category -> productRepository.deleteAllByCategoriesIs(category));
    }

    @Override
    public Product removeCategoryByName(Long id, String name) {
        Product product = productRepository.findById(id).get();
        product.setCategories(
                product
                        .getCategories()
                        .stream()
                        .filter(a -> !a.getName().equals(name))
                        .collect(Collectors.toSet())
        );

        return productRepository.save(product);
    }
}
