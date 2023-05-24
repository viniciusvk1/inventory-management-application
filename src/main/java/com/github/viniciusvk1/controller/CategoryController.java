package com.github.viniciusvk1.controller;

import com.github.viniciusvk1.model.Category;
import com.github.viniciusvk1.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // FindAll method -> able to search all categories already registered in the system.
    // mapped by: /category
    // Tested status: Working
    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    // findAllByName method -> able to search for a category by name that already exists in the system.
    // mapped by: /category/{name} | <- in the url {name} you should pass the name of the category you want to find
    // Tested status: Not Working
    @GetMapping("/{name}")
    public ResponseEntity<List<Category>> findAllByName(@Valid @PathVariable String name) {
        return ResponseEntity.ok(categoryRepository.findAllByNameContainingIgnoreCare(name));
    }

    // findById method -> able to search for a category by id that already exists in the system.
    // mapped by: /category/{id} | <- in the url {id} you should pass the id of the category you want to find
    // Tested status: Working
    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        return categoryRepository.findById(id).map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.notFound().build());
    }

}