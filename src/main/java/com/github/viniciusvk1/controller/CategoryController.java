package com.github.viniciusvk1.controller;

import com.github.viniciusvk1.model.Category;
import com.github.viniciusvk1.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    // Tested status: Working
    @GetMapping("/find/{name}")
    public ResponseEntity<List<Category>> findAllByName(@Valid @PathVariable String name) {
        return ResponseEntity.ok(categoryRepository.findAllByNameContainingIgnoreCase(name));
    }

    // findById method -> able to search for a category by id that already exists in the system.
    // mapped by: /category/{id} | <- in the url {id} you should pass the id of the category you want to find
    // Tested status: Working
    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        return categoryRepository.findById(id).map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.notFound().build());
    }

    // addCategory method -> Allows the user to add a new category to the database
    // To add using the method you must use the post method with the following json:
    /*
            {
    "name": "name",
    "description": "description"
}
     */
    // mapped by: /category
    // Tested status: Working
    @PostMapping
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryRepository.save(category));
    }

    // editCategory method -> Allows the user to edit an existing category by the registered id
    // mapped by: /category
    // Tested Status: Working
    @PutMapping
    public ResponseEntity<Category> editCategory(@Valid @RequestBody Category category) {
        return categoryRepository.findById(category.getId())
                .map(response -> ResponseEntity.ok().body(categoryRepository.save(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    // delCategoryById method -> Allows the user to delete a category by the generated id
    // mapped by: /category/del/{id} -> in the {id} url an id of an existing category must be inserted to be deleted
    // Tested Status: Working
    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> delCategoryById(@PathVariable Long id) {

        return categoryRepository.findById(id)
                .map(response -> {
                    categoryRepository.deleteById(id);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}