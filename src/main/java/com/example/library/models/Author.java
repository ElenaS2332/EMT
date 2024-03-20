package com.example.library.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Entity
@Table(name = "authors")
@Getter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @ManyToOne
    private Country country;

    @OneToMany
    private List<Book> books;

    public Author() {
    }

    public Author(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public Author(Long id, String name, String surname, Country country, List<Book> books) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.books = books;
    }
}
