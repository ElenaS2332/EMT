package com.example.library.services;

import com.example.library.models.Author;
import com.example.library.models.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> createAuthor(String name, String surname, Long countryId);
    Optional<Author> updateAuthor(Long id, String name, String surname, Long countryId);
    Optional<Author> updateAuthor(Long id, AuthorDto authorDto);

    Optional<Author> deleteAuthor(Long id);
    Optional<Author> findById(Long id);

    Optional<Author> addAuthorDto(AuthorDto authorDto);
}
