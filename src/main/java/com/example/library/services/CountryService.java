package com.example.library.services;

import com.example.library.models.Country;
import com.example.library.models.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Country findById(Long id);
    Optional<Country> save(String name, String continent);
    Optional<Country> save(CountryDto countryDto);

    Optional<Country> delete(Long id);
    Optional<Country> update(Long id, String name, String continent);
    Optional<Country> update(Long id, CountryDto countryDto);

}
