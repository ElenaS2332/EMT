package com.example.library.web.controllers;

import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.services.AuthorService;
import com.example.library.services.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;

    private final AuthorService authorService;


    public BooksController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getBooksIndexPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Book> books = this.bookService.findAll()
                .stream()
                .sorted(Comparator.comparing(Book::getId))
                .collect(Collectors.toList());

        model.addAttribute("books", books);
        model.addAttribute("bodyContent", "books");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.bookService.findById(id).isPresent()) {
            Book book = this.bookService.findById(id).get();
            List<Author> authors = this.authorService.findAll();
            model.addAttribute("authors", authors);
            model.addAttribute("book", book);
            model.addAttribute("bodyContent", "edit-book");
            return "master-template";
        }
        return "redirect:/books?error=BookNotFound";
    }

    @GetMapping("/add-book")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addBookPage(Model model) {
        List<Author> authors = this.authorService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("bodyContent", "add-book");
        return "master-template";
    }

    @PostMapping("/save-book")
    public String saveBook(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String category,
            @RequestParam Long author,
            @RequestParam Integer availableCopies) {
        if (id != null) {
            this.bookService.edit(id, name, category, author, availableCopies);
        } else {
            this.bookService.save(name, category, author, availableCopies);
        }

        return "redirect:/books";
    }


}
