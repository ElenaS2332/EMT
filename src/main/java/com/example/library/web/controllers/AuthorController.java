package com.example.library.web.controllers;

import com.example.library.models.Author;
import com.example.library.models.Country;
import com.example.library.models.enumerations.CategoryEnum;
import com.example.library.services.AuthorService;
import com.example.library.services.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorController(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }


    @GetMapping
    public String getAuthorsPage(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("bodyContent", "authors");
        return "master-template";
    }

    @GetMapping("/add-author")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddAuthorPage(Model model) {
        List<Country> countries = this.countryService.findAll();
        model.addAttribute("countries", countries);
        model.addAttribute("bodyContent", "add-author");
        return "master-template";
    }

    @PostMapping("/save-author")
    public String addAuthor(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam Long country) {
        this.authorService.createAuthor(name, surname, country);
        return "redirect:/authors";
    }



    @DeleteMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id)
    {
        this.authorService.deleteAuthor(id);
        return "redirect:/home";
    }

//    @PostMapping("/edit/{id}")
//    public ResponseEntity<Author> editAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto)
//    {
//        return authorService.updateAuthor(id, authorDto.getName(), authorDto.getSurname(), authorDto.getCountryId())
//                .map(author -> ResponseEntity.ok().body(author))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

}
