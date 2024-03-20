package com.example.library.services.impl;

import com.example.library.services.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.models.dto.BookDto;
import com.example.library.models.enumerations.CategoryEnum;
import com.example.library.models.exceptions.AuthorNotFoundException;
import com.example.library.models.exceptions.BookNotFoundException;
import com.example.library.repositories.AuthorRepository;
import com.example.library.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Book> save(String name, String category, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        CategoryEnum categoryEnum = CategoryEnum.valueOf(category); // Convert category string to enum
        this.bookRepository.deleteByName(name);
        Book book = new Book(name, categoryEnum, author, availableCopies);
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthorId()));
        CategoryEnum categoryEnum = bookDto.getCategory();
        this.bookRepository.deleteByName(bookDto.getName());
        Book book = new Book(bookDto.getName(), categoryEnum, author, bookDto.getAvailableCopies());
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, String name, String category, Long authorId, Integer availableCopies) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        book.setName(name);
        CategoryEnum categoryEnum = CategoryEnum.valueOf(category); // Convert category string to enum
        book.setCategory(categoryEnum);
        book.setAvailableCopies(availableCopies);

        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        book.setAuthor(author);

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        book.setName(bookDto.getName());
        CategoryEnum categoryEnum = bookDto.getCategory();
        book.setCategory(categoryEnum);
        book.setAvailableCopies(bookDto.getAvailableCopies());

        Author author = this.authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthorId()));
        book.setAuthor(author);

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> delete(Long id) {
        Book book = this.findBook(id);
        bookRepository.deleteById(id);
        return Optional.of(book);
    }

    @Override
    public Book findBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public Optional<Book> markRented(Long id) {
        Book book = this.findBook(id);
        book.setRented(!book.getIsRented());
        return Optional.of(bookRepository.save(book));
    }
}
