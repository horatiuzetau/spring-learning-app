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
public class BrandController {

    @Autowired private BrandServiceImpl brandService;

    @GetMapping("/brands")
    public ResponseEntity<?> getAllBrands(){
        List<Brand> res = brandService.getAllBrands();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/brands/{name}")
    public ResponseEntity<?> getBrandByName(@PathVariable String name){
        Optional<Brand > brandOptional = brandService.getBrandByName(name);

        if(brandOptional.isPresent()){
            return new ResponseEntity<>(brandOptional.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No brand " + name + " to be found!", HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/brands")
    public ResponseEntity<?> updateBrand(@RequestBody Brand brand){
        Brand res = brandService.updateBrand(brand);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @PostMapping("/brands")
    public ResponseEntity<?> createBrand(@RequestBody Brand brand){
        Brand res = brandService.createBrand(brand);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/brands/drop/{name}")
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

    @PostMapping("/add-city/{cityName}/{brandName}")
    public ResponseEntity<?> addCityToBrand(@PathVariable String cityName, @PathVariable String brandName){
        return new ResponseEntity<>(brandService.addCity(brandName, cityName), HttpStatus.OK);
    }

    @PostMapping("/remove-city/{cityName}/{brandName}")
    public ResponseEntity<?> removeCityFromBrand(@PathVariable String cityName, @PathVariable String brandName){
        return new ResponseEntity<>(brandService.removeCity(brandName, cityName), HttpStatus.OK);
    }

    @PostMapping("/{brandName}/add-existing-product/{productId}")
    public ResponseEntity<? > addExistingProduct(@PathVariable String brandName, @PathVariable Long productId){
        return new ResponseEntity<>(brandService.addExistingProduct(brandName, productId), HttpStatus.OK);
    }

}
