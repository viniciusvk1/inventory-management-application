package com.github.viniciusvk1.repository;

import com.github.viniciusvk1.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByNameContainingIgnoreCase(@Param("name") String name);

}