package com.reply.ro.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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


    @ManyToOne
    @JoinColumn(name = "seller")
    private User seller;







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

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
