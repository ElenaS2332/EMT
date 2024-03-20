package com.example.library.services.impl;

import com.example.library.models.Author;
import com.example.library.models.Country;
import com.example.library.models.dto.AuthorDto;
import com.example.library.models.exceptions.AuthorNotFoundException;
import com.example.library.models.exceptions.CountryNotFoundException;
import com.example.library.repositories.AuthorRepository;
import com.example.library.repositories.CountryRepository;
import com.example.library.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;


    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> createAuthor(String name, String surname, Long countryId) {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new CountryNotFoundException(countryId));
        Author author = new Author(name, surname, country);
        return Optional.of(authorRepository.save(author));
    }

    @Override
    public Optional<Author> updateAuthor(Long id, String name, String surname, Long countryId) {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new CountryNotFoundException(id));
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        return Optional.of(authorRepository.save(author));
    }

    @Override
    public Optional<Author> updateAuthor(Long authorId, AuthorDto authorDto) {
        Country country = countryRepository.findById(authorDto.getCountryId())
                .orElseThrow(() -> new CountryNotFoundException(authorDto.getCountryId()));
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        author.setName(author.getName());
        author.setSurname(author.getSurname());
        author.setCountry(country);
        return Optional.of(authorRepository.save(author));
    }

    @Override
    public Optional<Author> deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
        authorRepository.delete(author);
        return Optional.of(author);
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.of(authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id)));
    }

    @Override
    public Optional<Author> addAuthorDto(AuthorDto authorDto) {
        Author author = new Author(authorDto.getName(), authorDto.getSurname(),
                countryRepository.findById(authorDto.getCountryId())
                        .orElseThrow(() -> new CountryNotFoundException(authorDto.getCountryId())));

        return Optional.of(authorRepository.save(author));
    }
}
