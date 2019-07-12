package com.reply.ro.service;

import com.reply.ro.models.Brand;
import com.reply.ro.models.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BrandService {

//    ADD
    Brand createBrand(Brand brand);

//    UPDATE
    Brand updateBrand(Brand brand);

//    GET ALL
    List<Brand > getAllBrands();

//    GET ONE
    Optional<Brand > getBrandByName(String name);

//    DELETE ONE
    void deleteBrandByName(String name);

//    ADD PRODUCT
    Brand addProduct(Product product, String brandName);

//    REMOVE PRODUCT FROM BRANd
    Brand removeProduct(Long productId, String brandName);

//    ADD CITY TO BRAND
    Brand addCity(String brandName, String cityName);

//    REMOVE CITY FROM BRAND
    Brand removeCity(String brandName, String cityName);

//    ADD EXISTING PRODUCT TO BRAND
    Brand addExistingProduct(String brandName, Long productId);


}

