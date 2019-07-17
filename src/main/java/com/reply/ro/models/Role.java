package com.reply.ro.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Role {


    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;


    @Column(unique = true)
    private String name;


    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User > users;




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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
