package com.example.library.models;

import com.example.library.models.enumerations.CategoryEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Table(name = "books")
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @ManyToOne
    private Author author;

    private Integer availableCopies;

    private Boolean isRented;

    public Book() {
    }

    public Book(String name, CategoryEnum category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public Book(String name, CategoryEnum category, Author author, Integer availableCopies, Boolean isRented) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
        this.isRented = isRented;
    }

    public void setRented(Boolean rented) {
        isRented = rented;
    }
}
