package com.reply.ro.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @NaturalId
    private String name;

    @Column
    private Integer discount;

    @ManyToMany(
            mappedBy = "categories",
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<Product > products = new HashSet<>();




    public void addProduct(Product product){
        products.add(product);
    }





//    GET AND SET WITH INTELECT

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

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
