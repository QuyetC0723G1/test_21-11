package com.luxury.model;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty(message = "This field cannot be left blank")
    @Size(min = 1, max = 20, message = "Please enter 1 to 20 char")
    private String name;
    @NotNull
    private int quantity;
    private String image;
    @Pattern(regexp = "^.{10,}$", message = "Please enter more than 10 characters")
    private String description;
    @NotNull
    private String manufacture;
    @ManyToOne
    private Category category;
    @ColumnDefault(value = "false")
    private boolean deleteFlag;

    public Product() {
    }


    public Product(String name, int quantity, String image, String description, String manufacture, Category category) {
        this.name = name;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
        this.manufacture = manufacture;
        this.category = category;
    }

    public Product(Long id, String name, int quantity, String image, String description, String manufacture, Category category) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
        this.manufacture = manufacture;
        this.category = category;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
