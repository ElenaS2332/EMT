package com.example.library.services;

import com.example.library.models.Book;
import com.example.library.models.dto.BookDto;
import com.example.library.models.enumerations.CategoryEnum;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book findBook(Long id);
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    Optional<Book> save(String name, String category, Long authorId, Integer availableCopies);
    Optional<Book> save(BookDto bookDto);


//    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, String name, String category, Long authorId, Integer availableCopies);
    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteById(Long id);

    Optional<Book> delete(Long id);

    Optional<Book> markRented(Long id);
}
