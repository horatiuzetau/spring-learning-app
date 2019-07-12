package com.reply.ro.service.impl;

import com.reply.ro.models.Brand;
import com.reply.ro.models.Product;
import com.reply.ro.repository.BrandRepository;
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
}
