package com.reply.ro.repository;

import com.reply.ro.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category > findByName(String name);
    Set<Category > findByNameIn(Collection<String > names);
//    Set<Category > findAllByn(List<String > names);
}
