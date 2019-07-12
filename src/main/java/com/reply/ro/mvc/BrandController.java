package com.reply.ro.mvc;


import com.reply.ro.models.Brand;
import com.reply.ro.models.Product;
import com.reply.ro.service.BrandService;
import com.reply.ro.service.impl.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandServiceImpl brandService;

    @GetMapping
    public ResponseEntity<?> getAllBrands(){
        List<Brand> res = brandService.getAllBrands();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getBrandByName(@PathVariable String name){
        Optional<Brand > brandOptional = brandService.getBrandByName(name);

        if(brandOptional.isPresent()){
            return new ResponseEntity<>(brandOptional.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No brand " + name + " to be found!", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping
    public ResponseEntity<?> updateBrand(@RequestBody Brand brand){
        Brand res = brandService.updateBrand(brand);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<?> createBrand(@RequestBody Brand brand){
        Brand res = brandService.createBrand(brand);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteBrand(@PathVariable String name){
        brandService.deleteBrandByName(name);
        return new ResponseEntity<>("Brand " + name + " deleted!" , HttpStatus.OK);
    }


    @PostMapping("/{brandName}/add-product")
    public ResponseEntity<?> addProduct(@RequestBody Product product, @PathVariable String brandName){

        return new ResponseEntity<>(brandService.addProduct(product, brandName), HttpStatus.OK);
    }

    @PostMapping("/{brandName}/remove-product/{productId}")
    public ResponseEntity<?> removeProduct(@PathVariable Long productId, @PathVariable String brandName){
        return new ResponseEntity<>(brandService.removeProduct(productId, brandName), HttpStatus.OK);
    }

}
