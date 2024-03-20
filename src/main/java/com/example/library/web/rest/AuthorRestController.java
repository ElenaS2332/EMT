package com.example.library.web.rest;

import com.example.library.models.Author;
import com.example.library.models.dto.AuthorDto;
import com.example.library.services.AuthorService;
import com.example.library.services.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {
    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService ) {
        this.authorService = authorService;
    }

    @GetMapping("")
    public List<Author> getAuthors() {
        return authorService.findAll();
    }

    @PostMapping("/add-author")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Author> getAddAuthorPage(@RequestBody AuthorDto authorDto) {
        return authorService.addAuthorDto(authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable Long id)
    {
        return authorService.deleteAuthor(id)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> editAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto)
    {
        return authorService.updateAuthor(id, authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
