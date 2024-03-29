package com.reply.ro.mvc;


import com.reply.ro.models.Product;
import com.reply.ro.models.Warrant;
import com.reply.ro.service.ProductService;
import com.reply.ro.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/products", consumes = "application/json")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        List<Product> products = productService.getAllProducts();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        Optional<Product> res = productService.getProductById(id);

        if(res.isPresent()){
            return new ResponseEntity<>(res.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No product with id = " + id + " to be found!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product res = productService.createProduct(product);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        Product res = productService.updateProduct(product);

        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @DeleteMapping("/delete/all")
    public void deleteAll(){
        productService.deleteAllProducts();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteProductById(id);
    }

    @PutMapping("/rm-w/{id}")
    public Product removeWarrant(@PathVariable Long id){
        return productService.removeWarrant(id);
    }

    @PutMapping("/add-w/{id}")
    public ResponseEntity<?> removeWarrant(@RequestBody Warrant warrant, @PathVariable Long id){
        Optional<Product > productOptional = productService.addWarrant(warrant, id);

        if(productOptional.isPresent()){
            return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Something went wrong! The warrant is not added!", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/category/{name}")
    public Set<Product > getProductsByCategoryName(@PathVariable String name){
        return productService.getProductsByCategoryName(name);
    }
//    @PutMapping("/add?c={name}&p={id}")
//    public Product addCategory(@PathVariable String name, @PathVariable Long id){
//        return productService.addCategory(name, id);
//    }

}
