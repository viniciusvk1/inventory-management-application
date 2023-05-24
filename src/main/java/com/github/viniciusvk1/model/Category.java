package com.github.viniciusvk1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 25, message = "The allowed length for the category name is 25 characters.")
    @NotBlank(message = "The name field is required to add a new category.")
    @NotNull
    private String name;

    @Size(max = 50, message = "The allowed length for the category description is 50 characters.")
    @NotBlank(message = "The description field is required to add a new category.")
    @NotNull
    private String description;

}