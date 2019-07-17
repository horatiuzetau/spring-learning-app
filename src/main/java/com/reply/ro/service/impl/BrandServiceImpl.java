package com.reply.ro.service.impl;

import com.reply.ro.models.Brand;
import com.reply.ro.models.City;
import com.reply.ro.models.Product;
import com.reply.ro.repository.BrandRepository;
import com.reply.ro.repository.CityRepository;
import com.reply.ro.repository.ProductRepository;
import com.reply.ro.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand updateBrand(Brand brand) {
        Optional<Brand > brandOptional = brandRepository.findBrandByName(brand.getName());

        if(brandOptional.isPresent()){
            brand.setId(brandOptional.get().getId());
            return brandRepository.save(brand);
        }else{
            return null;
        }
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Optional<Brand> getBrandByName(String name) {
        return brandRepository.findBrandByName(name);
    }

    @Override
    public void deleteBrandByName(String name) {
        brandRepository.deleteBrandByName(name);
    }

    @Override
    public Brand addProduct(Product product, String brandName) {

        Optional<Brand > brandOptional = brandRepository.findBrandByName(brandName);

        if(brandOptional.isPresent()){
            Brand brandObject = brandOptional.get();

            brandObject.addProduct(product);
            product.setBrand(brandObject);
            return this.updateBrand(brandObject);
        }else{
            System.out.println("Nu avem asa ceva, asa brand? NUNU!");
            return null;
        }
    }

    @Override
    public Brand removeProduct(Long productId, String brandName) {
        Brand brand = brandRepository.findBrandByName(brandName).get();

        brand.removeProductById(productId);

        return brandRepository.save(brand);
    }

    @Override
    public Brand addCity(String brandName, String cityName) {
        Brand brand = brandRepository.findBrandByName(brandName).get();

        City city = cityRepository.findCityByName(cityName).get();

        city.addBrand(brand);
        brand.addCity(city);

        return brandRepository.save(brand);
    }

    @Override
    public Brand removeCity(String brandName, String cityName) {
        Brand brand = brandRepository.findBrandByName(brandName).get();

        brand.removeCityByName(cityName);

        return brandRepository.save(brand);
    }

    @Override
    public Brand addExistingProduct(String brandName, Long productId) {
        Brand brand = brandRepository.findBrandByName(brandName).get();

        Product product = productRepository.findById(productId).get();
//        daca are deja brand, atunci pa si pusi, exceptie
        product.setBrand(brand);
        brand.addProduct(product);


        return brandRepository.save(brand);
    }
}
