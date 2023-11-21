package com.luxury.model;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ProductForm {
    private Long id;
    @NotNull
    @NotEmpty(message = "This field cannot be left blank")
    @Size(min = 1, max = 20, message = "Please enter 1 to 20 char")
    private String name;
    @NotNull
    private int quantity;
    private MultipartFile image;

    @Pattern(regexp = "^.{10,}$", message = "Please enter more than 10 characters")
    private String description;
    private String manufacture;

    private Category category;

    public ProductForm() {
    }

    public ProductForm( String name, int quantity, MultipartFile image, String description, String manufacture, Category category) {
        this.name = name;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
        this.manufacture = manufacture;
        this.category = category;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
