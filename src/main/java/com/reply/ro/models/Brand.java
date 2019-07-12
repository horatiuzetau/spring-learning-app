package com.reply.ro.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue
    @Column(name = "brand_id")
    private Long id;

    @NaturalId
    private String name;


    @OneToMany(mappedBy = "brand", orphanRemoval = true, cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<Product > products = new ArrayList<>();


    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProductById(Long id){
        products.removeIf(p -> p.getId().equals(id));
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}