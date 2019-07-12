package com.reply.ro.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue
    @Column(name = "city_id")
    private Long id;

    @NaturalId
    private String name;

    @ManyToMany(mappedBy = "cities")
    @JsonIgnore
    private Set<Brand > brands = new HashSet<>();

    public void addBrand(Brand brand){
        brands.add(brand);
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

    public Set<Brand> getBrands() {
        return brands;
    }

    public void setBrands(Set<Brand> brands) {
        this.brands = brands;
    }
}
