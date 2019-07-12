package com.reply.ro.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Product {

    @Id @GeneratedValue @Column(name = "product_id")
    private Long id;

    @Column
    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    @JsonBackReference
    private Brand brand;


    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Warrant war;


    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE}
    )
    @JoinTable(
            name = "category_post",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category > categories = new HashSet<>();

    public void addCategory(Category c){
        this.categories.add(c);
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

    public Warrant getWar() {
        return war;
    }

    public void setWar(Warrant war) {
        this.war = war;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
